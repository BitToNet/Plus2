package com.example.plus2.day25.mvvm;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-08-24   14:48
 * desc   :
 */
public class ViewBinder {
    void bind(EditText editText, final ViewModel.TextAttr text) {
        //怎么绑定，监听器，但是text怎么监听呢？包起来，在ViewModel里面
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.equals(text.getText())) {
                    text.setText(s.toString());

                }
            }
        });
        text.setOnChangeListener(new ViewModel.TextAttr.OnChangeListener() {
            @Override
            public void onChange(String newTexr) {
                if (!newTexr.equals(editText.getText().toString())) {
                    editText.setText(newTexr);
                }
                System.out.println("被动改变："+newTexr);
            }
        });

    }
}
