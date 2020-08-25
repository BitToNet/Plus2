package com.example.plus2.day25.mvc;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.plus2.R;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-08-24   11:52
 * desc   :
 */
public class DataView extends LinearLayout {
    private EditText et1;
    private EditText et2;

    public DataView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    //在已经加载进这个界面后
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);

    }

    void showData(String data1, String data2) {
        et1.setText(data1);
        et2.setText(data2);
    }
}
