package com.example.plus2.day25.mvc;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.plus2.R;
import com.example.plus2.day25.data.DataCenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-08-24   09:52
 * desc   :
 */
public class MvcActivity extends AppCompatActivity {
    @BindView(R.id.et1)
    EditText et1;
    @BindView(R.id.et2)
    EditText et2;
    @BindView(R.id.submit)
    Button submit;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc);
        ButterKnife.bind(this);

        String[] data = DataCenter.getData();

        et1.setText(data[0]);
        et1.setText(data[1]);
    }
}
