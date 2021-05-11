package com.example.plus2.demos.mvp2.login;

import com.example.plus2.demos.mvp2.bean.BaseEntity;

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
    interface View<T extends BaseEntity>{
        //真实项目中，请求结果往往是以Javabean
        void handlerResult(T t);
    }
    interface Presenter<T >{
        // 登录请求（接收到view层的指令，可以自己做，也可以让model层实现）
        void requestLogin(String name, String pwd);

        // 结果响应（接收到model层的处理的结果，通知view层刷新）
        void responseResult(T t);
    }
}
