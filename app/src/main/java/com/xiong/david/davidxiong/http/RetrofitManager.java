package com.xiong.david.davidxiong.http;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/4/21.
 */
public class RetrofitManager {

    /** HOST地址*/
    public static final String BASE_URL = "http://apis.baidu.com";
    /**
     * 开发者Key
     */
    public static final String API_KEY = "8e13586b86e4b7f3758ba3bd6c9c9135";

    private static ClientService service;

    public static ClientService getClientService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
         service = retrofit.create(ClientService.class);
        return service;
    }

    /**
     * 获取PhoneService实例
     *
     * @return
     */
    public static ClientService getService() {
        return service == null ? getClientService() : service;
    }
}
