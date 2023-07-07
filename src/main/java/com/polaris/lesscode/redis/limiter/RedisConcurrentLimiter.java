package com.polaris.lesscode.redis.limiter;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.function.Function;

import org.springframework.data.redis.core.ValueOperations;

import com.polaris.lesscode.exception.BusinessException;
import com.polaris.lesscode.vo.BaseResultCode;

/**
 * @author      Bomb.
 *
 * @createTime: 2019-07-25 10:45:58
 */
public class RedisConcurrentLimiter {

	/**
	 * valueOperations.
	 */
	private ValueOperations<String, Long> valueOperations;
	/**
	 * retry  interval.
	 */
	private long intervalMsecs = 10l;
	/**
	 * limiter suffix.
	 */
	private static final String LIMITER_SUFFIX = ":concurrent:limiter";
	
	/**
	 * limiter key.
	 */
	private String limiterKey;
	
	/**
	 * concurrency size.
	 */
	private long concurrencySize = 10;
	
	/**
	 * key expire timeout
	 */
	private long expireMsecs = 60 * 10 * 1000l;
	
	/**
	 * wait timeout
	 */
	private long timeoutMsecs = 5 * 1000l;
	

	/**
	 * constructor.
	 * 
	 * @param redisTemplate RedisTemplate
	 * @param limiterKey       redis counter key.
	 */
	public RedisConcurrentLimiter(ValueOperations<String, Long> valueOperations, String limiterKey,long concurrencySize) {
		this.valueOperations = valueOperations;
		this.limiterKey = limiterKey + LIMITER_SUFFIX;
		this.concurrencySize = concurrencySize;
	}

	/**
	 * constructor.
	 * 
	 * @param redisTemplate RedisTemplate
	 * @param limiterKey       redis counter key.
	 * @param timeoutMsecs  wait timeout.
	 */
	public RedisConcurrentLimiter(ValueOperations<String, Long> valueOperations, String limiterKey,long concurrencySize, long timeoutMsecs) {
		this(valueOperations, limiterKey,concurrencySize);
		this.timeoutMsecs = timeoutMsecs;
	}

	/**
	 * Constructor.
	 * 
	 * @param redisTemplate RedisTemplate.
	 * @param limiterKey    Redis counter key
	 * @param timeoutMsecs  water counter token timeout
	 * @param expireMsecs   counter key expire timeout
	 */
	public RedisConcurrentLimiter(ValueOperations<String, Long> valueOperations, String limiterKey,long concurrencySize, long timeoutMsecs, long expireMsecs,long intervalMsecs) {
		this(valueOperations, limiterKey,concurrencySize, timeoutMsecs);
		this.expireMsecs = expireMsecs;
		this.intervalMsecs = intervalMsecs;
	}
	
	public Boolean fetchPermit() {
	    long timeout = timeoutMsecs;
        boolean permit = false;
        while (timeout >= 0) {
            if(!valueOperations.getOperations().hasKey(limiterKey) || 
                    valueOperations.get(limiterKey).longValue() < this.concurrencySize) {
                permit = true;
            }
            if(permit) {
                valueOperations.increment(limiterKey, 1);
            }
            timeout -= intervalMsecs;
            // 延时
            LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(intervalMsecs));
        }
        return permit;
	}
	
	public void finishJob() {
	    valueOperations.decrement(limiterKey, 1);
        valueOperations.getOperations().expire(limiterKey, expireMsecs, TimeUnit.MILLISECONDS);
	}

	
	/**
	 * concurrency execute function
	 * @param func Function
	 * @param t T
	 * @return R
	 * @throws InterruptedException
	 */
	public <T,R> R  concurrencyExecute(Function<T, R> func, T t) {
		if(fetchPermit()) {
		    try {
		        return func.apply(t);
		    }finally {
		        finishJob();
		    }
		}
		throw new BusinessException(BaseResultCode.RATE_LIMITER_OVERFLOW_ERROR);
	}
	
	
}
