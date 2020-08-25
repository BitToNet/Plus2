package com.example.plus2.day12;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.plus2.Utils;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-08-13   18:01
 * desc   :
 */
public class ScalableImageView3 extends View {
    private static final float IMAGE_WIDTH = Utils.dp2px(300);
    //放大系数
    private static final float OVER_SCALE_FACTOR = 2f;
    Bitmap bitmap;
    private float offsetX;
    private float offsetY;
    float smallScale;
    float bigScale;

    public ScalableImageView3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        bitmap = Utils.getAvatar(getResources(), (int) IMAGE_WIDTH);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        offsetX = (getWidth() - bitmap.getWidth()) / 2f;
        offsetY = (getHeight() - bitmap.getHeight()) / 2f;

        if ((float) bitmap.getHeight() / bitmap.getWidth() > (float) getWidth() / getHeight()) {
            smallScale = (float) getWidth() / bitmap.getWidth();
            bigScale = (float) getHeight() / bitmap.getHeight() * OVER_SCALE_FACTOR;
        } else {
            smallScale = (float) getHeight() / bitmap.getHeight();
            bigScale = (float) getWidth() / bitmap.getWidth() * OVER_SCALE_FACTOR;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        float scale = small
    }
}
