package com.xiong.david.davidxiong.model;

import android.view.animation.Animation;

/**
 * Created by Administrator on 2016/4/19.
 */
public abstract class EndAnimatorListener implements Animation.AnimationListener {
    @Override
    public void onAnimationStart(Animation animation) {

    }

    public abstract void onAnimationEnd(Animation animation);


    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
