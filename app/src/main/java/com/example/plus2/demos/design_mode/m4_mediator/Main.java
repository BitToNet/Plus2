package com.example.plus2.demos.design_mode.m4_mediator;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 5/20/21   11:32 AM
 * desc   : 消息中间件：解耦
 * 调停者：内部之间对一个人负责，互相之间不打交道（对内）
 * 门面代办：一个门面代办所有流程（对外）
 */
public class Main {
    public static void main(String[] args){
        ArrayList<String> a = new ArrayList<>();
        Iterator<String> iterator = a.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            iterator.remove();
        }
    }
}
