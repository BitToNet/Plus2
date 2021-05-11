package com.example.plus2.demos.mvvm.vm;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.plus2.demos.mvvm.model.UserInfo;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2021-01-06   10:24
 * desc   :
 */
public class LoginViewModel {
    public UserInfo userInfo;

    public TextWatcher nameInputListener = new TextWatcher(){
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public TextWatcher pwdInputListener = new TextWatcher(){
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public View.OnClickListener loginClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
}
