package com.example.huchenzhang.myutils.retrofit;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * 所有的接口定义
 * Created by hu on 2018/3/26.
 */

public interface ApiService {
    //使用@Headers添加多个请求头
    //post请求（url）
    @Headers({"User-Agent:android","apikey:123456789"})
    @GET() //@Url 会直接填到@POST()里 @Body 是post请求的消息体
    Call<PhoneBean> getPhoneLocation(@Url String url);

//    @Headers({"User-Agent:android","apikey:123456789"})
//    @POST("query")
//    Call<PhoneBean> getId();
}
