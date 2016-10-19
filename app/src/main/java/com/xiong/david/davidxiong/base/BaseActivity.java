package com.xiong.david.davidxiong.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by xcc on 2016/4/19.
 */
public abstract class BaseActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        initView();
        initData();
        initEvent();
        //inhao a 
    }


    protected abstract int setLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initEvent();

}
