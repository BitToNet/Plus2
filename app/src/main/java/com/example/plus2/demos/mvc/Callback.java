package com.example.plus2.demos.mvc;

import com.example.plus2.demos.mvc.bean.ImageBean;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-12-23   10:51
 * desc   :
 */
public interface Callback {
    void callback(int resultCode, ImageBean imageBean);
}
