package com.xiong.david.davidxiong.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xiong.david.davidxiong.R;
import com.xiong.david.davidxiong.base.BaseActivity;
import com.xiong.david.davidxiong.view.CommonTitle;

public class MainActivity extends BaseActivity {

    private String[] titleName = new String[]{"指示器","Retrofit","RxJava"};
    private CommonTitle mTitleBar;
    private ListView mListView;
    private ArrayAdapter<String> mArrayAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mTitleBar = (CommonTitle) findViewById(R.id.layout_title);
        mListView = (ListView) findViewById(R.id.listView);
    }

    @Override
    protected void initData() {
        mTitleBar.setTitleText("首页");
        mArrayAdapter = new ArrayAdapter<>(
                MainActivity.this, android.R.layout.simple_list_item_1,
                titleName);
        //绑定适配器
        mListView.setAdapter(mArrayAdapter);
    }

    @Override
    protected void initEvent() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (titleName[i]){
                    case "指示器":
                        TabActivity.launch(MainActivity.this,"");
                        break;
                    case "Retrofit":
                        RetrofitActivity.launch(MainActivity.this);
                        break;
                    case "RxJava":
                        RxJavaActivity.launch(MainActivity.this);
                        break;
                }
            }
        });
    }

    public static void launch(Context context, String info) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }


}
