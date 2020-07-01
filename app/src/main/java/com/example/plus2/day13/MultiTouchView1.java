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
 * desc   : 接力型多点触控
 * desc   : 下一节：多点触控 ：协作型、互不干扰
 */
public class MultiTouchView1 extends View {
    private static final float IMAGE_WIDTH = Utils.dp2px(200);

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;

    float offsetX;
    float offsetY;
    float originalOffsetX;
    float originalOffsetY;
    float downX;
    float downY;

    int trackingPointerId;

    public MultiTouchView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        bitmap = Utils.getAvatar(getResources(), (int) IMAGE_WIDTH);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                //拿到第一根手指的id
                trackingPointerId = event.getPointerId(0);
                //getX()默认为获取第一个手指的坐标，手指抬起坐标改变，但是id不会变
                //但是id会复用，第一个手指抬起，第二个手指的坐标改变为0，但是第一个手指再次落下，id会返还给他，并且第二根手指的坐标再次变为1
                //第一个手指抬起，再有一根手指落下，他会默认这根手指是之前那根
                //event.getY() == event.getY(0)
                downX = event.getX();
                downY = event.getY();
                originalOffsetX = offsetX;
                originalOffsetY = offsetY;
                break;
            case MotionEvent.ACTION_MOVE:
                int index = event.findPointerIndex(trackingPointerId);
                //手指当前位置-手指起始位置+图片初始位置
                offsetX = event.getX(index) - downX + originalOffsetX;
                offsetY = event.getY(index) - downY + originalOffsetY;
                invalidate();
                break;
            //多指支持
            case MotionEvent.ACTION_POINTER_DOWN:
                int actionIndex = event.getActionIndex();
                //新按下手指的id
                trackingPointerId = event.getPointerId(actionIndex);
                downX = event.getX(actionIndex);
                downY = event.getY(actionIndex);
                originalOffsetX = offsetX;
                originalOffsetY = offsetY;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                actionIndex = event.getActionIndex();
                int pointerId = event.getPointerId(actionIndex);
                //抬起的手指是不是正在追踪的手指
                if (pointerId == trackingPointerId) {
                    int newIndex;
                    if (actionIndex == event.getPointerCount() - 1) {
                        //如果抬起的是最后一个手指
                        newIndex = event.getPointerCount() - 2;
                    } else {
                        newIndex = event.getPointerCount() - 1;
                    }
                    trackingPointerId = event.getPointerId(newIndex);
                    downX = event.getX(newIndex);
                    downY = event.getY(newIndex);
                    originalOffsetX = offsetX;
                    originalOffsetY = offsetY;
                }
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(bitmap, offsetX, offsetY, paint);
    }
}
