package com.example.plus2.demos.mvp_test;

import com.example.plus2.demos.mvp_test.base.BaseModel;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-12-30   10:11
 * desc   :
 */
public class LoginPersenter {

    private BaseModel baseModel;
    Ilogin activity;

    public LoginPersenter(Ilogin activity) {
        activity = this.activity;
    }

    void Login(String trim, String s){
        //todo 登录操作

        activity.loginSuccess();

    }

    public void load() {
        baseModel = new BaseModel();
    }

    interface Ilogin{
        void loginSuccess();
    }
}
