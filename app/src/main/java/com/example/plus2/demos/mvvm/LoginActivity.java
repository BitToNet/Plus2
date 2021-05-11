package com.example.plus2.demos.mvvm;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.plus2.R;
import com.example.plus2.demos.mvp2.base.BaseView;
import com.example.plus2.demos.mvp2.bean.UserInfo;
import com.example.plus2.demos.mvp2.login.LoginContract;
import com.example.plus2.demos.mvp2.login.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.login)
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demos_mvvm);
        ButterKnife.bind(this);

    }


    @OnClick(R.id.login)
    public void onViewClicked() {
        String name = etName.getText().toString();
        String pwd = etPassword.getText().toString();
    }

}
