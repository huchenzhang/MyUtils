package com.example.huchenzhang.myutils.recyclerview;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.View;

import com.example.huchenzhang.myutils.Application;
import com.example.huchenzhang.myutils.BaseActivity;
import com.example.huchenzhang.myutils.R;
import com.example.huchenzhang.myutils.databinding.ActivityRecyclerViewBinding;
import com.example.huchenzhang.myutils.utils.HuToast;
import com.example.huchenzhang.myutils.utils.SpeechUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * http://blog.csdn.net/skykingf/article/details/50827141
 * MyRecyclerView
 * Created by hu on 2017/4/26.
 */

public class MyRecyclerView extends BaseActivity<ActivityRecyclerViewBinding> {
	private List<String> mDates;//假数据
	private MyRecyclerViewAdapter mAdapter;//适配器

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setCountView(this,R.layout.activity_recycler_view);
		initData();
		initView();
	}

	private void initView() {
		mAdapter = new MyRecyclerViewAdapter(this,mDates);
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		//设置布局管理器
		binding.layRecyclerView.setLayoutManager(layoutManager);
		//设置为垂直布局
		layoutManager.setOrientation(OrientationHelper.VERTICAL);
		//设置adapter
		binding.layRecyclerView.setAdapter(mAdapter);
		//设置增加或删除条目动画
		binding.layRecyclerView.setItemAnimator(new DefaultItemAnimator());
		//设置分割线
		binding.layRecyclerView.addItemDecoration(new MyItemDecoration(this,LinearLayoutManager.VERTICAL));
		//设置点击事件监听
		mAdapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
			@Override
			public void onClick(View view, int position) {
				speech();
				HuToast.show("点击了 "+ position,MyRecyclerView.this);
			}
		});

		//设置长按事件
		mAdapter.setOnItemLongClickListener(new MyRecyclerViewAdapter.OnItemLongClickListener() {
			@Override
			public void onLongClick(View view, int position) {
				HuToast.show("长按了 " + position,MyRecyclerView.this);
			}
		});
	}

	/***
	 * 这是一个自定义通知语音
	 */
	private void speech(){
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
		mBuilder.setSound(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.new_order));
		Notification build = mBuilder.build();
		mNotificationManager.notify(1,build);

	}


	/***
	 * 初始化数据
	 * @return 数据
	 */
	private List<String> initData(){
		mDates = new ArrayList<>();
		for(int i = 0; i < 40 ; i++){
			mDates.add("Android " + i);
		}
		return mDates;
	}
}
