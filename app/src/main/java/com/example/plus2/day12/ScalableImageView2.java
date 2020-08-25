package com.example.plus2.day12;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.OverScroller;

import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;

import com.example.plus2.Utils;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-07-01   09:36
 * desc   : 复习
 */
public class ScalableImageView2 extends View implements Runnable {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final float IMAGE_WIDTH = Utils.dp2px(300);

    //放大系数
    private static final float OVER_SCALE_FACTOR = 1.5f;

    Bitmap bitmap;

    float originalOffsetX;
    float originalOffsetY;
    float offsetX;
    float offsetY;
    float smallScale;
    float bigScale;
    boolean big;

    float scaleFraction; //0-1f 动画参数
    ObjectAnimator scaleAnimator;

    //android自带的双击监听，这两个都是一样的
//    GestureDetector;
    GestureDetectorCompat detector;

    //惯性滑动
    OverScroller scroller;
    //这个初始速度很慢
//    Scroller scroller;

    public ScalableImageView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        bitmap = Utils.getAvatar(getResources(), (int) IMAGE_WIDTH);
        detector = new GestureDetectorCompat(getContext(), new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                if (big) {
                    offsetX -= distanceX;
                    offsetY -= distanceY;
                    offsetX = Math.max(offsetX, -(bitmap.getWidth() * bigScale - getWidth()) / 2);
                    offsetX = Math.min(offsetX, (bitmap.getWidth() * bigScale - getWidth()) / 2);
                    offsetY = Math.max(offsetY, -(bitmap.getHeight() * bigScale - getHeight()) / 2);
                    offsetY = Math.min(offsetY, (bitmap.getHeight() * bigScale - getHeight()) / 2);
                    invalidate();
                }

                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                scroller.fling((int)offsetX,(int)offsetY,(int)velocityX,(int)velocityY,
                        -(int)((bitmap.getWidth() * bigScale - getWidth()) / 2),
                        (int)(bitmap.getWidth() * bigScale - getWidth()) / 2,
                        -(int)(bitmap.getHeight() * bigScale - getHeight()) / 2,
                        (int)(bitmap.getHeight() * bigScale - getHeight()) / 2,
                        100, 100
                        );
                postOnAnimation(ScalableImageView2.this);
                return false;
            }
        });

        detector.setOnDoubleTapListener(new GestureDetector.OnDoubleTapListener() {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                big = !big;
                if (big) {
                    getScaleAnimator().start();
                } else {
                    getScaleAnimator().reverse();
                }
                invalidate();
                return false;
            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                return false;
            }
        });

        scroller = new OverScroller(getContext());


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if ((float) getWidth() / bitmap.getWidth() > (float) getHeight() / bitmap.getHeight()) {
            smallScale = (float) getHeight() / bitmap.getHeight();
            bigScale = (float) getWidth() / bitmap.getWidth() * OVER_SCALE_FACTOR;
        } else {
            bigScale = (float) getHeight() / bitmap.getHeight() * OVER_SCALE_FACTOR;
            smallScale = (float) getWidth() / bitmap.getWidth();
        }

        originalOffsetX = (bitmap.getWidth()*bigScale-getWidth())/2;
        originalOffsetY = (bitmap.getHeight()*bigScale-getHeight())/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!big) {
            offsetX = 0;
            offsetY = 0;
        }
        canvas.translate(offsetX, offsetY);

        float scale = smallScale + (bigScale - smallScale) * scaleFraction;

        canvas.scale(scale, scale, getWidth() / 2, getHeight() / 2);

        canvas.drawBitmap(bitmap, (getWidth() - bitmap.getWidth()) / 2, (getHeight() - bitmap.getWidth()) / 2, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }

    public float getScaleFraction() {
        return scaleFraction;
    }

    public void setScaleFraction(float scaleFraction) {
        this.scaleFraction = scaleFraction;
        invalidate();
    }

    private ObjectAnimator getScaleAnimator() {
        if (scaleAnimator == null) {
            scaleAnimator = ObjectAnimator.ofFloat(this, "scaleFraction", 0, 1);
        }
        return scaleAnimator;
    }

    @Override
    public void run() {
        if(scroller.computeScrollOffset()){
            offsetX = scroller.getCurrX();
            offsetY = scroller.getCurrY();
            invalidate();
            postOnAnimation(this);
        }
    }
}
