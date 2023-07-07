/**
 * 
 */
package com.polaris.lesscode.jackson;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.polaris.lesscode.util.DateTimeFormatterUtils;
import com.polaris.lesscode.util.UsefulRegexUtils;

/**
 * @author admin
 *
 */
public class DateJsonDeserializer extends JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		if(StringUtils.isNotBlank(p.getText())){
        	String dateString = p.getText().trim();
        	Instant instant = null;
			if(UsefulRegexUtils.isMatch(UsefulRegexUtils.DATE_REGEX_PATTERN, dateString)) {
				instant = DateTimeFormatterUtils.parseDate(dateString);
			}else if(UsefulRegexUtils.isMatch(UsefulRegexUtils.DATETIME_REGEX_PATTERN, dateString)) {
				instant = DateTimeFormatterUtils.parseDateTime(dateString);
			}else if(UsefulRegexUtils.isMatch(UsefulRegexUtils.SECOND_LONG_REGEX_PATTERN, dateString)) {
				instant = Instant.ofEpochSecond(Long.valueOf(dateString));
			}else if(UsefulRegexUtils.isMatch(UsefulRegexUtils.REGEX_TIME_MILLS, dateString)) {
				instant =  Instant.ofEpochMilli(Long.parseLong(dateString));
			}else {
				throw new IllegalArgumentException("时间格式错误");	
			}
			return new Date(instant.toEpochMilli());
        }
		return null;
	}

}
