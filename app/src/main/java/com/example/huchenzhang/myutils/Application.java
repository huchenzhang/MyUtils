package com.example.huchenzhang.myutils;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.taobao.sophix.SophixManager;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import org.litepal.LitePal;

import java.lang.reflect.Method;

/**
 * 最先初始化
 * Created by hu on 2017/4/26.
 */

public class Application extends android.app.Application{
	private static Context context;
	
	public void onCreate(){
		super.onCreate();
		MultiDex.install(this);
		LitePal.initialize(this);
		//阿里热修复：queryAndLoadNewPatch不可放在attachBaseContext 中，否则无网络权限，建议放在后面任意时刻，如onCreate中
		//该方法主要用于查询服务器是否有新的可用补丁
		SophixManager.getInstance().queryAndLoadNewPatch();
		Application.context = getApplicationContext();
		
		//友盟初始化
		UMShareAPI.get(this);
		Config.DEBUG = true;
	}
	
	{
		PlatformConfig.setWeixin("","");
		PlatformConfig.setQQZone("1106526385","R5LaY71Uph13LAbc");
		PlatformConfig.setSinaWeibo("","","");
	}
	
	public static Context getAppContext() {
		return Application.context;
	}
	
	/**
	 * 获取序列号
	 */
	private String getSerialNumber() {
		String serial = null;
		try {
			Class<?> c = Class.forName("android.os.SystemProperties");
			Method get = c.getMethod("get", String.class);
			serial = (String) get.invoke(c, "ro.serialno");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serial;
	}
	
}
