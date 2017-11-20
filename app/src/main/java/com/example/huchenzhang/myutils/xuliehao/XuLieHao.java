package com.example.huchenzhang.myutils.xuliehao;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import com.example.huchenzhang.myutils.BaseActivity;
import com.example.huchenzhang.myutils.R;
import com.example.huchenzhang.myutils.databinding.XuLieHaoActivityBinding;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * 序列号，uuid
 * Created by hu on 2017/4/26.
 */

public class XuLieHao extends BaseActivity {
	private static TelephonyManager tm;
	private XuLieHaoActivityBinding binding;
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = DataBindingUtil.setContentView(this,R.layout.xu_lie_hao_activity);
		initView();
	}
	
	private void initView() {
		tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
		binding.number.setText(String.format("序列号 : %s", getSerialNumber()));
		binding.number2.setText(String.format("IMSI : %s", getImsi()));
		binding.number3.setText(String.format("IMEI : %s", getImei()));
		binding.number4.setText(String.format("IMEI SV : %s", getSoftwareVersion()));
		binding.number5.setText(String.format("UUID : %s", getUUID()));
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
	
	/**
	 * 获取IMSI
	 */
	public static String getImsi() {
		@SuppressLint("MissingPermission") String _imsi = tm.getSubscriberId();
		if (_imsi != null && !_imsi.equals("")) {
			return _imsi;
		}
		return "未知";
	}

	/**
	 * 获取IMEI
	 */
	public static String getImei() {
		@SuppressLint("MissingPermission") String _imei = tm.getDeviceId();
		if (_imei != null && !_imei.equals("")) {
			return _imei;
		}
		return "未知";
	}

	/**
	 * 获取软件版本号 IMEI SV
	 */
	public static String getSoftwareVersion() {
		@SuppressLint("MissingPermission") String _imei = tm.getDeviceSoftwareVersion();
		if (_imei != null && !_imei.equals("")) {
			return _imei;
		}
		return "未知";
	}

	/**
	 * 获取UUID
	 */
	public static UUID getUUID() {
		UUID _imei = UUID.randomUUID();
		if (_imei != null && !_imei.equals("")) {
			return _imei;
		}
		return null;
	}
}
