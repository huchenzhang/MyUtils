package com.example.huchenzhang.myutils.retrofit;

import com.example.huchenzhang.myutils.xuliehao.XuLieHao;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
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
    @GET() //@Url 会直接填到@GET()里 ，@Query 会直接添加到url后面
    Call<PhoneBean> getPhoneLocationGet(@Url String url,@Query("appkey") String key , @Query("shouji") String phoneNumber);

    @Headers({"User-Agent:android","apikey:123456789"})
    @POST()//@Field 将参数放入消息体中
    Call<PhoneBean> getPhoneLocationPost(@Url String url ,@Field("shouji") String shouji);
}
