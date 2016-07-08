package com.xiong.david.davidxiong.http;

import com.xiong.david.davidxiong.bean.PhoneResult;
import com.xiong.david.davidxiong.bean.ZhaZha;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/*
    @Header用来添加Header
    @Query用来添加查询关键字
    @Path路径
 */
public interface ClientService {

    @GET("/apistore/mobilenumber/mobilenumber")
    Call<PhoneResult> getResult(@Header("apikey") String apikey,
                                @Query("phone") String phone);

    @GET("/apistore/mobilenumber/mobilenumber")
    Observable<PhoneResult> getPhoneResult(@Header("apikey") String apikey,
                                           @Query("phone") String phone);


    @GET("/api/xapi.ashx/info.json?key=bd_hyrzjjfb4modhj&size=10&page=1")
    Observable<ZhaZha> getZhaZha();
}