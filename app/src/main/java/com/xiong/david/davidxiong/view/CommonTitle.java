package com.xiong.david.davidxiong.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiong.david.davidxiong.R;

/**
 * Created by xcc on 2016/4/19.
 */
public class CommonTitle extends RelativeLayout implements View.OnClickListener {
    public static final int EVENT_BACK_PRESSED = 0x000;
    public static final int EVENT_EDIT_PRESSED = 0x001;
    private TextView mTitle;
    private Button mBtnBack;
    private ImageView mImgRight;

    public CommonTitle(Context context) {
        super(context);
        init(context);
    }

    public CommonTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CommonTitle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    public void onClick(View view) {
        if (view == mBtnBack) {
            if (mCallback != null) {
                mCallback.onButtonClick(EVENT_BACK_PRESSED);
            }
        }
        if (view == mImgRight) {
            if (mCallback != null) {
                mCallback.onButtonClick(EVENT_EDIT_PRESSED);
            }
        }
    }

    public void setTitleText(String text) {
        mTitle.setText(text);
    }

    public void showmImgRight(boolean isShow) {
        mImgRight.setVisibility(isShow ? View.VISIBLE : View.INVISIBLE);
    }

    public void showmBtnBack(boolean isShow) {
        mBtnBack.setVisibility(isShow ? View.VISIBLE : View.INVISIBLE);
    }
    public void shownText(boolean isShow) {
        mTitle.setVisibility(isShow ? View.VISIBLE : View.INVISIBLE);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.common_title, this);
        mTitle = (TextView) findViewById(R.id.text_title);
        mBtnBack = (Button) findViewById(R.id.btn_back);
        mImgRight = (ImageView) findViewById(R.id.img_right);
        mBtnBack.setOnClickListener(this);
        mImgRight.setOnClickListener(this);
    }

    public interface TitleBarCallback {
        void onButtonClick(final int event);
    }

    private TitleBarCallback mCallback;

    public void setOnBtnBackListener(TitleBarCallback mCallback){
        this.mCallback = mCallback;

    }

}
