package com.example.huchenzhang.myutils.netUtils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.DatePicker;
import com.example.huchenzhang.myutils.BaseActivity;
import com.example.huchenzhang.myutils.databinding.ActivityNetUtilsBinding;
import com.example.huchenzhang.myutils.utils.HuToast;
import com.example.huchenzhang.myutils.R;
import java.util.Calendar;

/**
 * 测试网络的界面
 * Created by hu on 2017/4/26.
 */

public class NetUtilsActivity extends BaseActivity<ActivityNetUtilsBinding> {
	// 定义5个记录当前时间的变量
	private int year;
	private int month;
	private int day;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setCountView(this,R.layout.activity_net_utils);
		initValue();
		initListener();
	}
	
	/***设置控件的监听*/
	private void initListener() {
		//时钟
		binding.DPDatePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
			@Override
			public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
				year = i;
				month = i1;
				day = i2;
				HuToast.show(i + "年" + i1 + "月" + i2 + "日",NetUtilsActivity.this);
			}
		});
		
		//bt1
		binding.btNet1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				bt1Click();
			}
		});
		
		//bt2
		binding.btNet2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				bt2Click();
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
	public void bt2Click(){
		if(NetUtils.isWifiConnected(this)){
			HuToast.show("连接wifi",this);
		}else{
			HuToast.show("没有连接wifi",this);
		}
	}
	
}
