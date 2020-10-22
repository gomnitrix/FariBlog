package com.gomnitrix.farigateway.utils;

import com.gomnitrix.commons.utils.JsonUtil;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public abstract class OkHttpUtil {
    private static final OkHttpClient httpClient = new OkHttpClient.Builder()
            .connectionPool(new ConnectionPool(5, 20, TimeUnit.SECONDS))
            .build();

    private static RequestBody getBody(Map<String, String> body){
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : body.entrySet()) {
            bodyBuilder.add(entry.getKey(), entry.getValue());
        }
        return bodyBuilder.build();
    }

    public static Request buildPostRequest(Map<String, String> bodyMap, String url, Map<String, String> headerMap){
        RequestBody requestBody = getBody(bodyMap);
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(url);
        requestBuilder.post(requestBody);
        for (Map.Entry<String, String> header : headerMap.entrySet()){
            requestBuilder.addHeader(header.getKey(), header.getValue());
        }
        return requestBuilder.build();
    }

    public static Response requestExecute(Request request) throws IOException {
        return httpClient.newCall(request).execute();
    }

    public static Map getDataFromResp(Response response) throws IOException {
        assert response.body() != null;
        String result = response.body().string();
        return JsonUtil.jsonToObject(result, Map.class);
    }
}
