package com.example.plus2.demos.design_mode.m3_factory;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 5/21/21   2:34 PM
 * desc   :
 */
public class SimpleVehicleFactory {
    public Car createCar(){
        // 前置工作
        return new Car();
    }


    public Plane createPlane(){
        return new Plane();
    }

}
