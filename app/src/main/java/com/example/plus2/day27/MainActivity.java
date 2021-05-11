package com.example.plus2.day27;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.plus2.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dalvik.system.DexClassLoader;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-08-25   17:07
 * desc   : 手写热更新
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.showTitleBt)
    Button showTitleBt;
    @BindView(R.id.loadPluginBt)
    Button loadPluginBt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        ButterKnife.bind(this);

//        Utils utils = new Utils();
//        utils.shout();

        try {
            Class utilsClass = Class.forName("com.example.plus2.day26.hidden.Utils");
            //获取第一个构造方法
            Constructor utilsConstructor = utilsClass.getDeclaredConstructors()[0];
            //解除限制
            utilsConstructor.setAccessible(true);
            Object utils = utilsConstructor.newInstance();
            Method shoutMethod = utilsClass.getDeclaredMethod("shout");
            shoutMethod.setAccessible(true);
            shoutMethod.invoke(utils);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        File apk = new File((getCacheDir() + "/26_pluginnable_plugin-debug.apk"));
        if (!apk.exists()) {
            try {
                InputStream is = getAssets().open("apk/26_pluginnable_plugin-debug.apk");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();

                FileOutputStream fos = new FileOutputStream(apk);
                fos.write(buffer);
                fos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            DexClassLoader classLoader = new DexClassLoader(apk.getPath(), getCacheDir().getPath(), null, null);
            Class pluginUtilsClass = classLoader.loadClass("com.example.a26_pluginnable_plugin.Utils");
            //获取第一个构造方法
            Constructor utilsConstructor = pluginUtilsClass.getDeclaredConstructors()[0];
            Object utils = utilsConstructor.newInstance();
            Method shoutMethod = pluginUtilsClass.getDeclaredMethod("shout");
            shoutMethod.invoke(utils);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @OnClick({R.id.showTitleBt, R.id.loadPluginBt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.showTitleBt:
                Title title = new Title();
                tv.setText(title.getTitle());
                break;
            case R.id.loadPluginBt:
//                getClassLoader().loadClass()
                break;
        }
    }
}
