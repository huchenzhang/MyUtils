package com.example.huchenzhang.myutils;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import com.example.huchenzhang.myutils.databinding.ActivityMainBinding;
import com.example.huchenzhang.myutils.myDataBinding.MyDataBindingActivity;
import com.example.huchenzhang.myutils.netUtils.NetUtilsActivity;
import com.example.huchenzhang.myutils.recyclerview.MyRecyclerView;
import com.example.huchenzhang.myutils.rxjava.RxJava;
import com.example.huchenzhang.myutils.swiperefresh.SwipeRefreshActivity;
import com.example.huchenzhang.myutils.xuliehao.XuLieHao;
import com.example.huchenzhang.myutils.yuyin.YuYinActivity;

public class MainActivity extends BaseActivity {
	
	private ActivityMainBinding binding;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
		initListener();
	}
	
	/** 初始化各种点击事件*/
	private void initListener(){
		//网络监测
		binding.btNetUtils.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				goNetWorkActivity();
			}
		});
		
		//获取手机序列号及uuid
		binding.btXuLieHao.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getUUid();
			}
		});
		
		//上下拉刷新
		binding.btSwipeRefresh.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				goSwipeRefresh();
			}
		});
		
		//RecyclerView
		binding.btRecyclerView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				goRecyclerView();
			}
		});
		
		//RxJava
		binding.btRxJava.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				goRxJavaView();
			}
		});
		
		//data
		binding.btDataBind.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				goDataBind();
			}
		});
		
		//百度语音合成
		binding.btYuyin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				goYuYin();
			}
		});
		
	}
	
	/***
	 * 网络监测
	 */
	public void goNetWorkActivity() {
		Intent intent = new Intent();
		intent.setClass(this, NetUtilsActivity.class);
		startActivity(intent);
	}
	
	/***
	 * 获取手机序列号及uuid
	 */
	public void getUUid() {
		Intent intent = new Intent();
		intent.setClass(this, XuLieHao.class);
		startActivity(intent);
	}
	
	/***
	 * 上拉下拉刷新
	 */
	public void goSwipeRefresh() {
		Intent intent = new Intent();
		intent.setClass(this, SwipeRefreshActivity.class);
		startActivity(intent);
	}
	
	/***
	 * RecyclerView
	 */
	public void goRecyclerView() {
		Intent intent = new Intent();
		intent.setClass(this, MyRecyclerView.class);
		startActivity(intent);
	}
	
	
	/***
	 * RxJava
	 */
	public void goRxJavaView() {
		Intent intent = new Intent();
		intent.setClass(this, RxJava.class);
		startActivity(intent);
	}
	
	
	/***
	 * DataBinding
	 */
	public void goDataBind() {
		Intent intent = new Intent();
		intent.setClass(this, MyDataBindingActivity.class);
		startActivity(intent);
	}
	
	
	/***
	 * 百度语音合成
	 */
	public void goYuYin() {
		Intent intent = new Intent();
		intent.setClass(this, YuYinActivity.class);
		startActivity(intent);
	}
}

	
