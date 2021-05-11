package com.example.plus2.demos.jetpack.viewmodel_databinding;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2021-01-13   15:01
 * desc   :
 */
public class MainViewModel extends AndroidViewModel {

    /**
     * ui data
     */
//    private String name;
//    private String pwd;

    /**
     * LiveData:感应改变数据
     */
    private MutableLiveData<String> name;
    private MutableLiveData<String> pwd;



    private Context context;

    public MainViewModel(@NonNull Application application) {
        super(application);
        context = application;


    }

    public MutableLiveData<String> getName() {
        if (name == null) {
            name = new MutableLiveData<>();
            name.setValue("默认");
        }
        return name;
    }

    public MutableLiveData<String> getPwd() {
        if (pwd == null) {
            pwd = new MutableLiveData< >();
            pwd.setValue("默认");
        }
        return pwd;
    }

    /**
     * 输入
     */
    public void appendNumber(String number) {
        if (null != number) {
            name.setValue(pwd.getValue());
        }
    }
}
