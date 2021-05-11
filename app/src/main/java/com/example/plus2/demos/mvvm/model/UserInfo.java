package com.example.plus2.demos.mvvm.model;

import androidx.databinding.ObservableField;

import java.util.Observable;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2021-01-05   11:03
 * desc   :
 */
public class UserInfo {
    //mvvm的bean类里的元素必须是public的，方便直接访问（databinding规范）
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> pwd = new ObservableField<>();
}
