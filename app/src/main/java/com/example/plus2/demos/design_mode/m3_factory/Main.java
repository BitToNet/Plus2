package com.example.plus2.demos.design_mode.m3_factory;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 5/20/21   11:32 AM
 * desc   : 工厂设计模式：
 * 任何可以产生对象的方法或类，都可以称之为工厂
 * 单例也是一种工厂
 * 为什么有了new还要工厂？1.灵活控制生产过程；2.权限、修饰、日志...
 */
public class Main {
    public static void main(String[] args) {
        // 我有个车，可以跑
//        Car car = new Car();
//        car.go();
        // 换飞机了
        Plane plane = new Plane();
        plane.go();
        // 让这段代码可扩展性好一些
        // 建个父类，交通工具
        Moveable m = new Plane();
        m.go();
        // 建个简单工厂，生产交通工具，可扩展性不好
        Car car = new SimpleVehicleFactory().createCar();
        // 定义一个Car的工厂，还可以定义飞机的工厂，可扩展
        // 现在可以做到任意定制交通工具，任意定制生产过程
        new CarFactory().create().go();

        // 再讲抽象工厂

    }
}
