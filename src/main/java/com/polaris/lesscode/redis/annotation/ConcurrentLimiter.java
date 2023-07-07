/**
 * 
 */
package com.polaris.lesscode.redis.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



/**
 * @author Bomb
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ConcurrentLimiter {

    /**
     * .默认同时10个并发
     * @return long
     */
    long concurrencySize() default 10;
    
    /**
     * .默认key失效时间 10分钟
     * @return long
     */
    long expireMsecs() default 60*10*1000;
    /**
     * .获取许可超时时间 3秒
     */
    long timeoutMsecs() default 3 * 1000l;
    
    /**
     * .循环迭代尝试获取时间间隔  10毫秒
     * @return long
     */
    long intervalMsecs() default 10l;
    
    /**
     * Redis key.
     * @return String
     */
    String key() default "";
    
}
