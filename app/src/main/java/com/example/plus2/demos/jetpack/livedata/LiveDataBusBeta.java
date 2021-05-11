package com.example.plus2.demos.jetpack.livedata;

import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.Map;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2021-01-15   10:02
 * desc   :
 */
public class LiveDataBusBeta {

    private final Map<String, MutableLiveData<Object>> bus;
    private LiveDataBusBeta(){
        bus = new HashMap<>();
    }

    private static class  SingleHolder{
        private static final LiveDataBusBeta DATA_BUS = new LiveDataBusBeta();
    }

    public static LiveDataBusBeta getInstance(){
        return SingleHolder.DATA_BUS;
    }

    public <T> MutableLiveData<T> with(String key,Class<T> type){
        if(!bus.containsKey(key)){
            bus.put(key,new MutableLiveData<Object>());
        }
        return (MutableLiveData<T>)bus.get(key);
    }

    public MutableLiveData<Object> with(String target){
        return with(target,Object.class);
    }

    public void remove(String key){
        if(bus.containsKey(key)){
            bus.remove(key);
        }
    }
}
