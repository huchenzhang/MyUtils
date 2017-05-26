//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.huchenzhang.myutils.swiperefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeLoadMoreTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;

public class SwipeRefreshFooterView extends TextView implements SwipeLoadMoreTrigger, SwipeTrigger {
	public SwipeRefreshFooterView(Context context) {
		this(context,null);
	}

	public SwipeRefreshFooterView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SwipeRefreshFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}


	public void onPrepare() {
	}

	public void onSwipe(int y, boolean isComplete) {
		setText("释放刷新");
	}

	public void onRelease() {
	}

	public void complete() {
		setText("刷新成功");
	}

	public void onReset() {
	}

	@Override
	public void onLoadMore() {
		setText("正在拼命加载......");
	}
}
