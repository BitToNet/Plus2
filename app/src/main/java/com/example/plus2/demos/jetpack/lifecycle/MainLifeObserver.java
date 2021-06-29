package com.example.plus2.demos.jetpack.lifecycle;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 6/23/21   2:53 PM
 * desc   :
 */
public class MainLifeObserver implements LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void start(){
        Log.e("MainLifeObserver==========","start");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void stop(){
        Log.e("MainLifeObserver==========","stop");
    }
}
