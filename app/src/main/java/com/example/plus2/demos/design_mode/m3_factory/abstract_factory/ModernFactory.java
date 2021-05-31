package com.example.plus2.demos.design_mode.m3_factory.abstract_factory;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 5/25/21   11:59 AM
 * desc   :
 */
public class ModernFactory extends AbstatcFactory {
    @Override
    Vehivle createVehivle() {
        return new Car();
    }

    @Override
    Weapon createWeapon() {
        return new AK47();
    }
}
