package com.example.plus2.demos.mvp_test;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.plus2.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements LoginPersenter.Ilogin {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.login)
    Button login;
    private LoginPersenter loginPersenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demos_mvp2);
        ButterKnife.bind(this);

        loginPersenter = new LoginPersenter(this);
        loginPersenter.load();
    }

    @OnClick(R.id.login)
    public void onViewClicked() {
        loginPersenter.Login(etName.getText().toString().trim(),etPassword.getText().toString().trim());
    }

    @Override
    public void loginSuccess() {

    }
}
