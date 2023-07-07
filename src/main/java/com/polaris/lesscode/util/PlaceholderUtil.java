package com.polaris.lesscode.util;

import java.util.Properties;

import org.springframework.util.PropertyPlaceholderHelper;

public class PlaceholderUtil {

	private static PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper(
            "${", "}", ".", false);
 
    public static String replacePlaceholders(String value, final Properties properties) {
    	return helper.replacePlaceholders(value, properties);
    }

}
