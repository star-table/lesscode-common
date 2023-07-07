package com.polaris.lesscode.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.polaris.lesscode.consts.CommonConsts;

/**
 * 
 * @author Bomb
 *
 */
public class DateTimeFormatterUtils {
	
	/**
	 * DateTime formatter.
	 */
	public static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern(CommonConsts.DEFAULT_DATE_PATTERN, Locale.getDefault());


	/**
	 * Date formatter.
	 */
	public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern(CommonConsts.DEFAULT_SIMPLE_DATE_PATTERN, Locale.ENGLISH);


	/**
	 * format Instant to String, pattern is : yyyy-MM-dd HH:mm:ss.
	 * 
	 * @param instant Instant
	 * @return String
	 */
	public static String getDateTimeString(Instant instant) {
		DateTimeFormatter dateTimeFormatter = DATETIME_FORMAT.withZone(ZoneId.systemDefault());
		return dateTimeFormatter.format(instant);
	}


	/**
	 * format Instant to String,pattern is : yyyy-MM-dd.
	 * 
	 * @param instant Instant
	 * @return String
	 */
	public static String getDateString(Instant instant) {
		DateTimeFormatter dateTimeFormatter = DATE_FORMAT.withZone(ZoneId.systemDefault());
		return dateTimeFormatter.format(instant);
	}

	
	/**
	 * parse string to DateTime,pattern is : yyyy-MM-dd HH:mm:ss.
	 * @param dateTimeString String
	 * @return Instant
	 */
	public static Instant parseDateTime(String dateTimeString) {
		DateTimeFormatter dateTimeFormatter = DATETIME_FORMAT.withZone(ZoneId.systemDefault());
		return Instant.from(dateTimeFormatter.parse(dateTimeString));
	}
	
	/**
	 * parse String to date, pattern is : yyyy-MM-dd.
	 * @param dateString String
	 * @return Instant
	 */
	public static Instant parseDate(String dateString) {
		DateTimeFormatter dateTimeFormatter = DATE_FORMAT.withZone(ZoneId.systemDefault());
		return Instant.from(dateTimeFormatter.parse(dateString));
	}
	
	/**
	 * parse data
	 * @param source date string or timemills
	 * @return Instant
	 */
	public static Optional<Instant> parse(String source) {
		Instant instant = null;
		if(!StringUtils.isEmpty(source)) {
			if(UsefulRegexUtils.isDateTime(source)) {
				instant = DateTimeFormatterUtils.parseDateTime(source);
			}else if(UsefulRegexUtils.isDate(source)) {
				instant = DateTimeFormatterUtils.parseDate(source);
			}else if(UsefulRegexUtils.isTimeMills(source)){
				instant = Instant.ofEpochMilli(Long.parseLong(source));
			}else {
				throw new IllegalArgumentException("the Date format incorrect");
			}
		}
		return Optional.ofNullable(instant);
		
	}
}
