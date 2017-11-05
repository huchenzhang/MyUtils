package com.example.huchenzhang.myutils.netUtils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.example.huchenzhang.myutils.utils.HuToast;
import com.example.huchenzhang.myutils.R;
import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * 网络监测，提供查看网络状态的方法，并且如果网络改变会发送广播
 * 7.0 以后网络监听，电池电量这种隐式意图被禁止了，必须使用动态注册
 * Created by hu on 2017/4/26.
 */

public class NetUtils extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		//获得网络连接服务
		String mAction = intent.getAction();
		if(ConnectivityManager.CONNECTIVITY_ACTION.equals(mAction)){
			ConnectivityManager connManager =
					(ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
			NetworkInfo info = connManager.getActiveNetworkInfo();
			if(info != null && info.isAvailable()){
				String name = info.getTypeName();
				HuToast.show(name,context);
			}else{
				HuToast.show(R.string.NetUtils_offline,context);
			}
		}
	}

	/***
	 * 判断网络是否存活
	 * @param context context
	 * @return boolean
	 */
	public static boolean isNetWorkConnected(Context context){
		if(context != null){
		   ConnectivityManager mConnectivityManager =
				   (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			 NetworkInfo info = mConnectivityManager.getActiveNetworkInfo();
			if(info != null){
				return info.isAvailable();
			}
		}
		return false;
	}


	/***
	 * 判断是否连接wifi
	 * @param context context
	 * @return boolean
	 */
	public static boolean isWifiConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mWiFiNetworkInfo = mConnectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if (mWiFiNetworkInfo != null) {
				return mWiFiNetworkInfo.isConnected();
			}
		}
		return false;
	}
}
