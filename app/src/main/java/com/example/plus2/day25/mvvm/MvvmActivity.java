package com.example.plus2.day25.mvvm;

import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.plus2.R;
import com.example.plus2.day25.data.DataCenter;
import com.example.plus2.day25.mvp.Presenter;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-08-24   09:52
 * desc   : 加了数据绑定的mvp
 */
public class MvvmActivity extends AppCompatActivity {
    private EditText et1;
    private EditText et2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);

        new ViewModel(new ViewBinder(),et1,et2).load();

    }

}
