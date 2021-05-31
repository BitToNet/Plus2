package com.example.plus2.demos.design_mode.m2_strategy;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 5/20/21   3:14 PM
 * desc   : 策略设计模式
 */
public class Sorter<T> {

    public void sort(T[] arr, Comparator<T> comparator) {
        for (int i = 0; i < arr.length; i++) {
            int minPos = i;

            for (int j = i + 1; j < arr.length; j++) {
//                minPos = arr[j] < arr[minPos] ? j : minPos;
//                minPos = arr[j].compareTo(arr[minPos])<0  ? j : minPos;
                minPos = comparator.compare(arr[j], arr[minPos]) < 0 ? j : minPos;
            }

            swap(arr, i, minPos);
        }
    }

    public static void sort(Comparable[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minPos = i;

            for (int j = i + 1; j < arr.length; j++) {
//                minPos = arr[j] < arr[minPos] ? j : minPos;
                minPos = arr[j].compareTo(arr[minPos]) < 0 ? j : minPos;
            }

            swap(arr, i, minPos);
        }
    }

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minPos = i;

            for (int j = i + 1; j < arr.length; j++) {
                minPos = arr[j] < arr[minPos] ? j : minPos;
//                minPos = arr[j].compareTo(arr[minPos])<0  ? j : minPos;
            }

            swap(arr, i, minPos);
        }
    }

    void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static void swap(Comparable[] arr, int i, int j) {
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
