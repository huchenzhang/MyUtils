package com.example.huchenzhang.myutils;

import android.content.Context;
import android.os.Build;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.ndk.CrashlyticsNdk;
import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;

import io.fabric.sdk.android.Fabric;

/**
 * 最先初始化
 * Created by hu on 2017/4/26.
 */

public class Application extends android.app.Application{
	private static Context context;
	
	public void onCreate(){
		super.onCreate();
		//初始化Fabric
		Fabric.with(this, new Crashlytics(), new CrashlyticsNdk());
		//阿里热修复：queryAndLoadNewPatch不可放在attachBaseContext 中，否则无网络权限，建议放在后面任意时刻，如onCreate中
		//该方法主要用于查询服务器是否有新的可用补丁
		SophixManager.getInstance().queryAndLoadNewPatch();
		Application.context = getApplicationContext();
	}
	
	public static Context getAppContext() {
		return Application.context;
	}
}
