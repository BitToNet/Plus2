package com.example.plus2.demos.mvp.presenter;

import com.example.plus2.demos.mvp.DownloaderConstract;
import com.example.plus2.demos.mvp.MainActivity;
import com.example.plus2.demos.mvp.engine.DownloaderEngine;
import com.example.plus2.demos.mvp.model.ImageBean;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-12-24   10:53
 * desc   :
 */
public class DownloaderPresenter implements DownloaderConstract.PV {

    private MainActivity view;
    private DownloaderEngine model;//下载模型

    public DownloaderPresenter(MainActivity view) {
        this.view = view;
        model = new DownloaderEngine(this);
    }

    @Override
    public void requestDownloader(ImageBean imageBean) {
        //接收到View层的指令，去完成某个需求（可以自己完成，也可以让别人完成）
        try {
            model.requestDownloader(imageBean);
        } catch (Exception e) {
            e.printStackTrace();
            //todo 省略了异常的处理
        }
    }

    @Override
    public void requestDownloaderResult(boolean isSuccess, ImageBean imageBean) {
        // 讲完成的结果告知View层（刷新UI）
        view.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                view.requestDownloaderResult(isSuccess,imageBean);
            }
        });
    }
}
