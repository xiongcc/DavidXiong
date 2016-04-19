package com.xiong.david.davidxiong.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;

import com.xiong.david.davidxiong.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudyItemFragment extends BaseFragment {


    private EditText mEtSearch;

    public StudyItemFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_study;
    }

    @Override
    protected void initView(View view) {
        mEtSearch = findView(R.id.et_serach);
    }

    @Override
    protected void initData() {
        mEtSearch.setText("您希望今后系统为您推荐那些资源？请填写关键词");
    }

    @Override
    protected void initEvent() {


    }



}
