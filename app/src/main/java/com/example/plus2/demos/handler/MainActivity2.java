package com.example.plus2.demos.handler;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.target.ViewTarget;
import com.example.plus2.R;

import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * https://www.jianshu.com/p/592fb6bb69fa
 */


public class MainActivity2 extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @BindView(R.id.tv)
    TextView tv;

    private Handler mHandler;
    private Handler subHandler;
    private static final int MSG_SUB_TO_MAIN = 100;
    private static final int MSG_MAIN_TO_SUB = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demos_handler);
        ButterKnife.bind(this);

        HashMap<String, String> stringStringHashMap = new HashMap<>();

        //主线程接收子线程消息
        test1();
        //子线程中接收主线程消息
//        test2();

        ImageView iv = new ImageView(this);
        Glide.with(this).load("").into(iv);

        RequestManager with = Glide.with(this);
        RequestBuilder<Drawable> load = with.load("");
        ViewTarget<ImageView, Drawable> into = load.into(iv);

        ExecutorService pool = Executors.newCachedThreadPool();
        pool.execute(new Runnable() {
            @Override
            public void run() {

            }
        });


    }

    private void test1() {
        //主线程调用
        mHandler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case MSG_SUB_TO_MAIN:
                        // 打印出处理消息的线程名和Message.obj
                        Log.e(TAG, "接收到消息： " + Thread.currentThread().getName() + "," + msg.obj);
                        break;
                    default:
                        break;
                }
            }
        };

        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建一个子线程，在子线程中发送消息
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        Message msg = Message.obtain();
                        msg.what = MSG_SUB_TO_MAIN;
                        msg.obj = "这是一个来自子线程的消息";
                        // 2.发送消息
                        mHandler.sendMessage(msg);
//                        SystemClock.sleep(3000);

                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                tv.setText("--------");
                            }
                        });

                    }

                }).start();
            }
        });
    }

    private void test2() {
        // 创建一个子线程，并在子线程中创建一个Handler，且重写handleMessage
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                subHandler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        // 处理消息
                        switch (msg.what) {
                            case MSG_MAIN_TO_SUB:
                                Log.e(TAG, "接收到消息： " +  Thread.currentThread().getName() + ","+ msg.obj);
                                break;
                            default:
                                break;
                        }
                    }
                };


                Looper.loop();
            }
        }).start();

        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = Message.obtain();
                msg.what = MSG_MAIN_TO_SUB;
                msg.obj = "这是一个来自主线程的消息";
                // 主线程中发送消息
                subHandler.sendMessage(msg);
            }
        });
    }
}
