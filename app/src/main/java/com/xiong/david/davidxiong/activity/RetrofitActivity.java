package com.xiong.david.davidxiong.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.xiong.david.davidxiong.R;
import com.xiong.david.davidxiong.bean.GithubModel;
import com.xiong.david.davidxiong.http.GitHubService;

import retrofit.RestAdapter;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrofitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://7xrflk.com1.z0.glb.clouddn.com")
                .build();
         GitHubService service = restAdapter.create(GitHubService.class);


        Observable<GithubModel> githubModelObservable = service.listRepos("test2");
        githubModelObservable .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GithubModel>() {
                    @Override
                    public void onCompleted() {
                        Log.e("tag","onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("tag",e.getMessage());
                    }

                    @Override
                    public void onNext(GithubModel githubModel) {
                        Log.e("tag",githubModel.id);
                    }
                });

    }
    public static void launch(Context context) {
        Intent intent = new Intent(context, RetrofitActivity.class);
        context.startActivity(intent);
    }
}
