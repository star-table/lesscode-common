/**
 * 
 */
package com.polaris.lesscode.jackson;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.polaris.lesscode.util.DateTimeFormatterUtils;

/**
 * @author Bomb.
 *
 */
public class DateJsonSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		Instant instant = Instant.ofEpochMilli(value.getTime());
		gen.writeString(DateTimeFormatterUtils.getDateTimeString(instant));
	}

}
