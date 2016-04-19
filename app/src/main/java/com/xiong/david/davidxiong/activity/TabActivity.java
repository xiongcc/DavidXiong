package com.xiong.david.davidxiong.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.xiong.david.davidxiong.R;
import com.xiong.david.davidxiong.base.BaseActivity;
import com.xiong.david.davidxiong.fragment.StudyMainFragment;
import com.xiong.david.davidxiong.view.CommonTitle;
import com.xiong.david.davidxiong.wigdet.AbstractTextTab;
import com.xiong.david.davidxiong.wigdet.TabIndicator;

public class TabActivity extends BaseActivity {
    private String[] titleText = {"我在学", "我已学", "学习任务", "社区活动", "推荐资源"};
    private StudyMainFragment studyMainFragment;
    private TabIndicator tab_top;
    private CommonTitle mTitleBar;
    @Override
    protected int setLayoutId() {
        return R.layout.activity_tab;
    }

    @Override
    protected void initView() {
        tab_top = (TabIndicator) findViewById(R.id.tab_top);
        mTitleBar = (CommonTitle) findViewById(R.id.layout_title);
    }

    @Override
    protected void initData() {
        tab_top.setTab(titleText, false, 18);
        tab_top.setCurrentTab(0);
        tab_top.setTabSelectedListener(new AbstractTextTab.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                Toast.makeText(TabActivity.this, titleText[position] + "", Toast.LENGTH_SHORT).show();
            }
        });
        studyMainFragment = new StudyMainFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.container, studyMainFragment).commit();
    }

    @Override
    protected void initEvent() {

    }

    public static void launch(Context context, String info) {
        Intent intent = new Intent(context, TabActivity.class);
        context.startActivity(intent);
    }
}
