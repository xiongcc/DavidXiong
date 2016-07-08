package com.xiong.david.davidxiong.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.xiong.david.davidxiong.R;
import com.xiong.david.davidxiong.bean.ZhaZha;
import com.xiong.david.davidxiong.http.RetrofitManagerLater;
import com.xiong.david.davidxiong.model.BaseSubscriber;

import rx.Observable;


/**
 * 后期根据自己的理解更改的Retrofit,个人认为较为实用.
 */

public class RetrofitLaterActicity extends AppCompatActivity {
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_later_acticity);

        mTextView = (TextView) findViewById(R.id.textView);
    }
    public void go(View s) {

        final Observable<ZhaZha> zhaZha = RetrofitManagerLater.getService().getZhaZha();

        RetrofitManagerLater.ioToMainThread(zhaZha, new BaseSubscriber<ZhaZha>(RetrofitLaterActicity.this
        ) {
            @Override
            public void onNext(ZhaZha zhaZha) {
                super.onNext(zhaZha);
                mTextView.setText(zhaZha.toString());
            }
        });

    }
    public static void launch(Context context) {
        Intent intent = new Intent(context, RetrofitLaterActicity.class);
        context.startActivity(intent);
    }
}
