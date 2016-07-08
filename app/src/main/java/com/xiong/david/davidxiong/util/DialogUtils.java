package com.xiong.david.davidxiong.util;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import com.xiong.david.davidxiong.R;


public class DialogUtils {
    public static Dialog createLoadingDialog(Context ctx) {

        Dialog loadingDialog = new Dialog(ctx, R.style.loading_dialog);

        loadingDialog.setContentView(View.inflate(ctx, R.layout.layout_loading, null));

        return loadingDialog;
    }
}
