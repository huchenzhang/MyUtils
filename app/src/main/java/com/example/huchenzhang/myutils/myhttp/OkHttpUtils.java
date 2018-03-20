package com.example.huchenzhang.myutils.myhttp;

import android.content.Context;
import android.util.Log;

import com.example.huchenzhang.myutils.BuildConfig;
import com.example.huchenzhang.myutils.utils.Constants;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 实现okHttp
 * Created by hu on 2017/5/10.
 */

public class OkHttpUtils {

	private static OkHttpClient mOkHttpClient;
	private static final String BAI_DU_URL = "http://www.baidu.com";
	private static final String GET = "GET";

	public static void getAsynHttp(final Context context){
		mOkHttpClient = new OkHttpClient();
		//防止被抓包
		if(!BuildConfig.DEBUG){
			mOkHttpClient.newBuilder().proxy(Proxy.NO_PROXY);
		}
		//或如下方法
//		URL url = new URL("www.baidu.com");
//		HttpURLConnection connection = (HttpURLConnection)url.openConnection(Proxy.NO_PROXY);
		Request.Builder requestBuilder = new Request.Builder().url(BAI_DU_URL);
		//此处可以省略，默认是GET请求
		requestBuilder.method(GET,null);
		final Request request = requestBuilder.build();
		Call mCall = mOkHttpClient.newCall(request);
		mCall.enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {

			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				if(null != response.cacheResponse()){
					String str = response.cacheResponse().toString();
					Log.i(Constants.HU_LOG, "OkHttpUtils -----" + str);
				}else{
					String str = response.networkResponse().toString();
					Log.i(Constants.HU_LOG,"OkHttpUtils -----" + str);
				}
			}
		});
	}

}
