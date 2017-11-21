package com.example.huchenzhang.myutils.yuyin;

import android.Manifest;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import com.baidu.tts.client.SpeechError;
import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.SpeechSynthesizerListener;
import com.baidu.tts.client.TtsMode;
import com.example.huchenzhang.myutils.BaseActivity;
import com.example.huchenzhang.myutils.R;
import com.example.huchenzhang.myutils.databinding.YuYinActivityBinding;

import java.util.ArrayList;

/**
 * 百度语音合成
 * Created by hu_cz on 2017/11/21 15:53.
 */

public class YuYinActivity  extends BaseActivity {
	
	private  YuYinActivityBinding binding;
	private SpeechSynthesizer mSpeechSynthesizer;
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = DataBindingUtil.setContentView(this, R.layout.yu_yin_activity);
		initYuYin();
		initView();
		initPermission();
	}
	
	/**
	 * android 6.0 以上需要动态申请权限
	 */
	private void initPermission() {
		String permissions[] = {
				Manifest.permission.INTERNET,
				Manifest.permission.ACCESS_NETWORK_STATE,
				Manifest.permission.MODIFY_AUDIO_SETTINGS,
				Manifest.permission.WRITE_EXTERNAL_STORAGE,
				Manifest.permission.WRITE_SETTINGS,
				Manifest.permission.READ_PHONE_STATE,
				Manifest.permission.ACCESS_WIFI_STATE,
				Manifest.permission.CHANGE_WIFI_STATE
		};
		ArrayList<String> toApplyList = new ArrayList<String>();
		for (String perm : permissions) {
			if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, perm)) {
				toApplyList.add(perm);
				//进入到这里代表没有权限.
			}
		}
		String tmpList[] = new String[toApplyList.size()];
		if (!toApplyList.isEmpty()) {
			ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), 123);
		}
	}
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		// 此处为android 6.0以上动态授权的回调，用户自行实现。
	}
	
	
	/** 初始化语音 **/
	private void initYuYin(){
		mSpeechSynthesizer = SpeechSynthesizer.getInstance();
		mSpeechSynthesizer.setContext(this); // this 是Context的之类，如Activity
		//listener是SpeechSynthesizerListener 的实现类，需要实现您自己的业务逻辑。SDK合成后会对这个类的方法进行回调。
		mSpeechSynthesizer.setSpeechSynthesizerListener(new SpeechSynthesizerListener() {
			@Override
			public void onSynthesizeStart(String s) {
			//mSpeechSynthesizer.synthesize()合成开始
			}
			
			@Override
			public void onSynthesizeDataArrived(String s, byte[] bytes, int i) {
				//mSpeechSynthesizer.synthesize()合成数据过程中的回调接口，返回合成数据和进度，分多次回调。
			}
			
			@Override
			public void onSynthesizeFinish(String s) {
			//mSpeechSynthesizer.synthesize()本次合成正常结束状态时，SDK的回调
			}
			
			@Override
			public void onSpeechStart(String s) {
			//mSpeechSynthesizer.speak()播放开始
			}
			
			@Override
			public void onSpeechProgressChanged(String s, int i) {
			//mSpeechSynthesizer.speak()播放中
			}
			
			@Override
			public void onSpeechFinish(String s) {
			//mSpeechSynthesizer.speak()播放结束
			}
			
			@Override
			public void onError(String s, SpeechError speechError) {
				Log.e("语音出错了：","" + speechError.code);
			}
		});
		
		String AppId = "10413068";
		String AppKey = "IhCkHtwpXYX2ZkT0e0lwujui";
		String AppSecret = "8e0f9eb8110cb0de9edd1228463e6ef2";
		/*这里只是为了让Demo运行使用的APPID,请替换成自己的id。*/
		mSpeechSynthesizer.setAppId(AppId);
		/*这里只是为了让Demo正常运行使用APIKey,请替换成自己的APIKey*/
		mSpeechSynthesizer.setApiKey(AppKey,AppSecret);
//		mSpeechSynthesizer.auth(TtsMode.ONLINE);  // 纯在线
		mSpeechSynthesizer.auth(TtsMode.MIX); // 离在线混合
		mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, "0"); // 设置发声的人声音，在线生效
		mSpeechSynthesizer.initTts(TtsMode.MIX);
	}
	
	/** 初始化点击事件 **/
	private void initView(){
		//点击播放
		binding.btGo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//判空，防止toString()崩溃
				mSpeechSynthesizer.speak(binding.etGet.getText() == null ? "你好" : binding.etGet.getText().toString());
			}
		});
	}
}
