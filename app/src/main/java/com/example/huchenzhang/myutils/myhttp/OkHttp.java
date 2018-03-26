package com.example.huchenzhang.myutils.myhttp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.example.huchenzhang.myutils.BaseActivity;
import com.example.huchenzhang.myutils.R;
import com.example.huchenzhang.myutils.databinding.ActivityOkHttpBinding;

/**
 * OkHttp
 * Created by hu on 2017/5/10.
 */

public class OkHttp extends BaseActivity<ActivityOkHttpBinding> {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setCountView(this,R.layout.activity_ok_http);
		initView();
	}

	/** 初始化点击事件 **/
	private void initView() {
		//点击发送按钮
		binding.btOkHttp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				sendToBaidu();
			}
		});
	}

	public void sendToBaidu(){
		OkHttpUtils.getAsynHttp(this);
	}

}
