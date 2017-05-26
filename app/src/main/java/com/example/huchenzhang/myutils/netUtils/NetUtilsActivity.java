package com.example.huchenzhang.myutils.netUtils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import com.example.huchenzhang.myutils.BaseActivity;
import com.example.huchenzhang.myutils.utils.HuToast;
import com.example.huchenzhang.myutils.R;
import java.util.Calendar;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 测试网络的界面
 * Created by hu on 2017/4/26.
 */

public class NetUtilsActivity extends BaseActivity {

	@Bind(R.id.lay_NetUtils)
	LinearLayout layNetUtils;
	@Bind(R.id.DP_DatePicker)
	DatePicker DPDatePicker;

	// 定义5个记录当前时间的变量
	private int year;
	private int month;
	private int day;


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.net_utils_activity);
		ButterKnife.bind(this);
		initValue();
		initListener();
	}

	/***设置控件的监听*/
	private void initListener() {
		DPDatePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
			@Override
			public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
				year = i;
				month = i1;
				day = i2;
				HuToast.show(i + "年" + i1 + "月" + i2 + "日",NetUtilsActivity.this);
			}
		});
	}

	/**初始化为当前时间**/
	private void initValue() {
		Calendar calender = Calendar.getInstance();
		year = calender.get(Calendar.YEAR);
		month = calender.get(Calendar.MONTH);
		day = calender.get(Calendar.DATE);
	}

	/***
	 * 查看是否有网
	 */
	@OnClick(R.id.bt_net1)
	public void bt1Click(){
		if(NetUtils.isNetWorkConnected(this)){
			HuToast.show("有网",this);
		}else{
			HuToast.show("无网",this);
		}
	}

	/***
	 * 查看是否连接wifi
	 */
	@OnClick(R.id.bt_net2)
	public void bt2Click(){
		if(NetUtils.isWifiConnected(this)){
			HuToast.show("连接wifi",this);
		}else{
			HuToast.show("没有连接wifi",this);
		}
	}

}
