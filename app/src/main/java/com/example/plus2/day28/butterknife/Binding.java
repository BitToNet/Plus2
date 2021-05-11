package com.example.plus2.day28.butterknife;

import android.app.Activity;

import java.lang.reflect.Field;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-09-07   18:09
 * desc   :
 */
public class Binding {
    public static void bind(Activity activity) {
        //反射的方式性能有问题，多个注解多个循环，所以用Annotation Processing ，依赖注入？不是依赖注入dagger
        for (Field field : activity.getClass().getDeclaredFields()) {
            Bind bind = field.getAnnotation(Bind.class);
            if (bind != null) {
                try {
                    field.setAccessible(true );
                    field.set(activity,activity.findViewById(bind.value()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
