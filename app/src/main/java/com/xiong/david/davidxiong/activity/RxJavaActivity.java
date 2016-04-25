package com.xiong.david.davidxiong.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.xiong.david.davidxiong.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
/**
 * Created by xcc on 2016/4/25.
 * RxAndroid请参考RetrofitActivity
 */
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
//        rxJavaFlatMap();
        rxJavaFrom();
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, RxJavaActivity.class);
        context.startActivity(intent);
    }

    private void aysnc() {
        //此asynctask只为了跟axjava对比
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


    /*
    RxJava最核心的两个东西是Observables（被观察者，事件源）和Subscribers（观察者）。
    Observables发出一系列事件，Subscribers处理这些事件。这里的事件可以是任何你感兴趣的东西（触摸事件，web接口调用返回的数据。
    一个Observable可以发出零个或者多个事件，知道结束或者出错。每发出一个事件，就会调用它的Subscriber的onNext方法，
    最后调用Subscriber.onNext()或者Subscriber.onError()结束。Rxjava的看起来很想设计模式中的观察者模式，但是有一点明显不同，
    那就是如果一个Observerble没有任何的的Subscriber，那么这个Observable是不会发出任何事件的。
    Observable的对象可以调用map。flatmap，subscribe等方法，所以得创建一个 Observable对象
     */


    /**
     * 测试AxJava的map功能
     * Func1的<I,O>I,O模版分别为输入和输出值的类型
     * 实现它的call方法后做对应的处理
     * 在subscirbe的call方法里得到你处理过的数据
     */
    private void rxJavaMap() {
        Observable a = Observable.just(5711246);//just是用来创建只发出一个事件就结束的Observable对象。
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
     * flatMap和map感觉就返回值类型不一样，平时使用用map足够了。但flatmap逼格高
     */
    private void rxJavaFlatMap() {
        Observable a = Observable.just(5711246);
        a.flatMap(new Func1<Integer, Observable<String>>() {

            @Override
            public Observable<String> call(Integer integer) {
                // FlatMap返回的是一个Observable对象，供subscrib使用
                return Observable.just(integer + " flatMap");
            }
        }).subscribe(new Action1<String>() {

            @Override
            public void call(String stringObservable) {
                textView.setText(stringObservable);
            }
        });
    }

    private void rxJavaFrom() {
        //form传入数组或容器，会逐个输出对象 flatMap里面的参数为输入类型，输出类型
        Observable.from(getList()).flatMap(new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String s) {
                //flatMap返回的必须是Observable对象
                return Observable.just(getInteger(s));
            }
        })
                .filter(new Func1<String, Boolean>() {
                    //Observable<String>自动会取得Observable里的String？应该是哦
                    @Override
                    public Boolean call(String s) {
                        //当s时two返回true,代表输出
                        return s.length() > 2;
                    }
                })
                .take(9)
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        //在这做输出每一个元素之前你要做的动作，比如保留某一个字段、
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        //当全部数据完成后会调用
                        Log.d("tag","onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("tag",e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d("tag","onNext s="+s);
                        textView.append(s + "  ");
                    }
                });
        /**
         * 如果你想简单，你可以用下替换掉上的subscrible方法
         .Subscriberribe(new Action1<String>() {
        @Override public void call(String s) {
        textView.append(s + "  ");
        }
        }); */
    }

    private String getInteger(String s) {
        Map<String, String> map = new HashMap<>();
        map.put("1", "one");
        map.put("2", "two");
        map.put("3", "three");
        map.put("4", "four");
        map.put("5", "five");
        map.put("6", "six");
        map.put("7", "sevent");
        map.put("8", "eight");
        map.put("9", "nine");
        return map.get(s);
    }

    private List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        return list;
    }
}
