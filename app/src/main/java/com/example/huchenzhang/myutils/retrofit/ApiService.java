package com.example.huchenzhang.myutils.retrofit;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * 所有的接口定义
 * Created by hu on 2018/3/26.
 * https://www.jianshu.com/p/73216939806a
 * @GET： 表明这是get请求
 * @POST 表明这是post请求
 * @PUT 表明这是put请求
 * @DELETE 表明这是delete请求
 * @PATCH 表明这是一个patch请求，该请求是对put请求的补充，用于更新局部资源
 * @HEAD 表明这是一个head请求
 * @OPTIONS 表明这是一个option请求
 * @HTTP 通用注解, 可以替换以上所有的注解，其拥有三个属性：method，path，hasBody
 * @Headers 用于添加固定请求头，可以同时添加多个。通过该注解添加的请求头不会相互覆盖，而是共同存在
 * @Header 作为方法的参数传入，用于添加不固定值的Header，该注解会更新已有的请求头
 * @Body 多用于post请求发送非表单数据, 比如想要以post方式传递json格式数据
 * @Filed 多用于post请求中表单字段, Filed和FieldMap需要FormUrlEncoded结合使用
 * @FiledMap 和@Filed作用一致，用于不确定表单参数
 * @Part 用于表单字段, Part和PartMap与Multipart注解结合使用, 适合文件上传的情况
 * @PartMap 用于表单字段, 默认接受的类型是Map<String,REquestBody>，可用于实现多文件上传
 * <p>
 * Part标志上文的内容可以是富媒体形势，比如上传一张图片，上传一段音乐，即它多用于字节流传输。
 * 而Filed则相对简单些，通常是字符串键值对。
 * </p>
 * Part标志上文的内容可以是富媒体形势，比如上传一张图片，上传一段音乐，即它多用于字节流传输。
 * 而Filed则相对简单些，通常是字符串键值对。
 * @Path 用于url中的占位符,{占位符}和PATH只用在URL的path部分，url中的参数使用Query和QueryMap代替，保证接口定义的简洁
 * @Query 用于Get中指定参数
 * @QueryMap 和Query使用类似
 * @Url 指定请求路径
 */

public interface ApiService {
    //使用@Headers添加多个请求头
    //post请求（url）
    @Headers({"User-Agent:android","apikey:123456789"})
    @GET() //@Url 会直接填到@GET()里 ，@Query 会直接添加到url后面
    Call<PhoneBean> getPhoneLocationGet(@Url String url,@Query("appkey") String key , @Query("shouji") String phoneNumber);

    @Headers({"User-Agent:android","apikey:123456789"})
    @GET() //@Url 会直接填到@GET()里 ，@Query 会直接添加到url后面
    Observable<PhoneBean> getPhoneLocationGetObservable(@Url String url, @Query("appkey") String key , @Query("shouji") String phoneNumber);

    //post请求必须加上这句 @FormUrlEncoded，不然报错
    @FormUrlEncoded
    @Headers({"User-Agent:android","apikey:123456789"})
    @POST()//@Field 将参数放入消息体中
    Call<PhoneBean> getPhoneLocationPost(@Url String url ,@Field("shouji") String shouji);
}
