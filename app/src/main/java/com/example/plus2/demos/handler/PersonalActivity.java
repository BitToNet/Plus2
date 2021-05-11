package com.example.plus2.demos.handler;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.plus2.R;
import com.example.plus2.demos.jetpack.livedata.LiveDataBusBeta;
import com.example.plus2.demos.jetpack.livedata.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalActivity extends AppCompatActivity {

    @BindView(R.id.bt)
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt)
    public void onViewClicked() {
//        MainActivity.liveData.setValue("555");
        LiveDataBusBeta.getInstance().with("MainActivity",String.class).setValue("666");
    }
}
