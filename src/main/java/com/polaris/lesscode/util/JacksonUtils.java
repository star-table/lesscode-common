package com.polaris.lesscode.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.polaris.lesscode.consts.CommonConsts;
import com.polaris.lesscode.jackson.InstantJsonDeserializer;
import com.polaris.lesscode.jackson.InstantJsonSerializer;

/**
 * 
 * @author Bomb.
 *
 */
public class JacksonUtils {
	
	/**
	 * object mapper.
	 */
	private static ObjectMapper mapper ;
	
	/**
	 * static block.
	 */
	static {
		mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		module.addDeserializer(Instant.class, new InstantJsonDeserializer());
		module.addSerializer(Instant.class, new InstantJsonSerializer());
		mapper.registerModule(module);
		SimpleDateFormat smt = new SimpleDateFormat(CommonConsts.DEFAULT_DATE_PATTERN);
		mapper.setDateFormat(smt);
		mapper.setTimeZone(TimeZone.getDefault());
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
	}
	
	/**
	 * convert object to json string.
	 * @param value Object.
	 * @return String
	 * @throws JsonProcessingException
	 */
	public static String Obj2Str(Object value) throws JsonProcessingException  {
		return mapper.writeValueAsString(value);
	}
	
	/**
	 * read value from string.
	 * @param value String
	 * @param clazz Class<T>
	 * @return T
	 * @throws Exception
	 */
	public static <T> T readValue(String value,Class<T> clazz) throws Exception {
		return mapper.readValue(value, clazz);
	}
	
	/**
	 * read value from string by javatype.
	 * @param value Json String
	 * @param javaType JavaType
	 * @return T
	 * @throws Exception
	 */
	public static <T> T readValue(String value,JavaType javaType) throws Exception {
		return mapper.readValue(value, javaType);
	}
}
