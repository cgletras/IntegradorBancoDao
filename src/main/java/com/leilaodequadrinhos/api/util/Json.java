package com.leilaodequadrinhos.api.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Json {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String objectToJson(Object obj) {
        String json = null;

        try {
            json = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
    }

}
