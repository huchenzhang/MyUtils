package com.example.huchenzhang.myutils;

import android.content.Context;

/**
 * 最先初始化
 * Created by hu on 2017/4/26.
 */

public class Application extends android.app.Application{
	private static Context context;
	
	public void onCreate(){
		super.onCreate();
		Application.context = getApplicationContext();
	}
	
	public static Context getAppContext() {
		return Application.context;
	}
}
