package com.example.plus2.demos.mvc;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.plus2.demos.mvc.bean.ImageBean;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-12-23   10:56
 * desc   :
 */
public class ImageDownloader {
    static final int SUCCESS = 200;
    static final int ERROR = 404;

    public void down(Callback callback, ImageBean imageBean) {
        new Thread((new DownLoader(callback, imageBean))).start();
    }

    private class DownLoader implements Runnable {
        private final Callback callback;
        private final ImageBean imageBean;

        public DownLoader(Callback callback, ImageBean imageBean) {
            this.callback = callback;
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
                    showUi(SUCCESS, bitmap);
                } else {
                    showUi(ERROR, null);
                }
            } catch (Exception e) {
                e.printStackTrace();
                showUi(ERROR, null);
            }
        }

        private void showUi(int resultCode, Bitmap bitmap) {
            if (callback != null) {
                imageBean.setBitmap(bitmap);
                callback.callback(resultCode, imageBean);
            }
        }
    }
}
