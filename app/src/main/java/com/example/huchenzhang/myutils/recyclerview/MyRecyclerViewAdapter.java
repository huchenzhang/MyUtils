package com.example.huchenzhang.myutils.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.huchenzhang.myutils.R;
import java.util.List;

/**
 * adapter
 * Created by hu on 2017/4/27.
 */

class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

	private List<String> mDatas;
	private Context mContext;
	private LayoutInflater inflater;
	private OnItemClickListener onItemClickListener;
	private OnItemLongClickListener onItemLongClickListener;

	MyRecyclerViewAdapter(Context context, List<String> Data){
		this.mDatas = Data;
		this.mContext = context;
		inflater = LayoutInflater.from(mContext);
	}

	/***
	 * 重写onCreateViewHolder方法
	 * @param parent ViewGroup
	 * @param viewType viewType
	 * @return 自定义的MyViewHolder对象
	 */
	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = inflater.inflate(R.layout.recycler_view_adapter_item,parent,false);
		return new MyViewHolder(view);
	}

	/***
	 * 填充onCreateViewHolder方法返回的holder中的控件
	 * @param holder 自定义的MyViewHolder对象
	 * @param position 位置
	 */
	@Override
	public void onBindViewHolder(final MyViewHolder holder, final int position) {
		holder.tv.setText(mDatas.get(position));

		if(onItemClickListener != null){
			holder.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view){
					onItemClickListener.onClick(holder.itemView,position);
				}
			});
		}

		if(onItemLongClickListener != null){
			holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View view){
					onItemLongClickListener.onLongClick(holder.itemView,position);
					return true;
				}
			});
		}

	}

	/**
	 * 一共有多少条数据
	 * @return int 共计条数
	 */
	@Override
	public int getItemCount() {
		return mDatas.size();
	}

	/***
	 * 点击事件接口
	 */
	interface OnItemClickListener{
		void onClick(View view,int position);
	}

	/***
	 * 长按事件接口
	 */
	interface OnItemLongClickListener{
		void onLongClick(View view,int position);
	}

	/**
	 * 需要activity去实现该方法
	 * @param onItemClickListener 点击事件
	 */
	void setOnItemClickListener(OnItemClickListener onItemClickListener){
		this.onItemClickListener = onItemClickListener;
	}

	/**
	 * 需要activity去实现该方法
	 * @param onItemLongClickListener 长按事件
	 */
	void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener){
		this.onItemLongClickListener = onItemLongClickListener;
	}

	/**
	 * 重写onCreateViewHolder方法，返回一个自定义的ViewHolder
	 */
	class MyViewHolder extends RecyclerView.ViewHolder{

		TextView tv;
		MyViewHolder(View itemView) {
			super(itemView);
			tv = (TextView)itemView.findViewById(R.id.tv_item);
		}
	}

}
