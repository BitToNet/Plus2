package com.example.plus2.day10;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.plus2.R;
import com.example.plus2.Utils;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-06-29   14:01
 * desc   : 布局2：
 * desc   : ---->对自定义view完全进行自定义尺寸计算，重新onMeasure()
 * 随着内部的圆变化的布局
 */
public class CircleView extends View {
    private static final int RADIUS = (int) Utils.dp2px(80);
    private static final int PADDING = (int) Utils.dp2px(30);
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = (PADDING + RADIUS) * 2;
        int height = (PADDING + RADIUS) * 2;

//        int specWidthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int specWidthSize = MeasureSpec.getSize(widthMeasureSpec);
//        switch (specWidthMode) {
//            //1.完全限制
//            case MeasureSpec.EXACTLY:
//                width = specWidthSize;
//                break;
//            //2.设置最大值
//            case MeasureSpec.AT_MOST:
//                if (width > specWidthSize) {
//                    width = specWidthSize;
//                }
//                break;
//            //3.不限制
//            case MeasureSpec.UNSPECIFIED:
//                break;
//        }
        //上面这一套是固定的，所以Android已经提供了这个方法
//        width = resolveSize(width,widthMeasureSpec);
//        height = resolveSize(height,heightMeasureSpec);
        //resolveSizeAndState会多存一个信息：你是否被强行压小了，有些父view会给你换行，使用很少
        width = resolveSizeAndState(width, widthMeasureSpec, 0);
        height = resolveSizeAndState(height, heightMeasureSpec, 0);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.GRAY);
        canvas.drawCircle(PADDING + RADIUS, PADDING + RADIUS, RADIUS, paint);
    }
}
