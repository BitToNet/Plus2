package com.example.plus2.day10;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-06-29   14:36
 * desc   : 布局3：自动折行的线性布局
 * desc   : 自定义Layout；重写重新onMeasure()和onLayout()
 */
public class TagLayout extends ViewGroup {
    List<Rect> childrenBounds = new ArrayList<>();

    public TagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthUsed = 0;
        int heightUsed = 0;
        int lineWidthUsed = 0;
        int lineMaxHeight = 0;
        int specWidth = MeasureSpec.getSize(widthMeasureSpec);
        int specMode = MeasureSpec.getMode(widthMeasureSpec);

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);

//            //开发者对我的要求
//            LayoutParams layoutParams = child.getLayoutParams();
//            //父View对我的要求
//            int specWidthMode = MeasureSpec.getMode(widthMeasureSpec);
//            int specWidthSize = MeasureSpec.getSize(widthMeasureSpec);
//            int childWidthMode;
//            int childWidthSize;
//            switch ((layoutParams.width)) {
//                case LayoutParams.MATCH_PARENT:
//                    switch (specWidthMode) {
//                        case MeasureSpec.EXACTLY:
//                        case MeasureSpec.AT_MOST:
//                            childWidthMode = MeasureSpec.EXACTLY;
//                            childWidthSize = specWidthSize - usedWidth;
//                            MeasureSpec.makeMeasureSpec(childWidthSize, childWidthMode);
//                            break;
//                        case MeasureSpec.UNSPECIFIED:
//                            childWidthMode = MeasureSpec.UNSPECIFIED;
//                            childWidthSize = 0;
//                            break;
//
//                    }
//
//            }
//            child.measure(childWidthSpec, childHeightSpec);
            //都是固定的代码，android有这个方法
            measureChildWithMargins(child, widthMeasureSpec, widthUsed, heightMeasureSpec, heightUsed);
            //量完以后看是否已经超线
            if (specMode != MeasureSpec.UNSPECIFIED && lineWidthUsed + child.getMeasuredWidth() > specWidth) {
                //换行
                lineWidthUsed = 0;
                heightUsed += lineMaxHeight;
                lineMaxHeight = 0;
                measureChildWithMargins(child, widthMeasureSpec, widthUsed, heightMeasureSpec, heightUsed);
            }
            Rect childBound;
            if (childrenBounds.size() <= i) {
                childBound = new Rect();
                childrenBounds.add(childBound);
            } else {
                childBound = childrenBounds.get(i);
            }
            childBound.set(lineWidthUsed, heightUsed, lineWidthUsed + child.getMeasuredWidth(), heightUsed + child.getMeasuredHeight());
            lineWidthUsed += child.getMeasuredWidth();
            widthUsed = Math.max(widthUsed, lineWidthUsed);
            lineMaxHeight = Math.max(lineMaxHeight, child.getMeasuredHeight());
        }

        int width = widthUsed;
        int height = heightUsed + lineMaxHeight;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            Rect childBounds = childrenBounds.get(i);
            child.layout(childBounds.left, childBounds.top, childBounds.right, childBounds.bottom);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        //写这个是为了measureChildWithMargins中获取要求
        return new MarginLayoutParams(getContext(), attrs);
    }

}
