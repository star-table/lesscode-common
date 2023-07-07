/**
 * 
 */
package com.polaris.lesscode.redis.aspect;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;

import com.polaris.lesscode.exception.BusinessException;
import com.polaris.lesscode.redis.annotation.ConcurrentLimiter;
import com.polaris.lesscode.vo.BaseResultCode;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Bomb.
 *
 */
@Aspect
@Slf4j
public class RedisConcurrentLimiterAspect {
    
    @Autowired
    private ValueOperations<String, Long> valueOperations;

    /**
     * define a point cut
     */
    @Pointcut(value="@annotation(com.polaris.lesscode.redis.annotation.ConcurrentLimiter)")
    public void pointcut() {
        //nothing to do.
    }
    
    @Around(value = "pointcut() && @annotation(limiter)")
    public Object around(ProceedingJoinPoint joinPoint,ConcurrentLimiter limiter) throws Throwable {
        log.debug("starting redis concurrent limiter");
        String simpleClassName = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        String key = limiter.key()+":"+simpleClassName+":"+methodName;
        
        long timeout = limiter.timeoutMsecs();
        boolean permit = false;
        while (timeout >= 0) {
            if(!valueOperations.getOperations().hasKey(key) || 
                    valueOperations.get(key).longValue() < limiter.concurrencySize()) {
                permit = true;
            }
            if(permit) {
                valueOperations.increment(key, 1);
                try {
                    Object result = joinPoint.proceed();
                    log.debug("ended redis concurrent limiter");
                    return result;
                }finally {
                    valueOperations.decrement(key, 1);
                    valueOperations.getOperations().expire(key, limiter.expireMsecs(), TimeUnit.MILLISECONDS);
                }
            }
            timeout -= limiter.intervalMsecs();
            // 延时
            LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(limiter.intervalMsecs()));
        }
        throw new BusinessException(BaseResultCode.RATE_LIMITER_OVERFLOW_ERROR);
    }
}
