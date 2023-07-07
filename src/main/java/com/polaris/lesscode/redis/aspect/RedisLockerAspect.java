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
import com.polaris.lesscode.redis.annotation.Locker;
import com.polaris.lesscode.vo.BaseResultCode;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Bomb.
 *
 */
@Aspect
@Slf4j
public class RedisLockerAspect {

    @Autowired
    private ValueOperations<String, Long> valueOperations;

    /**
     * define a point cut
     */
    @Pointcut(value="@annotation(com.polaris.lesscode.redis.annotation.Locker)")
    public void pointcut() {
        //nothing to do.
    }
    
    @Around(value = "pointcut() && @annotation(locker)")
    public Object around(ProceedingJoinPoint joinPoint,Locker locker) throws Throwable {
        log.debug("starting redis locker");
        String simpleClassName = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        String key = locker.lockKey()+":"+simpleClassName+":"+methodName;        
        long timeout = locker.waitTimeoutMsecs();
        boolean locked = false;
        while (timeout >= 0) {
            if (valueOperations.setIfAbsent(key, 0l,locker.expireMsecs(), TimeUnit.MILLISECONDS)) {
                locked = true;
            }
            if(locked) {
                try {
                    Object obj = joinPoint.proceed();
                    log.debug("ended redis locker");
                    return obj;
                }finally {
                    valueOperations.getOperations().delete(key);
                    locked = false;
                }
            }
            timeout -= locker.intervalMsecs();
            LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(locker.intervalMsecs()));
        }
        throw new BusinessException(BaseResultCode.RATE_LIMITER_OVERFLOW_ERROR);
    }
}
