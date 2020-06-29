package com.example.plus2.day08;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;

import com.example.plus2.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import com.example.plus2.R;

public class Main2Activity extends AppCompatActivity {

    private CircleView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        view = findViewById(R.id.view);
//        view.animate()
//                .translationX(Utils.dp2px(200))
//                .translationY(100)
//                .rotation(180)
//                .alpha(0.5f)
//                .setStartDelay(1000)
//                .start();

        //自定义一个圆心不断变大的属性动画
        //这个propertyName会调用反射去找自定义view里面的get、set方法
        //并且会逐渐把这个值改变为150,所以要在自定义view的set方法里面加invalidate();
        //自定义view里面的radiu名字可以改，但是get、set方法必须要与propertyName对应
        ValueAnimator animator = ObjectAnimator.ofFloat(view,"radius",Utils.dp2px(150));
        animator.setStartDelay(1000);
        animator.start();
        

    }

}
