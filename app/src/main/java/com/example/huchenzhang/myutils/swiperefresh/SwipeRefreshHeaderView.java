//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.huchenzhang.myutils.swiperefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;

public class SwipeRefreshHeaderView extends TextView implements SwipeRefreshTrigger, SwipeTrigger {
	public SwipeRefreshHeaderView(Context context) {
		this(context,null);
	}

	public SwipeRefreshHeaderView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SwipeRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public void onRefresh() {
		setText("正在拼命加载......");
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
}
