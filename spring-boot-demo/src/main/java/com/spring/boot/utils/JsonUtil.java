package com.spring.boot.utils;

import java.lang.reflect.Type;
import java.sql.Date;
import java.text.DateFormat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class JsonUtil {
    private static String GSON_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss:SSS";

    /**
     * <p>
     * Description: 用Gson库，对Date按格式转String
     * </p>
     * <p>
     * Create Time: 2016年2月25日
     * </p>
     * 
     * @author lijianqiang
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        Gson gson = new GsonBuilder().serializeNulls().setDateFormat(GSON_DATE_FORMAT).create();
        return gson.toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        Gson gson = new GsonBuilder().serializeNulls().setDateFormat(GSON_DATE_FORMAT).create();
        return gson.fromJson(json, classOfT);
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        Gson gson = new GsonBuilder().serializeNulls().setDateFormat(GSON_DATE_FORMAT).create();
        return gson.fromJson(json, typeOfT);
    }

    public static String toJsonDate2Long(Object object) {
        Gson gson = create();
        return gson.toJson(object);
    }

    public static <T> T fromJsonLong2Date(String json, Class<T> classOfT) {
        Gson gson = create();
        return gson.fromJson(json, classOfT);
    }

    public static <T> T fromJsonLong2Date(String json, Type typeOfT) {
        Gson gson = create();
        return gson.fromJson(json, typeOfT);
    }

    //
    public static Gson create() {
        GsonBuilder gb = new GsonBuilder().serializeNulls();
        gb.registerTypeAdapter(Date.class, new DateSerializer()).setDateFormat(DateFormat.LONG);
        gb.registerTypeAdapter(Date.class, new DateDeserializer()).setDateFormat(DateFormat.LONG);
        Gson gson = gb.create();
        return gson;
    }

    public static class DateDeserializer implements JsonDeserializer<Date> {

        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
            return new Date(json.getAsJsonPrimitive().getAsLong());
        }
    }

    public static class DateSerializer implements JsonSerializer<Date> {
        public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.getTime());
        }
    }

}
