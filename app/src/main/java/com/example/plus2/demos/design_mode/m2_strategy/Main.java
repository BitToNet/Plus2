package com.example.plus2.demos.design_mode.m2_strategy;

import java.util.Arrays;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 5/20/21   3:15 PM
 * desc   : 策略设计模式
 * Comparable和Comparator,Comparator是策略设计模式，Comparable不是
 */
public class Main {
    public static void main(String[] args) {
//        int[] a = {9, 3, 2, 5, 7, 1, 4};
//        Comparable[] a = {new Cat(1, 1), new Cat(2, 2), new Cat(4, 4), new Cat(3, 3)};
        Dog[] a = {new Dog(2, 2), new Dog(4, 4), new Dog(3, 3)};
        Sorter sorter = new Sorter();

        // 用不同的策略实现排序
        sorter.sort(a, new Comparator<Dog>() {
            @Override
            public int compare(Dog o1, Dog o2) {
                if(o1.weight < o2.weight) return  -1;
                else if(o1.weight > o2.weight) return 1;
                else return 0;
            }
        });
        // 我现在是用int排序
        // 如果我要用double类型呢？如果是Cat类呢？怎么比较猫的大小
        // 怎么针对不同类型的数据进行排序
        System.out.println(Arrays.toString(a));
    }
}
