package com.example.plus2.demos.mvp2.login;

import com.example.plus2.demos.mvp2.LoginActivity;
import com.example.plus2.demos.mvp2.base.BasePresenter;
import com.example.plus2.demos.mvp2.bean.UserInfo;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2021-01-05   09:56
 * desc   :
 */
public class LoginPresenter extends BasePresenter<LoginActivity,LoginModel,LoginContract.Presenter> {
    @Override
    public LoginContract.Presenter getContract() {
        return new LoginContract.Presenter<UserInfo>() {
            @Override
            public void requestLogin(String name, String pwd) {
                //三种实现方式，p层要么不干活，要么全干，或者让功能模块去实现
                //1.不干活，给model层去实现
                try {
                    m.getContract().executeLogin(name,pwd);
                } catch (Exception e) {
                    e.printStackTrace();
                }

//                //2.p层自己实现（谷歌官方是这种）
//                if("name".equals(name)&&"123".equals(pwd)){
//                    responseResult(new UserInfo("公司","某人"));
//                }else{
//                    responseResult(null);
//                }

                // 3.让功能模块去实现，下载，请求，图片加载
            }

            @Override
            public void responseResult(UserInfo userInfo) {
                //通知view层结果
                getView().getContract().handlerResult(userInfo);
            }
        };
    }

    @Override
    public LoginModel getModel() {
        return new LoginModel(this);
    }
}
