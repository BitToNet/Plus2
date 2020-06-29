package com.example.plus2.day08;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.plus2.Utils;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-06-23   11:10
 * desc   :
 */
public class CountryView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    String city = "湖南省";
    public CountryView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint.setTextSize(Utils.dp2px(100));
        paint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawText(city,getWidth()/2,getHeight()/2,paint);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
        invalidate();
    }
}
