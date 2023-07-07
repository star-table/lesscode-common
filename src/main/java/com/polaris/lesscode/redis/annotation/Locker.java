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
 * @author admin
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Locker {

    /**
     * .重试时间 10ms.
     */
     long intervalMsecs() default 10l;
    /**
     * .key
     */
    String lockKey() default "";
    /**
     * .锁超时时间，防止线程在入锁以后，防止阻塞后面的线程无法获取锁, 10s
     */
    long expireMsecs() default 10* 1000;
    /**
     * .线程获取锁的等待时间,3s
     */
    long waitTimeoutMsecs() default 3 * 1000;
}
