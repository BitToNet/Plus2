package com.example.plus2.day13;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.plus2.Utils;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_POINTER_DOWN;
import static android.view.MotionEvent.ACTION_POINTER_UP;
import static android.view.MotionEvent.ACTION_UP;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-07-02   09:30
 * desc   : 互不干扰的多点触控
 */
public class MultiTouchView3 extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

//    Path path = new Path();
    //多条路径,SparseArray轻量级array
    SparseArray<Path> paths = new SparseArray<>();


    public MultiTouchView3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(Utils.dp2px(4));
        //两头 圆头
        paint.setStrokeCap(Paint.Cap.ROUND);
        //拐角
        paint.setStrokeJoin(Paint.Join.ROUND);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()){
            case ACTION_DOWN:
            case ACTION_POINTER_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                int actionIndex = event.getActionIndex();
                int pointerId = event.getPointerId(actionIndex);
                Path path = new Path();
                path.moveTo(event.getX(actionIndex),event.getY(actionIndex));
                paths.append(pointerId,path);
                invalidate();
                break;
            case ACTION_MOVE:
                for (int i = 0; i < event.getPointerCount(); i++) {
                    pointerId = event.getPointerId(i);
                    path = paths.get(pointerId);
                    path.lineTo(event.getX(i),event.getY(i));
                }
                invalidate();
                break;
            case ACTION_UP:
            case ACTION_POINTER_UP:
                pointerId = event.getPointerId(event.getActionIndex());
                paths.remove(pointerId);
                invalidate();
                break;

        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < paths.size(); i++) {
            Path path = paths.valueAt(i);
            canvas.drawPath(path,paint);
        }
    }
}
