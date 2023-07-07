package com.polaris.lesscode.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.polaris.lesscode.util.CacheKeyUtils;
import com.polaris.lesscode.util.Maps;


public class RedisKeyUtilsTests {

	@Test
	public void testBuildKey() {
		String application = "app";
		String type = "lock"; //or buiness
		String keyTemplate = "appInfo:${appId}";
		
		String key = CacheKeyUtils.buildKey(new Maps<String, String>().put("appId", "1").toMap(), application, type, keyTemplate);
		System.out.println(key);
		assertEquals("app:lock:appInfo:1", key);
	}
}
