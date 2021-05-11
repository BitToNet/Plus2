package com.example.plus2.demos.mvc;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.plus2.R;
import com.example.plus2.demos.mvc.bean.ImageBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements Callback {

    @BindView(R.id.bt)
    Button bt;
    @BindView(R.id.iv)
    ImageView iv;

    private final static String PATH = "https://pics1.baidu.com/feed/72f082025aafa40f28a4ebf1530f854b78f0192f.jpeg?token=6fbd3dc5aaee14d7a3d266561254ee27&s=6123975508C2D6D6081DE8370300C063";
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case ImageDownloader.SUCCESS: //成功
                    iv.setImageBitmap((Bitmap) msg.obj);
                    break;
                case ImageDownloader.ERROR: //失败
                    Toast.makeText(MainActivity.this, "下载失败", Toast.LENGTH_SHORT).show();
                    break;
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demos);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                SystemClock.sleep(50000);
//            }
//        }).start();
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt)
    public void onViewClicked() {
        ImageBean imageBean = new ImageBean();
        imageBean.setRequestPath(PATH);
        new ImageDownloader().down(this,imageBean);
    }

    @Override
    public void callback(int resultCode, ImageBean imageBean) {
        Message message = handler.obtainMessage(resultCode);
        message.obj = imageBean.getBitmap();
        handler.sendMessageDelayed(message,500);
    }
}
