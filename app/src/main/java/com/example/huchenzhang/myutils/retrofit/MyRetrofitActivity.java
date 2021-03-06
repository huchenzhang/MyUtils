package com.example.huchenzhang.myutils.retrofit;

import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.view.View;
import com.example.huchenzhang.myutils.BaseActivity;
import com.example.huchenzhang.myutils.R;
import com.example.huchenzhang.myutils.databinding.ActivityMyRetrofitBinding;
import com.example.huchenzhang.myutils.utils.HttpUrl;
import com.example.huchenzhang.myutils.utils.HuToast;

import java.util.Observable;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
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
        //使用test1时，需要执行initRetrofit()初始化Retrofit，
        //test2时，不需要初始化，在RetrofitClient中已经初始化好了
        //        initRetrofit();
        initView();
    }

    /***
     * 初始化Retrofit
     */
    private void initRetrofit(){
        //初始化retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HttpUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApi = retrofit.create(ApiService.class);
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
//                test1();
                test2();
            }
        });
    }

    /**
     * 测试二：使用rxjava与retrofit结合
     */
    private void test2(){
        RetrofitClient.getInstance().getPhoneLocation(
                HttpUrl.QUERY_PHONE_NUMBER,
                getString(R.string.JI_SU_SHU_JU_APPKEY),
                binding.ed1.getText().toString())
                .subscribeOn(Schedulers.io())//请求数据的事件发生在io线程
                .observeOn(AndroidSchedulers.mainThread())//请求完成后在主线程更新UI
                .subscribe(new Observer<PhoneBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposables.add(d);
                    }

                    @Override
                    public void onNext(PhoneBean phoneBean) {
                        binding.tv1.setText(phoneBean.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        HuToast.show("请求失败",MyRetrofitActivity.this);
                    }

                    @Override
                    public void onComplete() {
                        HuToast.show("请求完成",MyRetrofitActivity.this);
                    }
                });
    }

    /**.
     * 测试一：直接新建线程去请求
     */
    private void test1(){
        //不在主线程进行网络操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                //进行网络请求
                getEnqueue1();
            }
        }).start();
    }

    /***
     * 得到结果，请求成功和失败的回调
     */
    private void getEnqueue1(){
        //url
        //String url = "?appkey=" + getString(R.string.JI_SU_SHU_JU_APPKEY) + "&shouji=" + binding.ed1.getText().toString();
        //http://api.jisuapi.com/shouji/query?appkey=yourappkey&shouji=13456755448
        //去请求
        Call<PhoneBean> call = mApi.getPhoneLocationGet(HttpUrl.QUERY_PHONE_NUMBER,getString(R.string.JI_SU_SHU_JU_APPKEY),binding.ed1.getText().toString());
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
