package com.example.huchenzhang.myutils.retrofit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.example.huchenzhang.myutils.BaseActivity;
import com.example.huchenzhang.myutils.R;
import com.example.huchenzhang.myutils.databinding.ActivityMyRetrofitBinding;
import com.example.huchenzhang.myutils.utils.HttpUrl;
import com.example.huchenzhang.myutils.utils.HuToast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit练习
 * https://www.jianshu.com/p/308f3c54abdd
 * Created by hu on 2018/3/26.
 */

public class MyRetrofitActivity extends BaseActivity<ActivityMyRetrofitBinding>{
    private ApiService mApi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCountView(this, R.layout.activity_my_retrofit);
        initView();
    }

    /**
     * 初始化view点击事件
     */
    private void initView(){
        binding.bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.ed1 == null || binding.ed1.getText() == null){
                    HuToast.show("填写内容为空",MyRetrofitActivity.this);
                    return;
                }
                //去查询号码归属地
                test1();
            }
        });

    }

    /**.
     * 查询电话号码归属地
     */
    private void test1(){
        //初始化retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HttpUrl.QUERY_PHONE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApi = retrofit.create(ApiService.class);

        //不在主线程进行网络操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                //进行网络请求
                getEnqueue();
            }
        }).start();
    }

    /***
     * 得到结果，请求成功和失败的回调
     */
    private void getEnqueue(){
        //url
       String url = "?appkey=" + getString(R.string.JI_SU_SHU_JU_APPKEY) + "&shouji=" + binding.ed1.getText().toString();
       //去请求
        Call<PhoneBean>  call = mApi.getPhoneLocation(url);
        call.enqueue(new Callback<PhoneBean>() {
            @Override
            public void onResponse(Call<PhoneBean> call, Response<PhoneBean> response) {
                HuToast.show("请求成功",MyRetrofitActivity.this);
                binding.tv1.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<PhoneBean> call, Throwable t) {
                HuToast.show("请求失败",MyRetrofitActivity.this);
            }
        });
    }
}
