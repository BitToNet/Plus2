package com.example.plus2.day12;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.OverScroller;

import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;

import com.example.plus2.Utils;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-06-30   14:03
 * desc   : 双向滑动,惯性滑动,定点缩放，多点触控（双指捏撑）
 */
public class ScalableImageView extends View {
    private static final float IMAGE_WIDTH = Utils.dp2px(300);
    //放大系数
    private static final float OVER_SCALE_FACTOR = 2f;

    Bitmap bitmap;
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    float originalOffsetX;
    float originalOffsetY;
    float offsetX;
    float offsetY;
    float smallScale;
    float bigScale;
    boolean big;

    //    float scaleFraction; //0-1f 动画参数
    float currentScale; //1f--> 手指缩放的放大系数不一样，所以改了动画参数
    ObjectAnimator scaleAnimator;

    //android自带的双击监听，这两个都是一样的
//    GestureDetector;
    GestureDetectorCompat detector;

    //惯性滑动
    OverScroller scroller;
    //这个初始速度很慢
//    Scroller scroller;
    MyFlingRunner myFlingRunner = new MyFlingRunner();
    //双指缩放
    ScaleGestureDetector scaleDetector;

    public ScalableImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        bitmap = Utils.getAvatar(getResources(), (int) IMAGE_WIDTH);
        detector = new GestureDetectorCompat(context, new GestureDetector.OnGestureListener() {
            //收到ACTION_DOWN事件以后调用
            //它必须返回true，不然后面的事件都收不到了
            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            //预按下
            //延迟显示，任何触摸100ms后再到达，100ms过后调用他
            @Override
            public void onShowPress(MotionEvent e) {

            }

            //每次按下抬起会有一次响应
            //这些返回值除了onDraw的返回值外，其他都没有用，是用来测试的
            //当长按监听以后，如果手过了500ms抬起，他不会被触发，这是为了长按和单击只有一个被触发
            //当长按关闭以后，不管多久抬起都会算触发：detector.setIsLongpressEnabled(false);
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            //其实就是onMove
            //参数 e1：上一个事件，就是down事件
            //参数 e2：当前事件
            //参数 distanceX：上一个点到当前点的偏移
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                if (big) {
                    float x = (bitmap.getWidth() * bigScale - getWidth()) / 2;
                    float y = (bitmap.getHeight() * bigScale - getHeight()) / 2;

                    offsetX = offsetX - distanceX;
                    offsetY = offsetY - distanceY;
                    //边界判断
                    offsetX = Math.min(x, offsetX);
                    offsetX = Math.max(-x, offsetX);
                    offsetY = Math.min(y, offsetY);
                    offsetY = Math.max(-y, offsetY);
                    invalidate();
                }
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            //惯性滑动，其实就是手指在滑动过程中抬起了
            //参数 e1：上一个事件，就是down事件
            //参数 e2：当前事件
            //参数 velocityX：速度
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (big) {
                    scroller.fling(
                            //初始值
                            (int) offsetX, (int) offsetY,
                            //速度
                            (int) velocityX, (int) velocityY,
                            //边界
                            -(int) (bitmap.getWidth() * bigScale - getWidth()) / 2,
                            (int) (bitmap.getWidth() * bigScale - getWidth()) / 2,
                            -(int) (bitmap.getHeight() * bigScale - getHeight()) / 2,
                            (int) (bitmap.getHeight() * bigScale - getHeight()) / 2,
                            //超出的白框效果
                            100, 100);
//                for (int i = 10; i < 100; i+=10) {
//                    postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            refresh();
//                        }
//                    },i);
//                }

                    //下一帧刷新
                    postOnAnimation(myFlingRunner);
                }


                return false;
            }
        });
        detector.setOnDoubleTapListener(new GestureDetector.OnDoubleTapListener() {
            //单击确认，单击点击300ms后触发
            //当监听了双击以后，单击的触发onSingleTapUp就不能用了
            //必须要用这个方法触发单击，因为如果用上面的方法，单击后直接跳转了，没法判断是单击还是双击
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                return false;
            }

            //双击，两次点击低于300ms，高于40ms（防止手抖）
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                big = !big;
                if (big) {
                    offsetX = (e.getX() - getWidth() / 2f) - (e.getX() - getWidth() / 2f) * bigScale / smallScale;
                    offsetY = (e.getY() - getHeight() / 2f) - (e.getY() - getHeight() / 2f) * bigScale / smallScale;
                    getScaleAnimator().start();
                } else {
                    getScaleAnimator().reverse();

                }
                return false;
            }

            //双击按下后拖动用
            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                return false;
            }
        });

        scroller = new OverScroller(context);
        scaleDetector = new ScaleGestureDetector(context, new ScaleGestureDetector.OnScaleGestureListener() {
            float initialScal;

            //缩放过程中
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                currentScale = initialScal * detector.getScaleFactor();//放大系数
                currentScale = Math.min(currentScale, bigScale);
                currentScale = Math.max(currentScale, smallScale);
                if (currentScale > smallScale) {
                    big = true;
                } else {
                    big = false;

                }
                invalidate();
                return false;
            }

            @Override
            public boolean onScaleBegin(ScaleGestureDetector detector) {
                //缩放开始时，要获取这个放缩事件，返回true
                initialScal = currentScale;
                return true;
            }

            @Override
            public void onScaleEnd(ScaleGestureDetector detector) {

            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        originalOffsetX = ((float) (getWidth() - bitmap.getWidth())) / 2;
        originalOffsetY = ((float) (getHeight() - bitmap.getHeight())) / 2;

        if ((float) bitmap.getHeight() / bitmap.getHeight() > (float) getWidth() / getHeight()) {
            smallScale = (float) getWidth() / bitmap.getWidth();
            bigScale = (float) getHeight() / bitmap.getHeight() * OVER_SCALE_FACTOR;
        } else {
            smallScale = (float) getHeight() / bitmap.getHeight();
            bigScale = (float) getWidth() / bitmap.getWidth() * OVER_SCALE_FACTOR;
        }
        currentScale = smallScale;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float scaleFraction = (currentScale - smallScale) / (bigScale - smallScale);
        canvas.translate(offsetX * scaleFraction, offsetY * scaleFraction);

//        float scale = smallScale + (bigScale - smallScale) * scaleFraction;
        canvas.scale(currentScale, currentScale, getWidth() / 2, getHeight() / 2);
        canvas.drawBitmap(bitmap, originalOffsetX, originalOffsetY, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //使用双击要监听他的事件
//        return detector.onTouchEvent(event)||scaleDetector.onTouchEvent(event);
        getParent().requestDisallowInterceptTouchEvent(true);
        boolean result = scaleDetector.onTouchEvent(event);
        if (!scaleDetector.isInProgress()) {
            result = detector.onTouchEvent(event);

        }
        return result;
    }

//    void refresh(){
//        scroller.computeScrollOffset();
//        offsetX = scroller.getCurrX();
//        offsetY = scroller.getCurrY();
//        invalidate();
//    }

    private ObjectAnimator getScaleAnimator() {
        if (scaleAnimator == null) {
            scaleAnimator = ObjectAnimator.ofFloat(this, "currentScale", 0);
        }
        //这两个值会改变
        scaleAnimator.setFloatValues(smallScale, bigScale);
        return scaleAnimator;
    }

    public float getCurrentScale() {
        return currentScale;
    }

    public void setCurrentScale(float currentScale) {
        this.currentScale = currentScale;
        invalidate();
    }


    private class MyFlingRunner implements Runnable {
        @Override
        public void run() {
//        refresh();
            //这个返回值是动画是否结束
            if (scroller.computeScrollOffset()) {
                offsetX = scroller.getCurrX();
                offsetY = scroller.getCurrY();
                invalidate();
                //下一帧还要执行
                postOnAnimation(this);
                //立刻去主线程执行
//            post(this);
                //自动兼容，旧系统用post，新系统用postOnAnimation
//            ViewCompat.postOnAnimation(this,this);
            }
        }
    }
}
