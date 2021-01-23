package com.gomnitrix.commons.utils;

import com.gomnitrix.commons.Response.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public abstract class JsonUtil {
    private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    public static String respToJson(Response resp){
        return objectToJson(resp);
    }

    public static String objectToJson(Object object){
        return gson.toJson(object);
    }

    public static <T> T jsonToObject(String jsonString, Class<T> clazz){
        return gson.fromJson(jsonString, clazz);
    }

    public static JsonObject obj2JsonObj(Object object){
        JsonElement element = gson.toJsonTree(object);
        return element.getAsJsonObject();
    }
}
