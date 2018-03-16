package com.example.huchenzhang.myutils.myView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自定义View
 * Created by 15910 on 2018/3/14.
 */

public class RainbowBar extends View{

    //构建Paint时直接加上去锯齿属性
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public RainbowBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth()/2, getHeight()/2, 50, paint);
    }

}
