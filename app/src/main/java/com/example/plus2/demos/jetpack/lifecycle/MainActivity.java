package com.example.plus2.demos.jetpack.lifecycle;

import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.plus2.R;
import com.example.plus2.Utils;
import com.example.plus2.day08.CameraView;
import com.example.plus2.day08.CircleView;
import com.example.plus2.day08.CountryView;
import com.example.plus2.day08.PointView;
import com.example.plus2.day08.ProvinceUtils;
import com.example.plus2.day09.MaterialEditText;
import com.example.plus2.day11.TouchView;
import com.example.plus2.day12.ScalableImageViewActivity;
import com.example.plus2.day13.MultiTouchView1Activity;
import com.example.plus2.day13.MultiTouchView2Activity;
import com.example.plus2.day13.MultiTouchView3Activity;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainLifeObserver observer = new MainLifeObserver();
        getLifecycle().addObserver(observer);
    }
}
