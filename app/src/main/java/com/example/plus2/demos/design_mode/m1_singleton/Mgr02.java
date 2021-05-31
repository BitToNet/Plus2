package com.example.plus2.demos.design_mode.m1_singleton;

/**
 * 跟01一样
 */
public class Mgr02 {
    private static final Mgr02 INSTANCE;
    static {
        INSTANCE = new Mgr02();
    }

    // 关键是私有化构造方法
    private Mgr02() {}

    public static Mgr02 getInstance(){
        return INSTANCE;
    }

    public static void main(String[] args){
        Mgr02 mgr01 = Mgr02.getInstance();
        Mgr02 mgr02 = Mgr02.getInstance();
        System.out.println(mgr01==mgr02);
    }
}
