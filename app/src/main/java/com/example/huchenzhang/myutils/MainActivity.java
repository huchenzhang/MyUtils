package com.example.huchenzhang.myutils;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.example.huchenzhang.myutils.bluetooth.MyBluetoothActivity;
import com.example.huchenzhang.myutils.dataTest.AddPeopleDataActivity;
import com.example.huchenzhang.myutils.databinding.ActivityMainBinding;
import com.example.huchenzhang.myutils.gaoDe.GaoDe;
import com.example.huchenzhang.myutils.kotlin.Test3;
import com.example.huchenzhang.myutils.myDataBinding.MyDataBindingActivity;
import com.example.huchenzhang.myutils.myPoi.PoiActivity;
import com.example.huchenzhang.myutils.myView.MyViewActivity;
import com.example.huchenzhang.myutils.netUtils.NetUtilsActivity;
import com.example.huchenzhang.myutils.recyclerview.MyRecyclerView;
import com.example.huchenzhang.myutils.retrofit.MyRetrofitActivity;
import com.example.huchenzhang.myutils.rxjava.RxJava;
import com.example.huchenzhang.myutils.share.Share;
import com.example.huchenzhang.myutils.svg.Svg;
import com.example.huchenzhang.myutils.swiperefresh.SwipeRefreshActivity;
import com.example.huchenzhang.myutils.updateFile.UpdateActivity;
import com.example.huchenzhang.myutils.xuliehao.XuLieHao;
import com.example.huchenzhang.myutils.yuyin.YuYinActivity;
import com.example.huchenzhang.myutils.zxing.Zxing;

public class MainActivity extends BaseActivity {

	private ActivityMainBinding binding;
	private Intent intent = new Intent();
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

		//svg
		binding.btSvg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				goSvg();
			}
		});

		//share
		binding.btShare.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				goShare();
			}
		});

		//kotlin
		binding.btKotlin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				goKotlin();
			}
		});

		//share
		binding.btZxing.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				goZxing();
			}
		});

		//Retrofit
		binding.btRetrofit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				goRetrofit();
			}
		});

		//高德
		binding.btGaoDe.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				goGaoDe();
			}
		});

		//自定义view
		binding.btView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				goMyView();
			}
		});

		//增加人员数据到三十万
		binding.btAdd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addPeople();
			}
		});

		//文件同步
		binding.btUpdate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				updateFile();
			}
		});

		//蓝牙
		binding.btBluetooth.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				bluetooth();
			}
		});

		//poi
		binding.btPoi.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				poi();
			}
		});
	}

	/***
	 * 网络监测
	 */
	public void goNetWorkActivity() {
		intent.setClass(this, NetUtilsActivity.class);
		startActivity(intent);
	}

	/***
	 * 获取手机序列号及uuid
	 */
	public void getUUid() {
		intent.setClass(this, XuLieHao.class);
		startActivity(intent);
	}

	/***
	 * 上拉下拉刷新
	 */
	public void goSwipeRefresh() {
		intent.setClass(this, SwipeRefreshActivity.class);
		startActivity(intent);
	}

	/***
	 * RecyclerView
	 */
	public void goRecyclerView() {
		intent.setClass(this, MyRecyclerView.class);
		startActivity(intent);
	}


	/***
	 * RxJava
	 */
	public void goRxJavaView() {
		intent.setClass(this, RxJava.class);
		startActivity(intent);
	}


	/***
	 * DataBinding
	 */
	public void goDataBind() {
		intent.setClass(this, MyDataBindingActivity.class);
		startActivity(intent);
	}


	/***
	 * 百度语音合成
	 */
	public void goYuYin() {
		intent.setClass(this, YuYinActivity.class);
		startActivity(intent);
	}


	/***
	 * svg
	 */
	public void goSvg() {
		intent.setClass(this, Svg.class);
		startActivity(intent);
	}


	/***
	 * share
	 */
	public void goShare() {
		intent.setClass(this, Share.class);
		startActivity(intent);
	}


	/***
	 * kotlin
	 */
	public void goKotlin() {
		intent.setClass(this,Test3.class);
		startActivity(intent);
	}


	/***
	 * Zxing
	 */
	public void goZxing() {
		intent.setClass(this, Zxing.class);
		startActivity(intent);
	}

	/***
	 * retrofit
	 */
	public void goRetrofit() {
		intent.setClass(this, MyRetrofitActivity.class);
		startActivity(intent);
	}

	/***
	 * 高德
	 */
	public void goGaoDe() {
		intent.setClass(this, GaoDe.class);
		startActivity(intent);
	}

	/***
	 * 自定义view
	 */
	public void goMyView() {
		intent.setClass(this, MyViewActivity.class);
		startActivity(intent);
	}

	/**
	 * 增加人员数据到三十万
	 */
	public void addPeople(){
		intent.setClass(this, AddPeopleDataActivity.class);
		startActivity(intent);
	}

	/**
	 * 文件同步
	 */
	public void updateFile(){
		intent.setClass(this, UpdateActivity.class);
		startActivity(intent);
	}

	/**
	 * 蓝牙
	 */
	public void bluetooth(){
		intent.setClass(this, MyBluetoothActivity.class);
		startActivity(intent);
	}

	/**
	 * poi
	 */
	public void poi(){
		intent.setClass(this, PoiActivity.class);
		startActivity(intent);
	}
}

	
