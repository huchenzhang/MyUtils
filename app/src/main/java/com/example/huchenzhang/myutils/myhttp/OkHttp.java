package com.example.huchenzhang.myutils.myhttp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import com.example.huchenzhang.myutils.BaseActivity;
import com.example.huchenzhang.myutils.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * OkHttp
 * Created by hu on 2017/5/10.
 */

public class OkHttp extends BaseActivity {
	@Bind(R.id.bt_ok_http)
	Button btOkHttp;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ok_http_activity);
		ButterKnife.bind(this);
		initView();
	}

	private void initView() {

	}

	@OnClick(R.id.bt_ok_http)
	public void sendToBaidu(){
		OkHttpUtils.getAsynHttp(this);
	}

}
