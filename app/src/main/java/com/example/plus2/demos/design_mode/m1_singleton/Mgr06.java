package com.example.plus2.demos.design_mode.m1_singleton;

/**
 * lazy loading
 * 懒汉式
 * 虽然达到啦按需初始化的目的，但也带来了线程不安全的问题
 * 完善懒汉式，在getInstance方法上加synchronized，但是效率下降了
 * 试图通过同步代码块的方式提高效率，不在整个方法里面加锁，只在我需要的时候,但是不行，没有锁住
 * 所以有了双重判断的写法，这是可以的，所以在以前的单例模式里面，这种是最完美的
 */
public class Mgr06 {
//    private static final Mgr02 INSTANCE;
//    private static Mgr06 INSTANCE;
    //加上volatile的原因比较复杂，语句重排
    private static volatile Mgr06 INSTANCE;

    // 关键是私有化构造方法
    private Mgr06() {}

    public static Mgr06 getInstance(){
        if(INSTANCE == null){
            // 多线程的时候可能同时进来，多次实例化
            synchronized (Mgr06.class){
                if(INSTANCE == null){
                    try {
                        //延长初始化时间，更容易看到这个bug
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new Mgr06();
                }
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args){
        for(int i=0; i<100; i++){
            new Thread(()-> System.out.println(Mgr06.getInstance().hashCode())
            ).start();
        }
    }
}
