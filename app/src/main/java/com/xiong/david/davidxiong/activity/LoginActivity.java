package com.xiong.david.davidxiong.activity;

import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.xiong.david.davidxiong.R;
import com.xiong.david.davidxiong.base.BaseActivity;
import com.xiong.david.davidxiong.model.EndAnimatorListener;

public class LoginActivity extends BaseActivity {

    private ImageView mImgBackgroud;

    @Override
    protected int setLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏

        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        mImgBackgroud = (ImageView) findViewById(R.id.img_backgroud);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        Animation animation = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.translate_anim);
        mImgBackgroud.startAnimation(animation);
        animation.setAnimationListener(new EndAnimatorListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                MainActivity.launch(LoginActivity.this, "");
            }
        });
    }
}
