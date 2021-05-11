package com.example.plus2.demos.mvp.engine;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.plus2.demos.mvp.Constant;
import com.example.plus2.demos.mvp.DownloaderConstract;
import com.example.plus2.demos.mvp.model.ImageBean;
import com.example.plus2.demos.mvp.presenter.DownloaderPresenter;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.InvalidMarkException;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-12-24   11:05
 * desc   : 下载引擎，他需要完成model的事情
 */
public class DownloaderEngine implements   DownloaderConstract.M {
    private DownloaderPresenter downloaderPresenter;

    public DownloaderEngine(DownloaderPresenter downloaderPresenter) {
        this.downloaderPresenter = downloaderPresenter;

    }

    @Override
    public void requestDownloader(ImageBean imageBean) throws Exception {
        //P层让我做这个需求
        new Thread(new DownLoader(imageBean)).start();
    }

    final class DownLoader implements Runnable{
        private final ImageBean imageBean;

        public DownLoader(ImageBean imageBean) {
            this.imageBean = imageBean;
        }

        @Override
        public void run() {
            try {
                URL url = new URL(imageBean.getRequestPath());
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("GET");

                if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    showUi(Constant.SUCCESS, bitmap);
                } else {
                    showUi(Constant.ERROR, null);
                }
            } catch (Exception e) {
                e.printStackTrace();
                showUi(Constant.ERROR, null);
            }
        }

        private void showUi(int resultCode, Bitmap bitmap) {
            imageBean.setBitmap(bitmap);
            downloaderPresenter.requestDownloaderResult(resultCode==Constant.SUCCESS, imageBean);
        }
    }
}
