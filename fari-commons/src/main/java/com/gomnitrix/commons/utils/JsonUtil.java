package com.gomnitrix.commons.utils;

import com.gomnitrix.commons.Response.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class JsonUtil {
    public static String respToJson(Response resp){
        return objectToJson(resp);
    }

    public static String objectToJson(Object object){
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.toJson(object);
    }
}
