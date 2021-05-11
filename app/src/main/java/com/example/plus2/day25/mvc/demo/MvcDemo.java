package com.example.plus2.day25.mvc.demo;

import android.view.View;

import com.example.plus2.R;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-11-27   11:34
 * desc   :
 */
public class MvcDemo {

    class View {
        //相当于xml
        //用来显示以及把用户事件传给controller
        Controller controller;

        void showData(int data) {
            //...
        }

        void clicked() {
            controller.viewClicked(this);
        }
    }

    class Controller {
        //相当于activity
        //用来调度，去操作model，让他去执行业务逻辑，他告诉model你要做什么？model再具体去实现。
        Model model;

        void viewClicked(View view) {
//            switch (view.getId()) {
//                case R.id.bt:
//                    model.dataAdd(1);
//                    break;
//
//            }
        }
    }

    class Model {
        //相当于相关数据操作类
        //将实现结果给view，让view去更新
        int data;
        View view;
        public void dataAdd(int delta) {
            data += delta;
            view.showData(data);
        }
    }
}
