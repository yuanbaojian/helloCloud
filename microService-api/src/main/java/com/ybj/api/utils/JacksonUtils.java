package com.ybj.api.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public final class JacksonUtils {

    private  static final ObjectMapper objectMapper = new ObjectMapper();

    /**   对象转String
     * @param object
     * @return java.lang.String
     * @author yuanbaojian
     * @date 2019/12/1
     * @time 22:56
     */
    public static String objectToJsonString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    /**  string转jsonNode 或 jsonNode数组
     * @param jsonString
     * @return com.fasterxml.jackson.databind.JsonNode
     * @author yuanbaojian
     * @date 2019/12/1
     * @time 22:56
     */
    public static JsonNode stringToJsonNode(String jsonString){
        try {
            return objectMapper.readTree(jsonString);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }


    /**  jsongString转Object（对象或对象数组）
     * @param jsonStr
     * @param valueType
     * @return T
     * @author yuanbaojian
     * @date 2019/12/1
     * @time 22:56
     */
    public static <T> T stringToObject(String jsonStr, Class<T> valueType) {
        try {
            return objectMapper.readValue(jsonStr, valueType);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

}