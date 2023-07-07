/**
 * 
 */
package com.polaris.lesscode.web;

import java.time.Instant;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

import com.polaris.lesscode.util.DateTimeFormatterUtils;
import com.polaris.lesscode.util.UsefulRegexUtils;

/**
 * @author Bomb
 * Instant convert.
 */
public class InstantConvert implements Converter<String, Instant> {

	@Override
	public Instant convert(String source) {
		Instant instant = null;
		if(!StringUtils.isEmpty(source)) {
			if(UsefulRegexUtils.isDateTime(source)) {
				instant = DateTimeFormatterUtils.parseDateTime(source);
			}else if(UsefulRegexUtils.isDate(source)) {
				instant = DateTimeFormatterUtils.parseDate(source);
			}else if(UsefulRegexUtils.isMatch(UsefulRegexUtils.SECOND_LONG_REGEX_PATTERN, source)) {
				return Instant.ofEpochSecond(Long.valueOf(source));
			}else if(UsefulRegexUtils.isMatch(UsefulRegexUtils.REGEX_TIME_MILLS, source)) {
				return Instant.ofEpochMilli(Long.parseLong(source));
			}else {
				throw new IllegalArgumentException("the Date format incorrect");
			}
		}
		return instant;
	}
}
