package com.xiong.david.davidxiong.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xiong.david.davidxiong.R;
import com.xiong.david.davidxiong.bean.PhoneResult;
import com.xiong.david.davidxiong.http.ClientService;
import com.xiong.david.davidxiong.http.RetrofitManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrofitActivity extends AppCompatActivity {
    @Bind(R.id.phone_view)
    EditText phoneView;
    @Bind(R.id.result_view)
    TextView resultView;
    private  ClientService clientService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        ButterKnife.bind(this);

         clientService = RetrofitManager.getService();
    }
    @OnClick({R.id.query_view, R.id.query_rxjava_view, R.id.duo_shuo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.query_view:
                query();
                break;
            case R.id.query_rxjava_view:
                queryByRxJava();
                break;
            case R.id.duo_shuo:
//                startActivity(new Intent(RetrofitActivity.this, DuoShuoActivity.class));
        }
    }
    private void queryByRxJava() {
        resultView.setText("");
        String number = phoneView.getText().toString();
        if (number.isEmpty()) {
            Toast.makeText(RetrofitActivity.this, "Please input phone number!", Toast.LENGTH_SHORT).show();
            return;
        }
        clientService.getPhoneResult(RetrofitManager.API_KEY, number)
                .subscribeOn(Schedulers.newThread())    //子线程访问网络
                .observeOn(AndroidSchedulers.mainThread())  //回调到主线程
                .subscribe(new Observer<PhoneResult>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(PhoneResult result) {
                            if (result != null && result.getErrNum() == 0) {
                                PhoneResult.RetDataEntity entity = result.getRetData();
                                resultView.append("地址：" + entity.getCity());
                            }
                    }
                });
    }
    private void query() {
        resultView.setText("");
        String number = phoneView.getText().toString();
        if (number.isEmpty()) {
            Toast.makeText(RetrofitActivity.this, "Please input phone number!", Toast.LENGTH_SHORT).show();
            return;
        }
        Call<PhoneResult> call = clientService.getResult(RetrofitManager.API_KEY, number);
        call.enqueue(new Callback<PhoneResult>() {
            @Override
            public void onResponse(Call<PhoneResult> call, Response<PhoneResult> response) {
                if (response.isSuccessful()) {
                    PhoneResult result = response.body();
                    if (result != null && result.getErrNum() == 0) {
                        PhoneResult.RetDataEntity entity = result.getRetData();
                        resultView.append("地址：" + entity.getCity());
                    }
                }
            }

            @Override
            public void onFailure(Call<PhoneResult> call, Throwable t) {

            }
        });
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, RetrofitActivity.class);
        context.startActivity(intent);
    }
}
