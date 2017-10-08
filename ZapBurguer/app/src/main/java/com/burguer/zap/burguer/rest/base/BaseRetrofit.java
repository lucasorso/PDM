package com.burguer.zap.burguer.rest.base;

import com.burguer.zap.burguer.properties.WS_PROPERTY;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by LucasOrso on 10/5/17.
 */

public class BaseRetrofit {

    public Retrofit buildRetrofit() {
        Gson lGson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZZ").create();
        HttpLoggingInterceptor lInterceptor = new HttpLoggingInterceptor();
        lInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient lBuild = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(lInterceptor)
                .build();

        return new Retrofit.Builder()
                .baseUrl(WS_PROPERTY.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(lGson))
                .client(lBuild)
                .build();
    }

    public Map<String, String> getParams(Object aRequest) {
        String lJson = new Gson().toJson(aRequest);
        Type lType = new TypeToken<Map<String, String>>() {}.getType();
        return new Gson().fromJson(lJson, lType);
    }
}
