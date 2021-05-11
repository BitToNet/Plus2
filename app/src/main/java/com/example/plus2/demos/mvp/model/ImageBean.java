package com.example.plus2.demos.mvp.model;

import android.graphics.Bitmap;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-12-23   10:40
 * desc   :
 */
public class ImageBean {
    private String requestPath;
    private Bitmap bitmap;

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
