package com.example.plus2.day25.mvp;

import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.plus2.R;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-08-24   09:52
 * desc   :....
 */
public class MvpActivity extends AppCompatActivity implements Presenter.IView {
    private EditText et1;
    private EditText et2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);

        new Presenter(this).load();
    }

    @Override
    public void showData(String data1, String data2) {
        et1.setText(data1);
        et2.setText(data2);
    }
}
