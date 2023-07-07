package com.polaris.lesscode.util;

import java.util.List;

import com.alibaba.fastjson.JSON;

public class ConvertUtil {

	public static <T> T convert(Object o, Class<T> clazz) {
		if(o == null) {
			return null;
		}
		return JSON.parseObject(JSON.toJSONString(o), clazz);
	}
	
	public static <T> List<T> convertList(Object o, Class<T> clazz) {
		if(o == null) {
			return null;
		}
		return JSON.parseArray(JSON.toJSONString(o), clazz);
	}
}
