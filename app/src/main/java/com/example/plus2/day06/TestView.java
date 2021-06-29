package com.example.plus2.day06;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-06-15   16:29
 * desc   : HenCoder Plus 第 6 课 讲义  图形的位置测量及Xfermode的使用
 */
public class TestView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path path = new Path();
    private PathMeasure pathMeasure;

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        path.reset();
        //ccw代表逆时针
        path.addRect(getWidth()/2-150,getHeight()/2-300, getWidth()/2+150, getHeight()/2,Path.Direction.CCW);
        path.addCircle(getWidth()/2,getHeight()/2, 150 ,Path.Direction.CW);
        //forceClosed:是否封口
        pathMeasure = new PathMeasure(path, false);
        //获取周长
//        pathMeasure.getLength();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        //画线
//        canvas.drawLine(100,100,200,200,paint);
//        //画圆
//        canvas.drawCircle(getWidth()/2,getHeight()/2,
//                Utils.dp2px(150),paint );
        path.setFillType(Path.FillType.WINDING);
        canvas.drawPath(path,paint);


    }

}
