package com.example.plus2.demos.design_mode.m3_factory.abstract_factory;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 5/25/21   11:58 AM
 * desc   :
 */
public class MagicFactory extends AbstatcFactory {
    @Override
    Vehivle createVehivle() {
        return new Bread();
    }

    @Override
    Weapon createWeapon() {
        return new MagicStick();
    }
}
