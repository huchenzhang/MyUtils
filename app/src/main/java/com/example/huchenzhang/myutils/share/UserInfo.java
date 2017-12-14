package com.example.huchenzhang.myutils.share;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.design.widget.FloatingActionButton;

import com.example.huchenzhang.myutils.BR;
import com.google.gson.annotations.SerializedName;

/**
 * QQ用户信息
 * Created by 15910 on 2017/12/14.
 */

public class UserInfo extends BaseObservable{
    /** 用户名 */
    @SerializedName("screen_name")
    private String name;

    /** accesstoken */
    @SerializedName("access_token")
    private String accesstoken;

    /** 过期时间 */
    @SerializedName("expires_in")
    private String expiresIn;

    /** 性别 */
    @SerializedName("gender")
    private String gender;

    /** 头像 */
    @SerializedName("iconurl")
    private String iconUrl;

    /** 是否黄钻 */
    @SerializedName("is_yellow_year_vip")
    private String isYellowVip;

    /** 黄钻等级 */
    @SerializedName("yellow_vip_level")
    private String yellowVipLevel;

    /** 城市 */
    @SerializedName("city")
    private String city;

    /** 省份 */
    @SerializedName("province")
    private String province;


    public UserInfo() {
    }

    public UserInfo(String name, String accesstoken, String expiresIn, String gender, String iconUrl, String isYellowVip, String yellowVipLevel, String city, String province) {
        this.name = name;
        this.accesstoken = accesstoken;
        this.expiresIn = expiresIn;
        this.gender = gender;
        this.iconUrl = iconUrl;
        this.isYellowVip = isYellowVip;
        this.yellowVipLevel = yellowVipLevel;
        this.city = city;
        this.province = province;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
        notifyPropertyChanged(BR.accesstoken);
    }

    @Bindable
    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
        notifyPropertyChanged(BR.expiresIn);
    }

    @Bindable
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
        notifyPropertyChanged(BR.gender);
    }

    @Bindable
    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
        notifyPropertyChanged(BR.iconUrl);
    }

    @Bindable
    public String getIsYellowVip() {
        return isYellowVip;
    }

    public void setIsYellowVip(String isYellowVip) {
        this.isYellowVip = isYellowVip;
        notifyPropertyChanged(BR.isYellowVip);
    }

    @Bindable
    public String getYellowVipLevel() {
        return yellowVipLevel;
    }

    public void setYellowVipLevel(String yellowVipLevel) {
        this.yellowVipLevel = yellowVipLevel;
        notifyPropertyChanged(BR.yellowVipLevel);
    }

    @Bindable
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
        notifyPropertyChanged(BR.city);
    }

    @Bindable
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
        notifyPropertyChanged(BR.province);
    }
}
