package com.example.plus2.day06.test;

import android.animation.TypeEvaluator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.plus2.Utils;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 6/25/21   6:07 PM
 * desc   :
 */
public class TestView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path path = new Path();
    private PathMeasure pathMeasure;

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * 1.先简单画个圆
         * onDraw是为了获取画布
         * 新建画笔，调用canvas的draw方法，这个在hencoder网站上有
         */
//        canvas.drawLine(100,100,200,200,paint);
//        canvas.drawCircle(getWidth()/2,getHeight()/2, Utils.dp2px(150),paint);
        /**
         * 笔画绘制时，哪部分算内部
         * WINDING：螺旋状，重叠的部分是内部，
         * 图上任意一个点，往外发射一条线，如果交点左边发射加一个数，右边减少一个数，如果最终是1，说明这个点在内部
         * EVEN_ODD：做镂空一般用这个，他是算穿过几条线，偶数就算内部
         */
        path.setFillType(Path.FillType.EVEN_ODD);
        canvas.drawPath(path,paint);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        /**
         * 2.如果我要画一个方形，如何定义方向，什么是方向？
         * 新建一个path，重新onSizeChanged
         * 在onDraw中绘制 canvas.drawPath(path,paint);
         *
         * onSizeChanged是每次你做onLayout结束以后，你的实际尺寸改变了，我调用一次
         * 为什么在这里写代码？
         * 第一，保证每次图像尺寸改变后，你重新绘制，保证绘制没问题
         * 第二，不会被过多的调用
         *
         * 方向：
         * CCW：逆时针
         * CW：顺时针
         */
        path.reset();
        path.addRect(getWidth() / 2 - 150, getWidth() / 2 - 300, getWidth() / 2 + 150,
                getHeight() / 2, Path.Direction.CCW);
        path.addCircle(getWidth()/2,getHeight()/2,150, Path.Direction.CCW);

        /**
         * PathMeasure
         * 第二个参数：是否封口
         * 他能做什么？
         * 测量圆的周长
         */
        pathMeasure = new PathMeasure(path, false);
    }
}
