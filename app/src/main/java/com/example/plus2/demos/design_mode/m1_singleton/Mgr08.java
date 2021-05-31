package com.example.plus2.demos.design_mode.m1_singleton;

/**
 * 完美的写法之三：
 * 枚举单例
 * 《effective java》的作者，java的创作者之一，在书中写了一种
 * 不仅可以解决线程同步，还可以防止反序列化
 * 反序列化问题：java的反射是可以通过一个.class文件，然后把整个class，load到内存，然后new一个实例出来。
 * 枚举类没有构造方法，所以你就算拿到他的class文件，也没法构造他的对象
 */
public enum Mgr08 {
    INSTANCE;
    private static void main(String[] args){
        for (int i = 0; i < 100; i++) {
            new Thread(()->System.out.println(Mgr08.INSTANCE.hashCode())).start();
        }
    }
}
