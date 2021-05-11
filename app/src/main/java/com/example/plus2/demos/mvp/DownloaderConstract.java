package com.example.plus2.demos.mvp;

import com.example.plus2.demos.mvp.model.ImageBean;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-12-24   10:45
 * desc   : 构造器
 */
public interface DownloaderConstract {

    interface M{
        //P层告诉M层，需要做什么
        void requestDownloader(ImageBean imageBean)throws Exception;
    }

    interface PV{
        // V层告诉P层，需要做什么
        void requestDownloader(ImageBean imageBean);

        // P层得到M层的结果返回，再通知V层
        void requestDownloaderResult(boolean isSuccess, ImageBean imageBean);
    }
}
