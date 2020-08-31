package com.xiong.david.davidxiong.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.xiong.david.davidxiong.R;
import com.xiong.david.davidxiong.wigdet.AbstractTextTab;
import com.xiong.david.davidxiong.wigdet.TabIndicator;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudyMainFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    private ViewPager viewpager;
    private StudyAdapter studyAdapter;
    private StudyItemFragment itemFragment;
    private TabIndicator tab_bottom;
    private String[] bottomText = {"好友分享", "系统推荐"};
    public StudyMainFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_study_main;
    }

    @Override
    protected void initView(View view) {
        viewpager = findView(R.id.viewpager);
        tab_bottom = findView(R.id.tab_bottom);

    }

    @Override
    protected void initData() {
        studyAdapter = new StudyAdapter(getChildFragmentManager());
        viewpager.setAdapter(studyAdapter);
        viewpager.setOnPageChangeListener(this);
    }

    @Override
    protected void initEvent() {
        tab_bottom.setTab(bottomText, true, 20);
        tab_bottom.setCurrentTab(0);
                tab_bottom.setTabSelectedListener(new AbstractTextTab.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
//                Log.e("tag", "position=" + position);
                if (viewpager != null)
                    viewpager.setCurrentItem(position);
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tab_bottom.setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    public void sendData(String[] bottomText) {

//        this.bottomText = bottomText;
    }

    class StudyAdapter extends FragmentPagerAdapter {

        public StudyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            itemFragment = new StudyItemFragment();
            return itemFragment;
        }

        @Override
        public int getCount() {
//            return imgPptUrl != null ? imgPptUrl.size() : 0;
            return bottomText.length;
        }

    }


    public void setViewpager(int position) {
        viewpager.setCurrentItem(position);
    }

//    PagerChangeListener pageListener;

//    public void setOnPagerChangeListener(PagerChangeListener pageListener) {
//        this.pageListener = pageListener;
//    }
//
//    public interface PagerChangeListener {
//        void onItemPosiont(int position);
//    }
//
//    public void refush() {
//        studyAdapter.notifyDataSetChanged();
//        Log.e("tag", "resush" + bottomText.length);
//    }
}
