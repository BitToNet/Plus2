package com.example.plus2.demos.design_mode.m1_singleton;

/**
 * lazy loading
 * 懒汉式
 * 虽然达到啦按需初始化的目的，但也带来了线程不安全的问题
 */
public class Mgr03 {
//    private static final Mgr02 INSTANCE;
    private static Mgr03 INSTANCE;

    // 关键是私有化构造方法
    private Mgr03() {}

    public static Mgr03 getInstance(){
        if(INSTANCE == null){
            // 多线程的时候可能同时进来，多次实例化
            try {
                //延长初始化时间，更容易看到这个bug
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new Mgr03();
        }
        return INSTANCE;
    }

    public static void main(String[] args){
        for(int i=0; i<100; i++){
            new Thread(()->{
                System.out.println(Mgr03.getInstance().hashCode());
            }).start();
        }
    }
}
