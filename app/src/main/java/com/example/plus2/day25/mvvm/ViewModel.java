package com.example.plus2.day25.mvvm;

import android.widget.EditText;

import com.example.plus2.day25.data.DataCenter;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-08-24   10:06
 * desc   :
 */
public class ViewModel {
    TextAttr data1 = new TextAttr();
    TextAttr data2 = new TextAttr();

    ViewModel(ViewBinder binder, EditText et1,EditText et2){
        binder.bind(et1,data1);
        binder.bind(et2,data2);
    }

    void load() {
        String[] data = DataCenter.getData();
        data1.setText(data[0]);
        data2.setText(data[1]);
    }

    static class TextAttr {
        private String text;
        private OnChangeListener listener;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
            if (listener != null) {
                listener.onChange(text);
            }
        }

        void setOnChangeListener(OnChangeListener listener){
            this.listener = listener;
        }
        interface OnChangeListener {
            void onChange(String newTexr);
        }
    }
}
