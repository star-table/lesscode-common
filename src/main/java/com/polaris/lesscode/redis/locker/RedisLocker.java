package com.polaris.lesscode.redis.locker;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.function.Function;

import org.springframework.data.redis.core.ValueOperations;

import com.polaris.lesscode.exception.BusinessException;
import com.polaris.lesscode.vo.BaseResultCode;

/**
 * @author      Bomb.
 */
public class RedisLocker {
	/**
	 * valueOperations.
	 */
	private ValueOperations<String, Long> valueOperations;
	/**
	 * 重试时间
	 */
	private long intervalMsecs = 10l;
	/**
	 * 锁的后缀
	 */
	private static final String LOCK_SUFFIX = ":locker";
	/**
	 * 锁的key
	 */
	private String lockKey;
	/**
	 * 锁超时时间，防止线程在入锁以后，防止阻塞后面的线程无法获取锁
	 */
	private long expireMsecs = 60 * 10* 1000;
	/**
	 * 线程获取锁的等待时间
	 */
	private long timeoutMsecs = 10 * 1000;
	/**
	 * 是否锁定标志
	 */
	private volatile boolean locked = false;

	/**
	 * 构造器
	 * 
	 * @param redisTemplate
	 * @param lockKey       锁的key
	 */
	public RedisLocker(ValueOperations<String, Long> valueOperations, String lockKey) {
		this.valueOperations = valueOperations;
		this.lockKey = lockKey + LOCK_SUFFIX;
	}

	/**
	 * 构造器
	 * 
	 * @param redisTemplate
	 * @param lockKey       锁的key
	 * @param timeoutMsecs  获取锁的超时时间
	 */
	public RedisLocker(ValueOperations<String, Long> valueOperations, String lockKey, long timeoutMsecs) {
		this(valueOperations, lockKey);
		this.timeoutMsecs = timeoutMsecs;
	}

	/**
	 * 构造器
	 * 
	 * @param redisTemplate
	 * @param lockKey       锁的key
	 * @param timeoutMsecs  获取锁的超时时间
	 * @param expireMsecs   锁的有效期
	 */
	public RedisLocker(ValueOperations<String, Long> valueOperations, String lockKey, long timeoutMsecs, long expireMsecs,int intervalMsecs) {
		this(valueOperations, lockKey, timeoutMsecs);
		this.expireMsecs = expireMsecs;
		this.intervalMsecs = intervalMsecs;
	}

	public String getLockKey() {
		return lockKey;
	}

	/**
	 * 封装和jedis方法
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	private boolean setNX(final String key, final Long value) {
		return valueOperations.setIfAbsent(key, value,expireMsecs, TimeUnit.MILLISECONDS);
	}

	/**
	 * 获取锁
	 * 
	 * @return 获取锁成功返回ture，超时返回false
	 * @throws InterruptedException
	 */
	public  boolean lock() {
		long timeout = timeoutMsecs;
		while (timeout >= 0) {
			if (this.setNX(lockKey, 0l)) {
				locked = true;
				return true;
			}
			timeout -= intervalMsecs;
			// 延时
            LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(intervalMsecs));
		}
		return false;
	}

	/**
	 * 释放获取到的锁
	 */
	public synchronized void unlock() {
		if (locked) {
			valueOperations.getOperations().delete(lockKey);
			locked = false;
		}
	}
	
	/**
	 * exclusive execute function
	 * @param func Function
	 * @param t T
	 * @return R
	 * @throws InterruptedException
	 */
	public <T,R> R  exclusiveExecute(Function<T, R> func, T t) {
		if(lock()) {
		    try {
		        return func.apply(t);
		    }finally {
		        unlock();
		    }
		}
		throw new BusinessException(BaseResultCode.RATE_LIMITER_OVERFLOW_ERROR);
	}
}
