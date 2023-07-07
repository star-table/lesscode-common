package com.polaris.lesscode.redis.aspect;

import javax.annotation.PostConstruct;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.polaris.lesscode.exception.BusinessException;
import com.polaris.lesscode.redis.annotation.RateLimiter;
import com.polaris.lesscode.redis.limiter.RedisRateLimiter;
import com.polaris.lesscode.vo.BaseResultCode;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
public class RedisRateLimiterAspect {
    
    @Autowired
    private StringRedisTemplate template; 
    
    private RedisRateLimiter redisRateLimiter;

    @PostConstruct
    public void init() {
        redisRateLimiter = new RedisRateLimiter();
        redisRateLimiter.setTemplate(template);
    }
    
    /**
     * define a point cut
     */
    @Pointcut(value="@annotation(com.polaris.lesscode.redis.annotation.RateLimiter)")
    public void pointcut() {
        //nothing to do.
    }
    
    @Around(value = "pointcut() && @annotation(rateLimiter)")
    public Object around(ProceedingJoinPoint joinPoint,RateLimiter annolimiter) throws Throwable{
        log.debug("starting redis rate limiter");
        String simpleClassName = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        String key = annolimiter.id()+":"+simpleClassName+":"+methodName;
        boolean result = redisRateLimiter.tryFetchToken(key,annolimiter.maxBurst(),annolimiter.rate());
        if(!result) {
            throw new BusinessException(BaseResultCode.RATE_LIMITER_OVERFLOW_ERROR);
        }
        return joinPoint.proceed();
    }
}
