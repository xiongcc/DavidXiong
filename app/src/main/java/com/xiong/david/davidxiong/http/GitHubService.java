package com.xiong.david.davidxiong.http;

import android.support.annotation.NonNull;

import com.xiong.david.davidxiong.bean.GithubModel;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

public interface GitHubService {
    @GET("/{test2}.json")
    Observable<GithubModel> listRepos(@NonNull @Path("test2") String test2);

    /**
     * @param document_id: 文档id，必须
     * @return
     */
//    @GET("/resources/actions/get_document_url")
//    Observable<PPTResourceInfo> getPptInfo(@Query("document_id") String document_id);

}