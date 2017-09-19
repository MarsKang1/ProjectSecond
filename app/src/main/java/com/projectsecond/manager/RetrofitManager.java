package com.projectsecond.manager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/4/5.
 */

public class RetrofitManager {
    private static RetrofitManager Instance = null;
    private Retrofit retrofit;

    private RetrofitManager() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(logging).build();
        retrofit = new Retrofit.Builder().baseUrl("http://apicloud.mob.com/v1/cook/").addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).client(okHttpClient).build();
    }

    public static RetrofitManager getInstance() {
        if (Instance == null) Instance = new RetrofitManager();
        return Instance;
    }

    public <T> T create(Class<T> clazz) {
        return retrofit.create(clazz);
    }
}
