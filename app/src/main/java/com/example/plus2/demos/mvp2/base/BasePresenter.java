package com.example.plus2.demos.mvp2.base;

import java.lang.ref.WeakReference;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-12-30   14:19
 * desc   :
 */
public abstract class BasePresenter<V extends BaseView,M extends BaseModel,CONTRACT> {

    protected M m;
    //绑定v层弱引用。
    private WeakReference<V> vWeakReference;

    public BasePresenter() {
        m = getModel();
    }

    public void bindView(V v){
        vWeakReference = new WeakReference<>(v);
    }

    public void unBindView() {
        if(vWeakReference!=null){
            vWeakReference.clear();
            vWeakReference = null;
            System.gc();
        }
    }

    // 获取view
    public V getView(){
        if(vWeakReference!=null){
            return vWeakReference.get();
        }
        return null;
    }

    //获取子类具体契约（Model层和View层协商的共同业务）
    public abstract CONTRACT getContract();

    public abstract M getModel();
}
