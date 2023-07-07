package com.polaris.lesscode.util;

import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.PropertyPlaceholderHelper;

/**
 * 缓存key工具
 * 
 * @author nico
 * @date 2020年9月9日
 */
public final class CacheKeyUtils {

    public static final String DELIMITER = ":";
    
    private final static PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper(
            "${", "}", ".", false);
    
	/**
	 * 构建key
	 * 
	 * @param el	key模板，eg: datacenter,business,appinfo,${appId}
	 * @param properties	占位符对应的值
	 * @return
	 */
	public static String buildKey(final Map<String, String> properties, String... el) {
		if(el == null || el.length == 0) {
			throw new RuntimeException("keyTemplates can't be empty.");
		}
		String keyTemplate = StringUtils.join(el, DELIMITER);
		if(properties == null || properties.size() == 0) {
			return keyTemplate.toString();
		}
    	return helper.replacePlaceholders(keyTemplate, map2Properties(properties));
	}
	
	public static String buildKey(String... el) {
		return buildKey(null, el);
	}
	
	private static Properties map2Properties(final Map<String, String> map) {
		Properties properties = new Properties();
		properties.putAll(map);
		return properties;
	}
}
