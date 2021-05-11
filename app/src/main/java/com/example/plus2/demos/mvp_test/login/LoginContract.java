package com.example.plus2.demos.mvp_test.login;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-12-30   09:47
 * desc   : 将m/v/p层协商的共同业务，封装成接口。契约、合同
 */
public interface LoginContract {

    interface Model{
        //model层的子类完成方法的具体实现
        void executeLogin(String name, String password)throws Exception;

    }
}
