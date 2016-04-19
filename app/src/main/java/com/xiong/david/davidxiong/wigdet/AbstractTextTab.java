package com.xiong.david.davidxiong.wigdet;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by ChengChangXiong on 2016/4/7.
 */
public abstract class AbstractTextTab extends LinearLayout implements View.OnClickListener {
    public boolean marked;
    public int textSize = 15;
    public AbstractTextTab(Context context) {
        super(context);
    }

    public AbstractTextTab(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AbstractTextTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onClick(View view) {
        if (view instanceof TabIndicator.tagIndicator) {
            toggleTabClick((TabIndicator.tagIndicator) view);
        }
    }


    private void toggleTabClick(TabIndicator.tagIndicator tab) {
        for (int i = 0; i < getChildCount(); i++) {
            View v = getChildAt(i);
            if (v instanceof TabIndicator.tagIndicator) {
                if (v == tab) {
                    v.setSelected(true);
                    if (mTabSelectedListener != null) {
                        mTabSelectedListener.onTabSelected(i);
                    }
                } else {
                    v.setSelected(false);
                }

            }
        }
    }

    public interface OnTabSelectedListener {
        void onTabSelected(int position);
    }

    private OnTabSelectedListener mTabSelectedListener;

    public void setTabSelectedListener(OnTabSelectedListener listener) {
        this.mTabSelectedListener = listener;
    }


    public void setCurrentTab(int index) {
        for (int i = 0; i < getChildCount(); i++) {
            View v = getChildAt(i);
            if (v instanceof TabIndicator.tagIndicator) {
                v.setSelected(i == index);
            }
        }
    }

    protected void init(Context context) {
        setOrientation(HORIZONTAL);
    }

    protected abstract void addTab(String tabText);

    /**
     * @param list 标题
     * @param marked 是否有下滑指示器
     * @param textSize 字体大小
     */
    public void setTab(String[] list, boolean marked,int textSize) {
        this.marked = marked;
        this.textSize = textSize;
        for (String text : list) {
            addTab(text);
        }
    }

}
