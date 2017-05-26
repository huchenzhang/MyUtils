package com.example.huchenzhang.myutils.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

/**
 * 单例toast
 * 如果第一个Toast还没显示完，则第二个toast取代第一个重新显示
 * Created by hu on 2017/4/26.
 */

public class HuToast {

	private static Toast mToast = null;

	public static void show(String text, Context context){
		if(!TextUtils.isEmpty(text) && context != null){
			showToast(context, text, Toast.LENGTH_SHORT);
		}
	}

	public static void show(final int resId, final Context context) {
		if (context != null) {
			showToast(context, resId, Toast.LENGTH_LONG);
		}
	}

	/***
	 * Toast提示，只显示一个
	 * @param context
	 * @param msg
	 * @param length
	 */
	private static void showToast(Context context, Object msg, int length){
		if(null == mToast){
			if(msg instanceof Integer){
				mToast = Toast.makeText(context,(Integer) msg,length);
			}else{
				mToast = Toast.makeText(context, (String) msg, length);
			}
		}else {
			if (msg instanceof Integer) {
				mToast.setText((Integer) msg);
			} else {
				mToast.setText((String) msg);
			}
		}
		mToast.setDuration(length);
		mToast.setGravity(Gravity.CENTER, 0, 0);
		mToast.show();
	}
}
