package com.example.huchenzhang.myutils;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.example.huchenzhang.myutils.keepalive.BootCompleteReceiver;
import com.example.huchenzhang.myutils.netUtils.NetUtils;
import butterknife.ButterKnife;

/**
 * 项目中Activity的基类
 * Created by hu on 2017/4/26.
 */

public abstract class BaseActivity extends AppCompatActivity {
	
	private NetUtils mNetUtils = new NetUtils();
	
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//注册网络监听
		registerNet();
		//注册锁屏监听
		registerScreen();
	}
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		//取消注册广播
		unregisterReceiver(mNetUtils);
		//ButterKnife取消注册
		ButterKnife.unbind(this);
	}
	
	
	/**
	 * 注册网络监听
	 */
	private void registerNet(){
		//注册广播分为两种：1.静态注册，在清单文件中注册（常驻广播），程序关闭也能接收到广播
		//<receiver android:name=".receiver.BootCompleteReceiver" >//类名
		// <intent-filter android:priority="1000" >//设置接受广播的优先级
		//    <action android:name="android.net.conn.CONNECTIVITY_CHANGE" />
//                <action android:name="android.net.wifi.WIFI_STATE_CHANGED" />
//                <action android:name="android.net.wifi.STATE_CHANGE" />//设置需要接受那个广播
		// </intent-filter>
		//</receiver>
		
		//注册广播第二种：2.动态注册（非常驻广播），跟注册者生命周期一致
		//过滤器
		IntentFilter filter = new IntentFilter();
		//设置过滤内容
		filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		//设置接受广播的优先级，1-1000
		filter.setPriority(1000);
		registerReceiver(mNetUtils,filter);
	}
	
	
	/**
	 * 监听锁屏和解锁通知，不能静态注册广播，只能动态注册
	 */
	private void registerScreen() {
		BroadcastReceiver broadcastReceiver = new BootCompleteReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_SCREEN_ON);
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		registerReceiver(broadcastReceiver, filter);
	}
	
}
