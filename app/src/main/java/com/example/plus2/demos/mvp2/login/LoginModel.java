package com.example.plus2.demos.mvp2.login;

import com.example.plus2.demos.mvp2.base.BaseModel;
import com.example.plus2.demos.mvp2.bean.UserInfo;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2021-01-05   09:55
 * desc   :
 */
public class LoginModel extends BaseModel<LoginPresenter, LoginContract.Model> {
    public LoginModel(LoginPresenter loginPresenter) {
        super(loginPresenter);
    }

    @Override
    public LoginContract.Model getContract() {
        return new LoginContract.Model() {
            @Override
            public void executeLogin(String name, String password) throws Exception {
               if("name".equals(name)&&"123".equals(password)){
                   p.getContract().responseResult(new UserInfo("公司","某人"));
               }else{
                   p.getContract().responseResult(null);
               }
            }
        };
    }
}
