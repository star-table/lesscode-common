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
 * @author Bomb.
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter {

    /**
     * Redis key
     * @return String
     */
    String id() default "";
    
    /**
     * .瞬时最大并发
     * @return
     */
    int maxBurst() default 0;
    
    /**
     * permit second.
     * @return integer
     */
    int rate() default 0;
    
}
