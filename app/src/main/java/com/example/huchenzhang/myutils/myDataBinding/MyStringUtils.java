package com.example.huchenzhang.myutils.myDataBinding;

import android.text.TextUtils;

/**
 * 工具类
 * Created by user on 2017/5/27.
 */

public class MyStringUtils {
	public static String capitalize(final String s){

		if(!TextUtils.isEmpty(s) && s.length() > 1 ){
			return String.valueOf(s.charAt(0)).toUpperCase()+ s.substring(1);
		}
		return s;
	}
}
