package com.example.plus2.demos.handler;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.plus2.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 1.handler内存泄漏测试
 * 2.为什么不能在子线程创建handler
 * 3.textView.setText()只能在主线程执行，这句话是错误！
 * 4.new Handler()两种写法有什么区别？
 * 5.ThreadLocal用法和原理
 */


public class MainActivity extends AppCompatActivity {


    @BindView(R.id.tv)
    TextView tv;

    //第一种写法，传一个回调
    private Handler handler1 = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            startActivity(new Intent(MainActivity.this,PersonalActivity.class));
            return false;
        }
    });

    //第二种写法，空的构造方法，但是要重写handleMessage
    private Handler handler2 = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            tv.setText(msg.obj.toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demos_handler);
        ButterKnife.bind(this);

        test();

//        HandlerThread handlerThread = new HandlerThread("handlerThread");
//        handlerThread.start();
//        Handler handler = new Handler(handlerThread.getLooper()){
//            @Override
//            public void handleMessage(@NonNull Message msg) {
//                super.handleMessage(msg);
//            }
//        };
//        handlerThread.quitSafely();
    }

    private void test() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //常规写法
//                Message message = new Message();
////                message.what=163;
////                message.obj = "Net163";
////                handler2.sendMessage(message);
//
//                //1.Handler内存泄漏测试
                SystemClock.sleep(1000);
//                //休眠的时候，用户退出app，activity被销毁了
//                //然后发消息跳转页面，这个时候activity重启了
////                message.what=3;
//                if(handler1!=null)handler1.sendMessage(message);

//                new Handler();
                tv.setText("666");
//                Toast.makeText(MainActivity.this, "111", Toast.LENGTH_SHORT).show();
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("netease >>>","onDestroy");

        handler1=null;
    }
}
