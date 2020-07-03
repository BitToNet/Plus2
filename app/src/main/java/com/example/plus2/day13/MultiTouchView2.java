package com.example.plus2.day13;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.plus2.Utils;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-07-01   14:39
 * desc   : 协作型多点触控
 */
public class MultiTouchView2 extends View {
    private static final float IMAGE_WIDTH = Utils.dp2px(200);

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;

    float offsetX;
    float offsetY;
    float originalOffsetX;
    float originalOffsetY;
    float downX;
    float downY;


    public MultiTouchView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        bitmap = Utils.getAvatar(getResources(), (int) IMAGE_WIDTH);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float sumX = 0;
        float sumY = 0;
        int pointerCount = event.getPointerCount();
        boolean isPointUp = event.getActionMasked() == MotionEvent.ACTION_POINTER_UP;
        for (int i = 0; i < pointerCount; i++) {
            if (!(isPointUp && i == event.getActionIndex())){
                sumX += event.getX(i);
                sumY += event.getY(i);
            }
        }
        if (isPointUp) {
            pointerCount -= 1;

        }
        float focusX = sumX / pointerCount;
        float focusY = sumY / pointerCount;
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
            case MotionEvent.ACTION_POINTER_UP:
                getParent().requestDisallowInterceptTouchEvent(true);
                //getX()默认为获取第一个手指的坐标，手指抬起坐标改变，但是id不会变
                //但是id会复用，第一个手指抬起，第二个手指的坐标改变为0，但是第一个手指再次落下，id会返还给他，并且第二根手指的坐标再次变为1
                //第一个手指抬起，再有一根手指落下，他会默认这根手指是之前那根
                //event.getY() == event.getY(0)
                downX = focusX;
                downY = focusY;
                originalOffsetX = offsetX;
                originalOffsetY = offsetY;
                break;
            case MotionEvent.ACTION_MOVE:
                //手指当前位置-手指起始位置+图片初始位置
                offsetX = focusX - downX + originalOffsetX;
                offsetY = focusY - downY + originalOffsetY;
                invalidate();
                break;
            //多指支持

        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(bitmap, offsetX, offsetY, paint);
    }
}
