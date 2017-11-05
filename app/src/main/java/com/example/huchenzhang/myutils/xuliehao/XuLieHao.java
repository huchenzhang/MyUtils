package com.example.huchenzhang.myutils.xuliehao;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huchenzhang.myutils.BaseActivity;
import com.example.huchenzhang.myutils.R;

import java.lang.reflect.Method;
import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 序列号，uuid
 * Created by hu on 2017/4/26.
 */

public class XuLieHao extends BaseActivity {
	private static TelephonyManager tm;
	@Bind(R.id.number)
	TextView number;
	@Bind(R.id.number2)
	TextView number2;
	@Bind(R.id.number3)
	TextView number3;
	@Bind(R.id.number4)
	TextView number4;
	@Bind(R.id.number5)
	TextView number5;
	@Bind(R.id.lay_xuLieHao)
	RelativeLayout layXuLieHao;
	
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xu_lie_hao_activity);
		ButterKnife.bind(this);
		initView();
	}
	
	private void initView() {
		tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
		number.setText(String.format("序列号 : %s", getSerialNumber()));
		number2.setText(String.format("IMSI : %s", getImsi()));
		number3.setText(String.format("IMEI : %s", getImei()));
		number4.setText(String.format("IMEI SV : %s", getSoftwareVersion()));
		number5.setText(String.format("UUID : %s", getUUID()));
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
		String _imsi = tm.getSubscriberId();
		if (_imsi != null && !_imsi.equals("")) {
			return _imsi;
		}
		return "未知";
	}

	/**
	 * 获取IMEI
	 */
	public static String getImei() {
		String _imei = tm.getDeviceId();
		if (_imei != null && !_imei.equals("")) {
			return _imei;
		}
		return "未知";
	}

	/**
	 * 获取软件版本号 IMEI SV
	 */
	public static String getSoftwareVersion() {
		String _imei = tm.getDeviceSoftwareVersion();
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
