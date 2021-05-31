package com.example.plus2.demos.design_mode.m2_strategy;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 5/20/21   3:28 PM
 * desc   :
 */
public class Dog {
    int weight;

    @Override
    public String toString() {
        return "Dog{" +
                "weight=" + weight +
                ", height=" + height +
                '}';
    }

    int height;

    public Dog(int weight, int height){
        this.weight = weight;
        this.height = height;
    }

}
