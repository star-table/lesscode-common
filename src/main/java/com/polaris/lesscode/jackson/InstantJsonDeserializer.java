/**
 * 
 */
package com.polaris.lesscode.jackson;

import java.io.IOException;
import java.time.Instant;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.polaris.lesscode.util.DateTimeFormatterUtils;
import com.polaris.lesscode.util.UsefulRegexUtils;

/**
 * @author Bomb
 *
 */
public class InstantJsonDeserializer extends JsonDeserializer<Instant> {

	@Override
	public Instant deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        if(StringUtils.isNotBlank(p.getText())){
        	String dateString = p.getText().trim();
			if(UsefulRegexUtils.isMatch(UsefulRegexUtils.DATE_REGEX_PATTERN, dateString)) {
				return DateTimeFormatterUtils.parseDate(dateString);
			}
			if(UsefulRegexUtils.isMatch(UsefulRegexUtils.DATETIME_REGEX_PATTERN, dateString)) {
				return DateTimeFormatterUtils.parseDateTime(dateString);
			}
			if(UsefulRegexUtils.isMatch(UsefulRegexUtils.SECOND_LONG_REGEX_PATTERN, dateString)) {
				return Instant.ofEpochSecond(Long.valueOf(dateString));
			}
			if(UsefulRegexUtils.isMatch(UsefulRegexUtils.REGEX_TIME_MILLS, dateString)) {
				return Instant.ofEpochMilli(Long.parseLong(dateString));
			}
			throw new IllegalArgumentException("时间格式错误");			
        }
		return null;
	}

}
