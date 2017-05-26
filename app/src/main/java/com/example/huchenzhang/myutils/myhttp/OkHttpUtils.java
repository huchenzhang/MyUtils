package com.example.huchenzhang.myutils.myhttp;

import android.content.Context;
import android.util.Log;
import java.io.IOException;
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
					Log.i("okHttp",str);
				}else{
					String str = response.networkResponse().toString();
					Log.i("okHttp",str);
				}
			}
		});
	}

}
