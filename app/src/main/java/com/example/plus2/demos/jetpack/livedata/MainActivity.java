package com.example.plus2.demos.jetpack.livedata;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.plus2.R;
import com.example.plus2.databinding.ActivityMainDemosJetpackBinding;
import com.example.plus2.day08.Main3Activity;
import com.example.plus2.demos.handler.PersonalActivity;
import com.example.plus2.demos.jetpack.room.StudentDatabase;
import com.example.plus2.demos.jetpack.viewmodel_databinding.MainViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.bt)
    Button bt;
    @BindView(R.id.bt1)
    Button bt1;
    private ActivityMainDemosJetpackBinding binding;
    private MainViewModel mainViewModel;

    public static MutableLiveData<String> liveData;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
//                            Toast.makeText(MainActivity.this, "111", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, Main3Activity.class));
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demos_livedata);
        ButterKnife.bind(this);


        liveData = new MutableLiveData<>();
        liveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });

        LiveDataBusBeta.getInstance().with(TAG,String.class).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
//                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, Main3Activity.class));
            }
        });
    }

    @OnClick({R.id.bt, R.id.bt1, R.id.bt2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt:
//                        liveData.setValue("111");

                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
//                        liveData.postValue("111");
//                        handler.sendMessage(new Message());
                        LiveDataBusBeta.getInstance().with(TAG,String.class).postValue("666");
                        super.run();
                    }
                }.start();
                break;
            case R.id.bt2:
//                        liveData.setValue("222");
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        liveData.postValue("222");
//                        handler.sendMessage(new Message());
                        super.run();
                    }
                }.start();
                break;
            case R.id.bt1:
                startActivity(new Intent(MainActivity.this, PersonalActivity.class));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LiveDataBusBeta.getInstance().remove(TAG);
    }
}
