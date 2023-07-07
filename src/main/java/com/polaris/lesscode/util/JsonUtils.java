package com.polaris.lesscode.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author jeecg-boot
 * @version 1.0
 * @description 描述
 * @create 2019-11-25 14:33
 */
public class JsonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    static{
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    }

    /**
     * 对象转换为json
     * @param obj
     * @return
     */
    public static String toJson(Object obj){
        return JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat);
    }

    /**
     * json转换为对象
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T fromJson(String json, Class<T> clazz){
        try {
            return JSON.parseObject(json, clazz);
        }catch (Exception ex){
            logger.error("json decode error. json:"+json+";class:"+clazz.getName());
            return null;
        }
    }

    /**
     * json转换为泛型
     * @param json
     * @param type
     * @return
     */
    public static <T> T fromJson(String json, TypeReference<T> type){
        try {
            return JSON.parseObject(json, type);
        }catch (Exception ex){
            logger.error("json decode error. json:"+json+";type:"+type.getType().getTypeName());
            return null;
        }
    }

    /**
     * json转换为泛型
     * @param json
     * @param type
     * @return
     */
    public static <T> T fromJson(String json, Type type){
        try {
            return JSON.parseObject(json, type);
        }catch (Exception ex){
            logger.error("json decode error. json:"+json+";type:"+type.getTypeName());
            return null;
        }
    }

    /**
     * json数据转换为泛型列表
     * @param json
     * @param clazz
     * @return
     */
    public static <T> List<T>  fromJsonArray(String json, Class<T> clazz){
        try {
            return JSONArray.parseArray(json, clazz);
        }catch (Exception ex){
            logger.error("jsonArray decode error. json:"+json+";type:"+clazz.getName());
            return null;
        }
    }

    public static void isJson (String json) throws Exception {
        JSON.parse(json);
    }

}