package com.polaris.lesscode.redis.limiter;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.web.server.ServerWebExchange;

import com.polaris.lesscode.exception.BusinessException;
import com.polaris.lesscode.vo.BaseResultCode;

/**
 * @author Bomb.
 */
public class RedisRateLimiter {

	/**
	 * limiter Lua script.
	 */
	private static DefaultRedisScript<Long> limiterLuaScript;
	
	/**
	 * static block.
	 */
	static {
		limiterLuaScript = new DefaultRedisScript<>();
		limiterLuaScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/redis-ratelimiter.lua")));
		limiterLuaScript.setResultType(Long.class);
	}
	
	/**
	 * redis value operations.
	 */
	private StringRedisTemplate template; 
	
	/**
	 * set method.
	 * @param template
	 */
	public void setTemplate(StringRedisTemplate template) {
		this.template = template;
	}
	
	/**
	 * get keys by id.
	 * @param id Redis Key
	 * @return List<String>
	 */
	private static List<String> getKeys(String id) {
	    String prefix = "permiter_rate_limiter.{" + id;
	    String tokenKey = prefix + "}.tokens";
	    String timestampKey = prefix + "}.timestamp";
	    return Arrays.asList(tokenKey, timestampKey);
	}
	
	/**
	 * try to fetch a token.
	 * @param id
	 * @param maxBurst
	 * @param rate
	 * @return
	 */
	public boolean tryFetchToken(String id,Integer maxBurst,Integer rate) {
		boolean result = false;
		List<String> keys = getKeys(id);
		Object[] scriptArgs = new String[] {String.valueOf(rate),
		        String.valueOf(maxBurst), String.valueOf(Instant.now().getEpochSecond()) , "1"};
		Long allowedNumber = this.template.execute(limiterLuaScript, keys, scriptArgs);
		if(null != allowedNumber && allowedNumber.intValue() == 1) {
			result = true;
		}
		return result;
	}
	
	/**
	 * rate limited execute a function.
	 * @param func Function
	 * @param t T
	 * @return R
	 * @throws InterruptedException 
	 */
	public <T,R> R rateLimitedExecute(Function<T, R> func, T t,String key,Integer maxBurst,Integer rate)  {
		boolean	result = tryFetchToken(key,maxBurst,rate);
        if(!result) {
        	throw new BusinessException(BaseResultCode.RATE_LIMITER_OVERFLOW_ERROR);
        }
        return func.apply(t);
	}
	
	/**
	 * try fetch tokens
	 * @param id key ids
	 * @param maxBurst
	 * @param rate  permit/second
	 * @param tokenSize
	 * @return
	 */
	public Optional<Long> tryFetchToken(String id,Integer maxBurst,Integer rate,Long tokenSize){
		List<String> keys = getKeys(id);
		Object[] scriptArgs = new String[] {String.valueOf(rate),
		        String.valueOf(maxBurst), String.valueOf(Instant.now().getEpochSecond()), tokenSize.toString()};
		Long allowedNumber = this.template.execute(limiterLuaScript, keys, scriptArgs);
		return Optional.ofNullable(allowedNumber);
	}
}
