package com.example.huchenzhang.myutils;

import android.content.Context;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.ndk.CrashlyticsNdk;
import io.fabric.sdk.android.Fabric;

/**
 * 最先初始化
 * Created by hu on 2017/4/26.
 */

public class Application extends android.app.Application{
	private static Context context;
	
	public void onCreate(){
		super.onCreate();
		Fabric.with(this, new Crashlytics(), new CrashlyticsNdk());
		Application.context = getApplicationContext();
	}
	
	public static Context getAppContext() {
		return Application.context;
	}
}
