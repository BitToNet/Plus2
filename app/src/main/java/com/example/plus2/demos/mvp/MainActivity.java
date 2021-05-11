package com.example.plus2.demos.mvp;

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
import com.example.plus2.demos.mvc.Callback;
import com.example.plus2.demos.mvc.ImageDownloader;
import com.example.plus2.demos.mvp.model.ImageBean;
import com.example.plus2.demos.mvp.presenter.DownloaderPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements DownloaderConstract.PV {

    @BindView(R.id.bt)
    Button bt;
    @BindView(R.id.iv)
    ImageView iv;

    private DownloaderPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demos);
        ButterKnife.bind(this);

        presenter = new DownloaderPresenter(this);
    }

    @OnClick(R.id.bt)
    public void onViewClicked() {
        ImageBean imageBean = new ImageBean();
        imageBean.setRequestPath(Constant.PATH);
        requestDownloader(imageBean);
    }

    @Override
    public void requestDownloader(ImageBean imageBean) {
        if (presenter != null) presenter.requestDownloader(imageBean);
    }

    @Override
    public void requestDownloaderResult(boolean isSuccess, ImageBean imageBean) {
        Toast.makeText(this, isSuccess?"下载成功":"下载失败", Toast.LENGTH_SHORT).show();
        if (isSuccess && imageBean.getBitmap() != null) {
            iv.setImageBitmap(imageBean.getBitmap());
        }
    }
}
