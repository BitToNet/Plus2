package com.example.plus2.day09;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-06-28   15:46
 * desc   : Drawable怎么用
 */
public class DrawableView extends View {
    Drawable drawable;
    public DrawableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        drawable = new MashDrawable();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawable.setBounds(100,100,getWidth(),getHeight());
        drawable.draw(canvas);
    }
}
