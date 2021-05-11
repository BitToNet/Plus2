package com.example.plus2.demos.mvp2.base;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-12-30   14:25
 * desc   : View层基类
 */
public abstract class BaseView<P extends BasePresenter,CONTRACT> extends Activity {
    protected P p;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 弱引用
        p = getPresenter();
        // 绑定
        p.bindView(this);
    }

    // 让p层做什么需求
    public abstract CONTRACT getContract();

    // 从子类中获取具体的契约
    public abstract P getPresenter();

    // 如果p层出现了问题，需要告知v层
    public void error(Exception e){}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除绑定
        p.unBindView();
    }
}
