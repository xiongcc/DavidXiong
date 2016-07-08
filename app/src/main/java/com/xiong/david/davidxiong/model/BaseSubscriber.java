package com.xiong.david.davidxiong.model;

import android.app.Dialog;
import android.content.Context;
import android.os.Looper;
import android.util.Log;

import com.xiong.david.davidxiong.util.DialogUtils;

import rx.Subscriber;


public class BaseSubscriber<T> extends Subscriber<T> {

    public static boolean isInMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    private Dialog mLoadingDialog;

    private Context dialogContext;

    public BaseSubscriber(Context activityContext) {
        this.dialogContext = activityContext;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (null != this.dialogContext) {



            mLoadingDialog = DialogUtils.createLoadingDialog(this.dialogContext);
            mLoadingDialog.setCancelable(true);
            mLoadingDialog.show();
        }
        Log.i("BaseSubscriber", "onStart inMainThread=" + isInMainThread());
    }

    @Override
    public void onCompleted() {
        if (null != this.mLoadingDialog) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
        Log.d("BaseSubscriber", "====================> onCompleted.");
    }

    @Override
    public void onError(Throwable e) {
        Log.d("BaseSubscriber", "====================> onError");
        e.printStackTrace();
        if (null != this.mLoadingDialog) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }


    @Override
    public void onNext(T t) {
        Log.d("BaseSubscriber", "====================> onNext:" + t);

    }
}
