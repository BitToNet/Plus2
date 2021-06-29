package com.example.plus2.demos.design_mode.m1_singleton;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 5/20/21   11:32 AM
 * desc   : 单例，8种写法，只有两种是完美无缺的，实际工作中用的一般是有瑕疵的
 */
public class Main {
    public static void main(String[] args) {
//        String input = "www.baidu.com";
        char[] input = {'w','w','w','.','b','a','i','d','u'};
        if (input != null && input.length > 0) {
            char[] a = funC(input);
//            String a = funC2(input);
            System.out.println(a);
            System.out.println(input);
        }
    }

    private static char[] funC(char[] input) {
        char[] output = new char[input.length];
        int start = 0;
        for (int i = 0; i < input.length; i++) {
            char charAt = input[i];
            if(!(charAt>='a'&&charAt<='z')||i==input.length-1){
                int wordLength = i - start;
                for (int j = 0; j < wordLength; j++) {
                    output[j+start] = input[i-j-1];
                }
                output[i] = input[i];
                start = i+1;
            }
        }
        return output;
    }

    private static String funC2(String input) {
        String output = "";
        String words = "";
        for (int i = 0; i < input.length(); i++) {
            char charAt = input.charAt(i);
            if (charAt >= 'a' && charAt <= 'z') {
                words = charAt + words;
                if (i == input.length() - 1) {
                    output += words;
                }
            } else {
                output += words;
                output += charAt;
                words = "";
            }

        }
        return output;
    }
}
