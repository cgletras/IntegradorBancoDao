package com.leilaodequadrinhos.api.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Json {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String objectToJson(Object obj) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
        mapper.setDateFormat(df);
        String json = null;

        try {
            json = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
    }

}
