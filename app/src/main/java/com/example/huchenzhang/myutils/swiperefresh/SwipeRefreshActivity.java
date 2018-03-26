package com.example.huchenzhang.myutils.swiperefresh;

import android.os.Bundle;
import com.example.huchenzhang.myutils.BaseActivity;
import com.example.huchenzhang.myutils.R;
import com.example.huchenzhang.myutils.databinding.ActivitySwipeRefreshBinding;
import com.example.huchenzhang.myutils.utils.HuToast;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * 上拉和下拉刷新
 */
public class SwipeRefreshActivity extends BaseActivity<ActivitySwipeRefreshBinding> {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setCountView(this,R.layout.activity_swipe_refresh);
		initView();
	}
	
	/** 加载更多和刷新事件监听 */
	private void initView() {
		//加载更多
		binding.refresh.setOnLoadmoreListener(new OnLoadmoreListener() {
			@Override
			public void onLoadmore(RefreshLayout refreshlayout) {
				HuToast.show("上拉加载更多",SwipeRefreshActivity.this);
				if(binding.refresh.isLoading()){
					binding.refresh.finishLoadmore();
				}
			}
		});
		//刷新
		binding.refresh.setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh(RefreshLayout refreshlayout) {
				HuToast.show("下拉刷新",SwipeRefreshActivity.this);
				if(binding.refresh.isRefreshing()){
					binding.refresh.finishRefresh();
				}
			}
		});
	}
}
