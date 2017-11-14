package com.example.huchenzhang.myutils;

import android.content.Context;
import android.support.annotation.Keep;
import android.util.Log;
import android.widget.Toast;

import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixApplication;
import com.taobao.sophix.SophixEntry;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;
/**
 * Sophix入口类，专门用于初始化Sophix，不应包含任何业务逻辑。
 * 此类必须继承自SophixApplication，onCreate方法不需要实现。
 * AndroidManifest中设置application为此类，而SophixEntry中设为原先Application类。
 * 注意原先Application里不需要再重复初始化Sophix，并且需要避免混淆原先Application类。
 * 如有其它自定义改造，请咨询官方后妥善处理。
 */
/**
 * 阿里热修复稳健接入
 * Created by hu_cz on 2017/11/14 17:02.
 */

public class SophixStubApplication extends SophixApplication {
	private final String TAG = "SophixStubApplication";
	// 此处SophixEntry应指定真正的Application，并且保证RealApplicationStub类名不被混淆。
	@Keep
	@SophixEntry(Application.class)
	static class RealApplicationStub {}
	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
//         如果需要使用MultiDex，需要在此处调用。
//         MultiDex.install(this);
		initSophix();
	}
	private void initSophix() {
		String appVersion = "0.0.0";
		try {
			appVersion = this.getPackageManager()
					.getPackageInfo(this.getPackageName(), 0)
					.versionName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		final SophixManager instance = SophixManager.getInstance();
		instance.setContext(this)
				.setAppVersion(appVersion)
				.setSecretMetaData("24692699-1","be248c5fb7f17ae2524ce21313fc0fb5",getResources().getString(R.string.RSASECRET))
				.setEnableDebug(true)
				.setEnableFullLog()
				.setPatchLoadStatusStub(new PatchLoadStatusListener() {
					@Override
					public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
						//mode: 补丁模式, 0:正常请求模式 1:扫码模式 2:本地补丁模式
						//code: 补丁加载状态码, 详情查看PatchStatusCode类说明
						//info: 补丁加载详细说明, 详情查看PatchStatusCode类说明
						//handlePatchVersion: 当前处理的补丁版本号, 0:无 -1:本地补丁 其它:后台补丁
						if (code == PatchStatus.CODE_LOAD_SUCCESS) {
							Log.i(TAG, "sophix load patch success!");
							Toast.makeText(getApplicationContext(),"补丁成功",Toast.LENGTH_SHORT).show();
						} else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
							// 如果需要在后台重启，建议此处用SharePreference保存状态。
							Log.i(TAG, "sophix preload patch success. restart app to make effect.");
							Toast.makeText(getApplicationContext(),"补丁生效需要重启",Toast.LENGTH_SHORT).show();
						}
					}
				}).initialize();
	}
}