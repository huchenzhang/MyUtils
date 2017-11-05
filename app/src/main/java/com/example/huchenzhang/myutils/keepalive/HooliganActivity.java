package com.example.huchenzhang.myutils.keepalive;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import com.example.huchenzhang.myutils.Application;
import com.example.huchenzhang.myutils.utils.Constants;

/**
 * 一像素点保活
 * Created by hu_cz on 2017/10/31 17:13.
 */

public class HooliganActivity extends Activity{
	private static HooliganActivity instance;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e(Constants.HU_LOG,"------- onCreate");
		instance = this;
		Window window = getWindow();
		window.setGravity(Gravity.START | Gravity.TOP);
		WindowManager.LayoutParams params = window.getAttributes();
		params.x = 0;
		params.y = 0;
		params.height = 100;
		params.width = 100;
		window.setAttributes(params);
	}
	
	/**
	 * 开启保活页面
	 */
	public static void startHooligan() {
		Intent intent = new Intent(Application.getAppContext(), HooliganActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Application.getAppContext().startActivity(intent);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.e(Constants.HU_LOG,"------- onDestroy");
		instance = null;
	}
	
	/**
	 * 关闭保活页面
	 */
	public static void killHooligan() {
		if(instance != null) {
			instance.finish();
		}
	}
}
