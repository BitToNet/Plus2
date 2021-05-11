package com.example.plus2.demos.mvp2.base;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-12-30   09:47
 * desc   : 接收p层交给他的需求
 */
public abstract class BaseModel<P extends BasePresenter,CONTRACT> {
    protected P p;

    // 业务结束，通过Presenter调用契约、合同（接口中的方法） void responseResult(T t);

    public BaseModel(P p){
        this.p = p;
    }

    public abstract CONTRACT getContract();
}
