package com.example.huchenzhang.myutils;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;

import com.example.huchenzhang.myutils.databinding.DataBindingActivityBinding;
import com.example.huchenzhang.myutils.myDataBinding.MyDataBindingActivity;
import com.example.huchenzhang.myutils.netUtils.NetUtilsActivity;
import com.example.huchenzhang.myutils.recyclerview.MyRecyclerView;
import com.example.huchenzhang.myutils.rxjava.RxJava;
import com.example.huchenzhang.myutils.swiperefresh.SwipeRefreshActivity;
import com.example.huchenzhang.myutils.xuliehao.XuLieHao;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

	@Bind(R.id.bt_NetUtils)
	Button btNetUtils;
	@Bind(R.id.bt_XuLieHao)
	Button btXuLieHao;
	@Bind(R.id.lay_MainLayout)
	GridLayout layMainLayout;
	@Bind(R.id.bt_SwipeRefresh)
	Button btSwipeRefresh;
	@Bind(R.id.bt_recyclerView)
	Button btRecyclerView;
	@Bind(R.id.bt_rx_java)
	Button btRxJava;
	@Bind(R.id.bt_data_bind)
	Button btDataBind;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
	}

	/***
	 * 网络监测
	 */
	@OnClick(R.id.bt_NetUtils)
	public void goNetWorkActivity() {
		Intent intent = new Intent();
		intent.setClass(this, NetUtilsActivity.class);
		startActivity(intent);
	}

	/***
	 * 获取手机序列号及uuid
	 */
	@OnClick(R.id.bt_XuLieHao)
	public void getUUid() {
		Intent intent = new Intent();
		intent.setClass(this, XuLieHao.class);
		startActivity(intent);
	}

	/***
	 * 网络监测
	 */
	@OnClick(R.id.bt_SwipeRefresh)
	public void goSwipeRefresh() {
		Intent intent = new Intent();
		intent.setClass(this, SwipeRefreshActivity.class);
		startActivity(intent);
	}

	/***
	 * RecyclerView
	 */
	@OnClick(R.id.bt_recyclerView)
	public void goRecyclerView() {
		Intent intent = new Intent();
		intent.setClass(this, MyRecyclerView.class);
		startActivity(intent);
	}


	/***
	 * RecyclerView
	 */
	@OnClick(R.id.bt_rx_java)
	public void goRxJavaView() {
		Intent intent = new Intent();
		intent.setClass(this, RxJava.class);
		startActivity(intent);
	}


	/***
	 * DataBinding
	 */
	@OnClick(R.id.bt_data_bind)
	public void goDataBind() {
		Intent intent = new Intent();
		intent.setClass(this, MyDataBindingActivity.class);
		startActivity(intent);
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		ButterKnife.unbind(this);
	}
}
