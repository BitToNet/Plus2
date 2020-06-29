package com.example.plus2.day06;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.plus2.Utils;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-06-15   16:29
 * desc   : HenCoder Plus 第 6 课 仪表盘
 */
public class Dashboard extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    //仪表盘下方空白的角度
    private static final int ANGLE = 120;
    private static final float RADIUS = Utils.dp2px(150);
    //指针长度
    private static final float LENGTH = Utils.dp2px(120);
    Path dash = new Path();
    private PathDashPathEffect effect;

    //只写一个大括号，程序会在super后调用，相当于init();
    {
        //画线
        paint.setStyle(Paint.Style.STROKE);
        //粗细
        paint.setStrokeWidth(Utils.dp2px(2));
        //虚线，通过画虚线的方式画出仪表盘
        //获取刻度间距advance
        Path arc = new Path();
        arc.addArc(
                getWidth() / 2 - RADIUS,
                getHeight() / 2 - RADIUS,
                getWidth() / 2 + RADIUS,
                getHeight() / 2 + RADIUS,
                90 + ANGLE / 2,
                360 - ANGLE);
        PathMeasure pathMeasure = new PathMeasure(arc, false);
        //获取弧长度减去最后一个刻度的宽度/20
        float advance = (pathMeasure.getLength() - Utils.dp2px(2)) / 20;
        //添加矩形
        dash.addRect(0, 0, Utils.dp2px(2), Utils.dp2px(10), Path.Direction.CW);
        //advance 两个虚线要空的距离
        //phase 起始距离
        //style 可以看网站hencoder1-2
        effect = new PathDashPathEffect(dash, advance, 0, PathDashPathEffect.Style.ROTATE);

    }

    public Dashboard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画线
        //startAngle:起始角度；sweepAngle:扫过的角度
        //userCenter:是否连连接中心，就是说画扇形还是画弧
        canvas.drawArc(
                getWidth() / 2 - RADIUS,
                getHeight() / 2 - RADIUS,
                getWidth() / 2 + RADIUS,
                getHeight() / 2 + RADIUS,
                90 + ANGLE / 2,
                360 - ANGLE,
                false,
                paint);

        //画刻度
        paint.setPathEffect(effect);
        canvas.drawArc(
                getWidth() / 2 - RADIUS,
                getHeight() / 2 - RADIUS,
                getWidth() / 2 + RADIUS,
                getHeight() / 2 + RADIUS,
                90 + ANGLE / 2,
                360 - ANGLE,
                false,
                paint);
        paint.setPathEffect(null);

        //画指针
        int mark = 5;//当前指向刻度
        canvas.drawLine(getWidth() / 2,
                getHeight() / 2,
                (float) (getWidth() / 2 + Math.cos(Math.toRadians(getAngleFormMark(mark)))* LENGTH),
                (float) (getHeight() / 2 + Math.sin(Math.toRadians(getAngleFormMark(mark)))* LENGTH),
                paint);
    }

    int getAngleFormMark(int mark) {
        return (int) (90 + (float) ANGLE / 2 + (360 - (float) ANGLE) / 20 * mark);

    }
}
