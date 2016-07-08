package com.xiong.david.davidxiong.http;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 文件名：
 * 描  述：
 * 作  者：Created by micw on 16/7/8 09:44
 */
public class RetrofitManagerLater {

    /**
     * HOST地址
     */
    public static final String BASE_URL = "http://www.391k.com";

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


    public static <T> void ioToMainThread(Observable<T> o, Subscriber<T> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    /**
     * 获取PhoneService实例
     *
     * @return
     */
    public static ClientService getService() {

        synchronized (RetrofitManager.class) {
            if (service == null) {
                service = getClientService();
            }

        }
        return service;
    }
}
