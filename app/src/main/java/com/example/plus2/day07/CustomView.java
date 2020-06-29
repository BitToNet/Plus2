package com.example.plus2.day07;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.plus2.Utils;

import retrofit2.http.PATCH;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-06-18   18:00
 * desc   :
 */
public class CustomView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //裁剪  画圆什么的会有锯齿，不要用这个画圆，他的原理就是这样
        canvas.clipRect(100,100,300,300);
        //反向裁剪
//        canvas.clipOutRect(100,100,300,300);
        canvas.drawBitmap(Utils.getAvatar(getResources(),400),0,0, paint);
    }
}
