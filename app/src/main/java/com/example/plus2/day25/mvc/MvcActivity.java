package com.example.plus2.day25.mvc;

import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.plus2.R;
import com.example.plus2.day25.data.DataCenter;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-08-24   09:52
 * desc   :
 */
public class MvcActivity extends AppCompatActivity{
    DataView dataView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc);

        dataView = findViewById(R.id.dataView);

        String[] data = DataCenter.getData();
        dataView.showData(data[0],data[1]);
    }
}
