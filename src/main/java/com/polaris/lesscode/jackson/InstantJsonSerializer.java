/**
 * 
 */
package com.polaris.lesscode.jackson;

import java.io.IOException;
import java.time.Instant;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.polaris.lesscode.util.DateTimeFormatterUtils;

/**
 * @author Bomb.
 *
 */
public class InstantJsonSerializer extends JsonSerializer<Instant> {

	@Override
	public void serialize(Instant value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeString(DateTimeFormatterUtils.getDateTimeString(value));
	}

}
