package com.example.huchenzhang.myutils.utils;

import android.text.TextUtils;

import org.jetbrains.annotations.NotNull;

/**
 * 对字符串的一些判断
 * Created by huchenzhang on 2018/3/17.
 */

public class StringUtils {

    /***
     * 二维码是否为微信付款码
     * @param str 扫描二维码得到的字符串
     * @return 是否为微信付款码
     */
    public static boolean WeChatToPay(@NotNull String str){
        //判断是否为空
        if (TextUtils.isEmpty(str)){
            return false;
        }
        //判断是否为纯数字
        boolean result = str.matches("[0-9]+");
        if(!result){
            return false;
        }
        //判断是否为18位
        if(18 != str.length()){
            return false;
        }
        //判断前两位数字是否等于10、11、12、13、14、15
        String str2 = str.substring(0,2);
        int a = Integer.parseInt(str2);
        return 10 <= a && a <= 15;
    }

    /***
     * 二维码是否为支付宝付款码
     * @param str 扫描二维码得到的字符串
     * @return 是否为支付宝付款码
     */
    public static boolean AlipayToPay(@NotNull String str){
        //判断是否为空
        if (TextUtils.isEmpty(str)){
            return false;
        }
        //判断是否为纯数字
        boolean result = str.matches("[0-9]+");
        if(!result){
            return false;
        }
        //判断前两位数字是否等于10、11、12、13、14、15
        String str2 = str.substring(0,2);
        int a = Integer.parseInt(str2);
        return 25 <= a && a <= 30;
    }
}
