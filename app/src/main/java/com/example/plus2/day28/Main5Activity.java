package com.example.plus2.day28;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.plus2.R;
import com.example.plus2.day28.butterknife.Bind;
import com.example.plus2.day28.butterknife.Binding;

import com.example.a28_lib_annotations.BindView;


public class Main5Activity extends AppCompatActivity {

    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        Binding.bind(this);
        tv.setText("哈哈哈哈");
    }
}
