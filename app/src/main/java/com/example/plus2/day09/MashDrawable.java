package com.example.plus2.day09;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.plus2.Utils;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-06-28   15:56
 * desc   : 自定义drawable，网眼
 */
public class MashDrawable extends Drawable {
    private  static  final int INTERVAL = (int) Utils.dp2px(80);
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    {
        paint.setColor(Color.RED);
        paint.setStrokeWidth(Utils.dp2px(2));
    }
    //绘制规则
    @Override
    public void draw(@NonNull Canvas canvas) {
        for (int i = INTERVAL; i < getBounds().right; i+=INTERVAL) {
            for (int j = INTERVAL; j < getBounds().bottom; j+=INTERVAL) {
                //横线
                canvas.drawLine(getBounds().left,j,getBounds().right,j,paint);
                //竖线
                canvas.drawLine(i,getBounds().top,i,getBounds().bottom,paint);
            }
        }
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public int getAlpha() {
        return paint.getAlpha();
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        //完全遮住他下面的view
        //部分
        //完全不
        return paint.getAlpha()==0? PixelFormat.TRANSPARENT:
                paint.getAlpha()==0xff?PixelFormat.OPAQUE:PixelFormat.TRANSLUCENT;
    }
}
