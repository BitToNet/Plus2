package com.example.plus2.day14;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-07-03   15:24
 * desc   : 拖拽和滑动冲突
 */
public class DragHelperGridView extends ViewGroup {

    public DragHelperGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
