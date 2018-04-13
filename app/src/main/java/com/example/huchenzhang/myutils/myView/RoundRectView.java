package com.example.huchenzhang.myutils.myView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自定义view练习
 * Created by hu on 2018/4/12.
 */

public class RoundRectView extends View {

    private Paint mPaint;
    private int mColor = Color.RED;

    public RoundRectView(Context context) {
        super(context);
        init();
    }

    public RoundRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoundRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public RoundRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setColor(mColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //获取View的宽高
        int width = getWidth();
        int height = getHeight();
        //画圆角矩形
        RectF rectF = new RectF(0, 0, 500, 500);
        canvas.drawRoundRect(rectF,50,50,mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //获取宽的测量模式和大小
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        //获取高的测量模式和大小
        int heighthMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //设置wrap_content的默认宽高值
        //默认宽高的设定并无固定依据，根据需要灵活设置
        //类似TextView，ImageView等针对wrap_content均在onMeasure()对设置默认宽高值有特殊处理
        int mWidth = 400;
        int mHeight = 400;

        //当测量模式是AT_MOST(即wrap_content)时设置默认值
        if(widthMode == MeasureSpec.AT_MOST && heighthMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(mWidth,mHeight);

            // 宽或高其中一个模式为AT_MOST（即wrap_content）时，设置为默认值
        } else if(widthMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(mWidth,heightSize);
        } else if(heighthMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSize,mHeight);
        }

    }
}
