package com.example.plus2.day08;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.plus2.Utils;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-06-22   15:07
 * desc   :
 */
public class CircleView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private float radius = Utils.dp2px(50);

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    {
        paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(getWidth()/2,getHeight()/2,radius,paint);
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
        invalidate();
    }
}
