package com.github.nitish_chandra_m.ad_campaign_tool.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JSONUtil {

    public static <T> T deserializeJSON(String json, Class<T> tClass) throws Exception {
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        try {
            return mapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

}
