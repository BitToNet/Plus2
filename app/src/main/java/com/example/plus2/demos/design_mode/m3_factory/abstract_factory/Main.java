package com.example.plus2.demos.design_mode.m3_factory.abstract_factory;

import com.example.plus2.demos.design_mode.m3_factory.CarFactory;
import com.example.plus2.demos.design_mode.m3_factory.Moveable;
import com.example.plus2.demos.design_mode.m3_factory.Plane;
import com.example.plus2.demos.design_mode.m3_factory.SimpleVehicleFactory;

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
        // 再讲抽象工厂
        //先来一个现代人，骑着车，拿着武器
        Car c = new Car();
        c.go();
        AK47 w = new AK47();
        w.shoot();

        //现在来了一个魔法世界的人，骑着扫帚，拿着魔法棒
        Bread b = new Bread();
        b.go();
        MagicStick m = new MagicStick();
        m.shoot();

        // todo 怎么实现一个工厂切换一族
        // 抽象工厂-->抽象交通工具、武器-->接口属性（名词用抽象类，形容词用接口）
        // 具体工厂实现抽象工厂、实现抽象方法-->
//        AbstatcFactory f = new MagicFactory();
        AbstatcFactory f = new ModernFactory();
        f.createVehivle().go();
        f.createWeapon().shoot();


    }
}
