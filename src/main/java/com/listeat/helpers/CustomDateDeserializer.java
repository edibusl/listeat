package com.listeat.helpers;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;

public class CustomDateDeserializer extends JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonParser jsonparser, DeserializationContext context) throws IOException {
        String dateAsString = jsonparser.getText();
        try {
            java.util.Date dateObj = CustomDateSerializer.DATE_FORMATTER.parse(dateAsString);
            java.sql.Date sqlDate = new java.sql.Date(dateObj.getTime());

            return sqlDate;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
