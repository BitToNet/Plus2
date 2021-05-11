package com.example.plus2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-08-04   10:50
 * desc   :
 */
public class Main {

    //算法题

    //3 无重复字符的最长子串
    public int lengthOfLongestSubstring(String s) {
        int lenth = 0;
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < strings.size(); i++) {
            if (strings.contains(String.valueOf(s.charAt(i)))) {
                lenth = lenth > strings.size() ? lenth : strings.size();
                strings.clear();
            } else {
                strings.add(String.valueOf(s.charAt(i)));
            }
        }
        return lenth;
    }

    //2  两数相加
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode tail = null;
        int carry = 0;

        while (l1 != null || l2 != null) {
            int i1 = l1 == null ? 0 : l1.val;
            int i2 = l2 == null ? 0 : l2.val;
            int i = i1 + i2 + carry;

            if (head == null) {
                tail = new ListNode(i % 10);
                head = tail;
            } else {
                tail.next = new ListNode(i % 10);
                tail = tail.next;
            }
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }

            carry = i / 10;
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    //1 两数之和
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((nums[i] + nums[j]) == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }


    public static void main(String[] args) {
//        FileOutputStream outputStream = null;
//        try {
//            InputStream initialStream = new FileInputStream("./lib/1.jpg");
//            byte[] buffer = new byte[initialStream.available()];
//            initialStream.read(buffer);
//            buffer = AESUtils.encrypt(buffer,"1234567812345678");
//            outputStream = new FileOutputStream("./lib/8/1.jpg");
//            outputStream.write(buffer);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
