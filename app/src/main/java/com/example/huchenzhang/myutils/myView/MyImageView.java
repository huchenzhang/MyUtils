package com.example.huchenzhang.myutils.myView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

/**
 * 自定义view练习
 * 一个带水印的图片
 * Created by hu on 2018/4/12.
 */

public class MyImageView extends android.support.v7.widget.AppCompatImageView{
    private Paint mPaint;

    public MyImageView(Context context) {
        super(context);
        init();
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint();//创建画笔
        mPaint.setColor(Color.RED);//设置画笔颜色
        mPaint.setTextSize(100);//创建字体大小
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画上水印
        canvas.drawText("胡晨璋", 20, getHeight() - 20, mPaint);
    }
}
