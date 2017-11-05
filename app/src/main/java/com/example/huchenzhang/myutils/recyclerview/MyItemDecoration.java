package com.example.huchenzhang.myutils.recyclerview;


import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.huchenzhang.myutils.R;

/**
 * 自定义item之间的分割线
 * Created by hu on 2017/4/27.
 */

class MyItemDecoration extends RecyclerView.ItemDecoration{

//	private static final int[] ATTRS = new int[]{
//			R.drawable.recycler_view_list_divider
//	};

	private static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
	private static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
	private Drawable mDivider;
	private int mOrientation;

	
	MyItemDecoration(Context context, int orientation) {
//		final TypedArray a = context.obtainStyledAttributes(ATTRS);
//		mDivider = a.getDrawable(0);
		mDivider = context.getResources().getDrawable(R.drawable.recycler_view_list_divider);
//		a.recycle();
		setOrientation(orientation);
	}

	private void setOrientation(int orientation) {
		if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
			throw new IllegalArgumentException( "invalid orientation");
		}
		mOrientation = orientation;
	}

	@Override
	public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
		if(mOrientation == VERTICAL_LIST){
			drawVertical(c,parent);
		}else{
			drawHorizontal(c, parent);
		}
	}

	private void drawHorizontal(Canvas c, RecyclerView parent) {
		final int top = parent.getPaddingTop();
		final int bottom = parent.getHeight() - parent.getPaddingBottom();

		final int childCount = parent.getChildCount();
		for (int i = 0; i < childCount; i++) {
			final View child = parent.getChildAt(i);
			final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
					.getLayoutParams();
			final int left = child.getRight() + params.rightMargin;
			final int right = left + mDivider.getIntrinsicHeight();
			mDivider.setBounds(left, top, right, bottom);
			mDivider.draw(c);
		}
	}


	private void drawVertical(Canvas c, RecyclerView parent){
		final int left = parent.getPaddingLeft();
		final int right = parent.getWidth() - parent.getPaddingRight();

		final int childCount = parent.getChildCount();
		for(int i = 0 ; i< childCount ; i++){
			View child = parent.getChildAt(i);
			RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)child.getLayoutParams();
			int top = child.getBottom() + params.bottomMargin;
			int bottom = top + mDivider.getIntrinsicHeight();
			mDivider.setBounds(left,top,right,bottom);
			mDivider.draw(c);
		}
	}

	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
		if(mOrientation == VERTICAL_LIST){
			outRect.set(0,0,0,mDivider.getIntrinsicHeight());
		}else{
			outRect.set(0,0,mDivider.getIntrinsicWidth(),0);
		}
	}
}
