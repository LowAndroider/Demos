package com.exam.basecomponent.net;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author LowAndroider
 * @date 2018年9月26日
 */
public class OkHttpUtil {
    private static OkHttpClient okHttpClientSingleInstance;

    private static OkHttpClient getInstance() {
        if (okHttpClientSingleInstance == null) {
            synchronized (OkHttpClient.class) {
                if (okHttpClientSingleInstance == null) {
                    HttpLoggingInterceptor httpInterceptor = new HttpLoggingInterceptor(new HttpLogger());
                    okHttpClientSingleInstance = new OkHttpClient.Builder()
                            .connectTimeout(10, TimeUnit.SECONDS)
                            .addNetworkInterceptor(httpInterceptor)
                            .build();
                }
            }
        }
        return okHttpClientSingleInstance;
    }

    public static Call get(String url, Callback callback) {
        OkHttpClient okHttpClient = getInstance();
        Request request = new Request.Builder()
                .url(url(url))
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
        return call;
    }

    public static Call post(String url, Object data, Callback callback) {
        return post(url, Converter.Companion.entity2Map(data), callback);
    }

    public static Call post(String url, Map<String, Object> data, Callback callback) {
        OkHttpClient okHttpClient = getInstance();
        String value = "";
        FormBody.Builder requestBodyBuilder = new FormBody.Builder(Charset.forName("UTF-8"));
        if (data != null) {
            Set<Map.Entry<String, Object>> entries = data.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                requestBodyBuilder.add(entry.getKey(), entry.getValue().toString());
            }
        }

        Request request = new Request.Builder().url(url(url))
                .post(requestBodyBuilder.build())
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
        return call;
    }

    public static Call post(String url, Object header, Object body, Callback callback) {
        return post(url, Converter.Companion.entity2Map(header), Converter.Companion.entity2Map(body), callback);
    }

    public static Call post(String url, Map<String, Object> header, Map<String, Object> body, Callback callback) {
        OkHttpClient okHttpClient = getInstance();
        String value = "";
        FormBody.Builder requestBodyBuilder = new FormBody.Builder(Charset.forName("UTF-8"));
        if (body != null) {
            Set<Map.Entry<String, Object>> entries = body.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                requestBodyBuilder.add(entry.getKey(), entry.getValue().toString());
            }
        }

        Headers.Builder headerBuilder = new Headers.Builder();
        if (header != null) {
            Set<String> keys = header.keySet();
            for (String key : keys) {
                if (header.get(key) != null) {
                    value = header.get(key).toString();
                }
                headerBuilder.add(key, value);
            }
        }

        Request request = new Request.Builder().url(url(url))
                .post(requestBodyBuilder.build())
                .headers(headerBuilder.build())
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
        return call;
    }

    private static String url(String url) {
        return ApiUrl.baseUrl + url;
    }
}
