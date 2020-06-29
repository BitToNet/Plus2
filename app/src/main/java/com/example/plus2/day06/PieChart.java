package com.example.plus2.day06;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.plus2.Utils;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-06-18   11:14
 * desc   : 扇形
 */
public class PieChart extends View {
    private static final int RADIUS = (int) Utils.dp2px(150);
    private static final int LENGTH = (int) Utils.dp2px(20);
    private static final int PULLED_OUT_INDEX = 2;

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    RectF bounds = new RectF();
    int[] angles = {60, 100, 120, 80};
    int[] colos = {Color.parseColor("#2979FF"),
            Color.parseColor("#C2185B"),
            Color.parseColor("#009688"),
            Color.parseColor("#FF8F00")};

    //只需要创建第二个就可以了，这个是给setContent用的,给系统用的
    public PieChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        bounds.set(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int currentAngle = 0;
        for (int i = 0; i < angles.length; i++) {
            paint.setColor(colos[i]);
            //锁住画布
            canvas.save();
            if (i == PULLED_OUT_INDEX) {
                //移动画布
                canvas.translate((float) Math.cos(Math.toRadians(currentAngle + angles[i] / 2)) * LENGTH,
                        (float) Math.sin(Math.toRadians(currentAngle + angles[i] / 2)) * LENGTH);
            }
            canvas.drawArc(bounds, currentAngle, angles[i], true, paint);
            //还原画布
            canvas.restore();
            currentAngle += angles[i];
        }


    }
}
