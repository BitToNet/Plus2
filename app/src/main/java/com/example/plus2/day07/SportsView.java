package com.example.plus2.day07;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.plus2.Utils;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-06-17   17:09
 * desc   : 绘制环
 */
public class SportsView extends View {

    private static final int CIRCLE_COLOR = Color.parseColor("#90A4AE");
    private static final int HIGHLIGHT_COLOR = Color.parseColor("#FF4081");
    private static final int RING_WIDTH = (int) Utils.dp2px(20);
    private static final int RADIUS = (int) Utils.dp2px(150);

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Rect rect = new Rect();
    Paint.FontMetrics fontMetrics = new Paint.FontMetrics();

    public SportsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint.setTextSize(Utils.dp2px(100));
        //设置字体
//        paint.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"Quicksand-Regular.ttf"));
        //设置文字居中 x轴方向，横向居中
        paint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制圆环
        paint.setStyle((Paint.Style.STROKE));
        paint.setColor(CIRCLE_COLOR);
        paint.setStrokeWidth(RING_WIDTH);
        canvas.drawCircle(getWidth()/2,getHeight()/2,RADIUS,paint);

        //绘制进度条
        paint.setColor(HIGHLIGHT_COLOR);
        //圆头
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(getWidth()/2-RADIUS,getHeight()/2-RADIUS,getWidth()/2+RADIUS,getHeight()/2+RADIUS,-90,225,false,paint);

        //绘制文字
        paint.setStyle(Paint.Style.FILL);
        //计算偏移,纵向居中
        //方法一：文字会跟随内容变动偏移，做动画时用户会很难受
//        paint.getTextBounds("abab",0,"abab".length(),rect);
//        int offset = (rect.top+rect.bottom)/2;
        //方法二:以b的高度计算偏移，aaa会显得偏下，当文字是固定会显得偏了
        paint.getFontMetrics(fontMetrics);
        float offset = (fontMetrics.ascent+fontMetrics.descent)/2;
        canvas.drawText("abab",getWidth()/2,getHeight()/2-offset,paint);

        //绘制文字2:文字的初始文字不是紧靠左边的
        paint.setTextSize(Utils.dp2px(150));
        paint.setTextAlign(Paint.Align.LEFT);
        paint.getTextBounds("aaa",0,"aaa".length(),rect);
        canvas.drawText("aaa",-rect.left,200,paint);

        //绘制文字3
        paint.setTextSize(Utils.dp2px(15));
        canvas.drawText("aaa",0,200+paint.getFontSpacing(),paint);
    }
}
