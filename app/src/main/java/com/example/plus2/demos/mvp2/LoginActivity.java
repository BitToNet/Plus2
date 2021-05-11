package com.example.plus2.demos.mvp2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.plus2.R;
import com.example.plus2.demos.mvp2.base.BaseView;
import com.example.plus2.demos.mvp2.bean.UserInfo;
import com.example.plus2.demos.mvp2.login.LoginContract;
import com.example.plus2.demos.mvp2.login.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends BaseView<LoginPresenter, LoginContract.View> {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.login)
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demos_mvp2);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.login)
    public void onViewClicked() {
        String name = etName.getText().toString();
        String pwd = etPassword.getText().toString();
        p.getContract().requestLogin(name,pwd);
    }


    @Override
    public LoginContract.View getContract() {
        return new LoginContract.View<UserInfo>() {
            @Override
            public void handlerResult(UserInfo userInfo) {
                if(userInfo!=null){
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    @Override
    public LoginPresenter getPresenter() {
        return new LoginPresenter();
    }
}
