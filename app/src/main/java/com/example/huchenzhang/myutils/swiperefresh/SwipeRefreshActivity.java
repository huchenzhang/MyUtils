package com.example.huchenzhang.myutils.swiperefresh;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.example.huchenzhang.myutils.BaseActivity;
import com.example.huchenzhang.myutils.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 这个使用有点复杂，换下面这个地址
 * compile 'org.litepal.android:core:1.6.0'
 */
public class SwipeRefreshActivity extends BaseActivity {
	@Bind(R.id.swipe_refresh_header)
	SwipeRefreshHeaderView swipeRefreshHeader;
	@Bind(R.id.swipe_load_more_footer)
	SwipeRefreshFooterView swipeLoadMoreFooter;
	@Bind(R.id.swipe_target)
	TextView swipeTarget;
	@Bind(R.id.swipeToLoad)
	SwipeToLoadLayout swipeToLoad;
	@Bind(R.id.lay_SwipeRefresh)
	RelativeLayout SwipeRefresh;

//	private SwipeRefreshFooterView swipeLoadMoreFooter;
//	private SwipeRefreshHeaderView swipeRefreshHeader;
//	private SwipeToLoadLayout swipeToLoad;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.swipe_refresh_activity);
		ButterKnife.bind(this);
		initView();
	}

	private void initView() {
//		swipeToLoad = (SwipeToLoadLayout) findViewById(R.id.swipeToLoad);
//		swipeLoadMoreFooter = (SwipeRefreshFooterView) findViewById(R.id.swipe_load_more_footer);
//		swipeRefreshHeader = (SwipeRefreshHeaderView) findViewById(R.id.swipe_refresh_header);

		swipeRefreshHeader.setPadding(20, 20, 20, 20);
		swipeRefreshHeader.setLayoutParams(new ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.MATCH_PARENT,
				ViewGroup.MarginLayoutParams.WRAP_CONTENT));
		swipeToLoad.setRefreshHeaderView(swipeRefreshHeader);
		swipeToLoad.setLoadMoreFooterView(swipeLoadMoreFooter);

		swipeToLoad.setOnRefreshListener(new OnRefreshListener() {//下拉刷新
			@Override
			public void onRefresh() {
				swipeToLoad.setRefreshing(false);
			}
		});

		swipeToLoad.setOnLoadMoreListener(new OnLoadMoreListener() {//上拉刷新
			@Override
			public void onLoadMore() {
				swipeToLoad.setLoadingMore(false);
			}
		});
	}
}
