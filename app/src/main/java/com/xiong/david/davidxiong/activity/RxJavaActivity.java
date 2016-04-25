package com.xiong.david.davidxiong.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.xiong.david.davidxiong.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public class RxJavaActivity extends AppCompatActivity {

    private List<String> list = new ArrayList<>();
    @Bind(R.id.textView)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_r);
        ButterKnife.bind(this);
//        addList();
//        aysnc();
//        rxJavaMap();
        rxJavaFlatMap();
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, RxJavaActivity.class);
        context.startActivity(intent);
    }

    private void rxJava() {


    }

    private void aysnc() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                StringBuffer sb = new StringBuffer();
                for (String lis : list) {
                    sb.append(lis + "&");
                }
                return sb.toString();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                textView.setText(s);

            }
        }.execute();

    }

    private void addList() {
        for (int i = 0; i < 5; i++) {
            list.add(i + "");
        }
    }

    /**
     * 测试AxJava的map功能
     * Func1的<I,O>I,O模版分别为输入和输出值的类型
     * 实现它的call方法后做对应的处理
     * 在subscirbe的call方法里得到你处理过的数据
     */
    private void rxJavaMap() {
        Observable a = Observable.just(5711246);
        a.map(new Func1<Integer, String>() {
            @Override
            public String call(Integer s) {
                //map里面直接对参数处理
                return s + " map";
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                textView.setText(s);
            }
        });

    }

    /**
     * 测试AxJava的FlatMap功能
     * Func1的<I,O>I,O模版分别为输入和输出值的类型
     * 实现它的call方法后做对应的处理
     * 在subscirbe的call方法里得到你处理过的数据
     */
    private void rxJavaFlatMap() {
        Observable a = Observable.just(5711246);
        a.flatMap(new Func1<Integer, Observable<String>>() {

            @Override
            public Observable<String> call(Integer integer) {
                // Observable.just(s)在里面对参数进行处理并返回一个Observable
                return Observable.just(integer + " flatMap");
            }
        }).subscribe(new Action1<String>() {

            @Override
            public void call(String stringObservable) {
                textView.setText(stringObservable);
            }
        });
    }
        /*
        flatMap和map感觉就返回值类型不一样，平时使用用map足够了。但flatmap逼格高
         */


}
