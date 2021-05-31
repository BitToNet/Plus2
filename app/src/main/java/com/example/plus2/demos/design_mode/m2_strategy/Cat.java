package com.example.plus2.demos.design_mode.m2_strategy;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 5/20/21   3:28 PM
 * desc   :
 */
public class Cat implements Comparable<Cat> {
    int weight;

    @Override
    public String toString() {
        return "Cat{" +
                "weight=" + weight +
                ", height=" + height +
                '}';
    }

    int height;

    public Cat(int weight, int height){
        this.weight = weight;
        this.height = height;
    }

    @Override
    public int compareTo(Cat c) {
        if(this.weight < c.weight) return  -1;
        else if(this.weight > c.weight) return 1;
        else return 0;
    }

}
