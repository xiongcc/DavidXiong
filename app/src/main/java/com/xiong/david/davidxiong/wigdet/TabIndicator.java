package com.xiong.david.davidxiong.wigdet;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;

/**
 * 指示器，没有绑定viewpager.
 * Created by ChengChangXiong on 2016/4/7.
 */
public class TabIndicator extends AbstractTextTab {
    private int mTitleColor = Color.BLACK;
    private int mTitleColorSelected = Color.RED;

    public TabIndicator(Context context) {
        super(context);
    }

    public TabIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TabIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init(Context context) {
        super.init(context);
    }

    @Override
    protected void addTab(String tabText) {
        TextView textView = new TabText(getContext());
        textView.setGravity(Gravity.CENTER);
        LayoutParams lp = new LayoutParams(0, LayoutParams.MATCH_PARENT);
        lp.weight = 1;
        textView.setText(tabText);
        textView.setTextColor(Color.BLUE);
        addView(textView, lp);
        textView.setOnClickListener(this);
    }


    private class TabText extends TextView implements tagIndicator {
        private Paint paint;
        private int indicatorHeight = 0;

        public TabText(Context context) {
            super(context);
            init(context);
        }

        public TabText(Context context, AttributeSet attrs) {
            super(context, attrs);
            init(context);
        }

        public TabText(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init(context);
        }

        private void init(Context context) {
            paint = new Paint();
            paint.setColor(Color.RED);
            indicatorHeight = (int)dp2px(context, 3);
            setTextSize(textSize);
        }

        @Override
        public void setSelected(boolean selected) {
            super.setSelected(selected);
            setTextColor(selected ? mTitleColorSelected : mTitleColor);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            final int width = getMeasuredWidth();
            final int height = getMeasuredHeight();
            if (isSelected() && marked) {
                canvas.save();
                canvas.drawRect(0, height - indicatorHeight, width, height, paint);
                //drawRect参数分别为左上右下，其实就是矩形区域左上角和右下角坐标
                canvas.restore();
            }
        }
    }

    public interface tagIndicator {

    }
    public static float dp2px(Context context, int dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}
