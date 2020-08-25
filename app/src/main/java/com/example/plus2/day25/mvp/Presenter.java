package com.example.plus2.day25.mvp;

import com.example.plus2.day25.data.DataCenter;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-08-24   10:06
 * desc   :
 */
public class Presenter {
    IView iView;
    Presenter(IView iView){
        this.iView = iView;
    }
    void load(){
        String[] data = DataCenter.getData();
        iView.showData(data[0],data[1]);
    }

    interface IView{
        void showData(String data1,String data2);
    }
}
