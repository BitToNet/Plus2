package com.example.plus2.day10;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.plus2.Utils;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-06-29   11:10
 * desc   : 布局1：
 * desc   : ---->继承已有的view，简单改写他们的尺寸，重新onMeasure()
 * 等宽高的布局
 */
public class SquareImageView extends AppCompatImageView {

    public SquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

//    @Override
//    public void layout(int l, int t, int r, int b) {
//        //如果在layout里面修改布局尺寸，只有自己知道，父view不知道，会导致整体的布局出现紊乱
//        //所以要用onMeasure()
//        super.layout(l, t, r, b);
//    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //拿到onMeasure()里面测得的期望尺寸
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        //getMeasuredWidth()与getWidth()的区别:
        //在layout()调用之前，onMeasure()调用之后，只能拿到getMeasuredWidth()的期望尺寸
        //好处是可以提前拿到，坏处是可能不准
//        getWidth();
//        getHeight();

        int size = Math.max(measuredWidth, measuredHeight);

        //计算完以后存一下才生效，在ImageView中也会调用
        setMeasuredDimension(size,size);
    }
}
