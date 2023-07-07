/**
 * 
 */
package com.polaris.lesscode.util;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.polaris.lesscode.consts.CommonConsts;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Bomb.
 *
 */

@Slf4j
public class GsonUtils {

	/**
	 * gson.
	 */
	private static Gson gson;
	/**
	 * json parser.
	 */
	//private static JsonParser jsonParser = new JsonParser();

	/**
	 * static block
	 */
	static {
		gson = new GsonBuilder().setDateFormat(CommonConsts.DEFAULT_DATE_PATTERN).create();
	}

	/**
	 * 使用泛型方法，把json字符串转换为相应的JavaBean对象。
	 */
	public static <T> T readValue(String jsonStr, Class<T> valueType) {
		return exec(() -> gson.fromJson(jsonStr, valueType));
	}

	public static <T> T readValue(String jsonStr, Type type) {
		return exec(() -> gson.fromJson(jsonStr, type));
	}

	/**
	 * JSONElement 转自定义TYPE的任意对象
	 */
	public static <T> T readValue(JsonElement json, Type type) {
		return exec(() -> gson.fromJson(json, type));
	}

	public static <T> T readValue(JsonElement json, Class<T> valueType) {
		return exec(() -> gson.fromJson(json, valueType));
	}

	/**
	 * 把Object转换为json字符串
	 */
	public static String toJson(Object object) {
		return exec(() -> gson.toJson(object));
	}

	/**
	 * 把Object转换为json字符串
	 */
	public static String toJson(Integer[] ints) {
		return exec(() -> gson.toJson(ints));
	}

	/**
	 * json string to JsonNode
	 *
	 * @param jsonStr
	 * @return JsonNode
	 */
	public static JsonObject toJsonNode(String jsonStr) {
		return exec(() -> JsonParser.parseString(jsonStr).getAsJsonObject());
	}

	private static <T> T exec(Function<T> function) {
		try {
			return function.apply();
		} catch (Exception e) {
			log.error("", e);
		}
		return null;
	}

	@FunctionalInterface
	private static interface Function<T> {
		T apply();
	}
}
