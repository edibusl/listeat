package com.listeat.helpers;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class CustomDateSerializer extends JsonSerializer<Date> {
    public static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2)
            throws IOException {
        if (value == null) {
            gen.writeNull();
        } else {
            gen.writeString(DATE_FORMATTER.format(value.getTime()));
        }
    }
}