package com.example.plus2.demos.design_mode.m1_singleton;

/**
 * 完美的写法之二：
 * 静态内部类的方式
 * jvm保证单例，虚拟机加载类的时候，保证只加载一次
 * 加载外部类时不会加载内部类，这样可以实现懒加载
 * 外面的类被加载的时候，他里面的静态内部类是不会被加载的
 */
public class Mgr07 {

    private static class Mgr07Holder{
        private final static Mgr07 INSTANCE = new Mgr07();
    }

    // 关键是私有化构造方法
    private Mgr07() {}

    public static Mgr07 getInstance(){
        return Mgr07Holder.INSTANCE;
    }

    public static void main(String[] args){
        for(int i=0; i<100; i++){
            new Thread(()-> System.out.println(Mgr07.getInstance().hashCode())
            ).start();
        }
    }
}
