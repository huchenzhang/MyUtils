package com.example.huchenzhang.myutils.keepalive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

/**
 * 监听锁屏和解锁事件
 * Created by hu_cz on 2017/10/31 17:18.
 */

public class BootCompleteReceiver extends BroadcastReceiver {
	
	@Override
	public void onReceive(Context context, Intent intent) {
		//如果为空return
		if(intent == null || TextUtils.isEmpty(intent.getAction())){
			return;
		}
		//如果锁屏了，就通知activity启动
		if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
			HooliganActivity. startHooligan();
		}
		//如果解锁了，就通知activity关闭，防止还存在抢焦点
		else if(intent.getAction().equals(Intent.ACTION_SCREEN_ON)){
			HooliganActivity. killHooligan();
		}
	}
}

