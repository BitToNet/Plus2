package com.example.plus2.day11;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-06-29   16:24
 * desc   :
 */
public class TouchView extends View {
    public TouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //常用事件MotionEvent
//        MotionEvent.ACTION_UP;
//        MotionEvent.ACTION_DOWN
//        MotionEvent.ACTION_MOVE
//        MotionEvent.ACTION_CANCEL
//        MotionEvent.ACTION_POINTER_DOWN
//        MotionEvent.ACTION_POINTER_UP

        //这个是老版本的，不包括多点触控
//        event.getAction()
        //第几根手指
//        event.getActionIndex()
        if(event.getActionMasked() == MotionEvent.ACTION_UP){
            //触发点击
            performClick();
        }
        return true;
    }
}
