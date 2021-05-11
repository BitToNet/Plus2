package com.example.plus2.day28.butterknife;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-09-07   17:54
 * desc   : 这是一个Annotation类，是一种特殊的interface
 */

//限制：1.SOURCE：只能看；2.CLASS：会被编译到class，但程序不一定运行；3.RUNTIME：保留到最后
@Retention(RetentionPolicy.RUNTIME)
//调用类型：字段（还有方法什么之类的）
@Target(ElementType.FIELD)
public @interface Bind {
    int value();

}
