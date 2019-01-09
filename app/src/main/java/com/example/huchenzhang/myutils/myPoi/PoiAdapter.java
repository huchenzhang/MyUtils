package com.example.huchenzhang.myutils.myPoi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.huchenzhang.myutils.R;
import java.util.List;

/**
 * Created by hu on 2019/1/9.
 */

public class PoiAdapter extends RecyclerView.Adapter<PoiAdapter.MyViewHolder>{

    private List<String> mDatas;
    private Context mContext;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    PoiAdapter(Context context, List<String> Data){
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
        View view = inflater.inflate(R.layout.item_my_poi,parent,false);
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
                    onItemClickListener.onClick(holder.itemView,mDatas.get(position),position);
                }
            });
        }

        if(onItemLongClickListener != null){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view){
                    onItemLongClickListener.onLongClick(holder.itemView,mDatas.get(position),position);
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
        void onClick(View view,String data,int position);
    }

    /***
     * 长按事件接口
     */
    interface OnItemLongClickListener{
        void onLongClick(View view,String data,int position);
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
            tv = (TextView)itemView.findViewById(R.id.tv_1);
        }
    }

}
