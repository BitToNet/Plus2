package com.example.plus2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-08-04   10:50
 * desc   :
 */
public class Main {
    public static void main(String[] args) {

//        boolean b = judgePoint24(new int[]{3, 3, 8, 8});
//        boolean b = judgePoint24my(new int[]{3, 3, 8, 8});
//        System.out.println("==="+b);
        int[] ints = {4,2,1,3};
        ListNode listNode = new ListNode();
        creatListNodeByArr(listNode, ints,0);
        System.out.println(listNode.next.toString());
        ListNode listNode1 = sortList(listNode);
        System.out.println(listNode1.next.toString());
    }
    public static ListNode creatListNodeByArr(ListNode node, int[] arr, int size) {
        if(size<arr.length){
            ListNode next = new ListNode(arr[size]);
            node.next = next;
            creatListNodeByArr(next,arr,++size);
        }
        return  node;
    }

    public static ListNode sortList(ListNode head) {
        return quickSort(head, null);
    }
    private static ListNode quickSort(ListNode head, ListNode end){
        //截止条件
        if(head == end||head.next ==end){
            return head;
        }
        //排序
        ListNode lhead = head, p = head.next, rhead = head;
        while(p != end){
            ListNode next = p.next;
            if(p.val<head.val){//尾插
                p.next = lhead;
                lhead = p;
            }else{//头插
                rhead.next = p;
                rhead = p;
            }
            p = next;
        }
        rhead.next = end;
        ListNode res = quickSort(lhead, head);
        head.next = quickSort(head.next, end);
        return res;
    }

    static final int TARGET = 24;
    static final double EPSILON = 1e-6;
    static final int ADD = 0, MULTIPLY = 1, SUBTRACT = 2, DIVIDE = 3;

    public static boolean judgePoint24(int[] nums) {
        List<Double> list = new ArrayList<Double>();
        for (int num : nums) {
            list.add((double) num);
        }
        return solve(list);
    }

    public static boolean solve(List<Double> list) {
        System.out.println(list.toString());
        if (list.size() == 0) {
            return false;
        }
        if (list.size() == 1) {
            return Math.abs(list.get(0) - TARGET) < EPSILON;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != j) {
                    List<Double> list2 = new ArrayList<Double>();
                    for (int k = 0; k < size; k++) {
                        if (k != i && k != j) {
                            list2.add(list.get(k));
                        }
                    }
                    for (int k = 0; k < 4; k++) {
                        if (k < 2 && i > j) {
                            continue;
                        }
                        if (k == ADD) {
                            list2.add(list.get(i) + list.get(j));
                        } else if (k == MULTIPLY) {
                            list2.add(list.get(i) * list.get(j));
                        } else if (k == SUBTRACT) {
                            list2.add(list.get(i) - list.get(j));
                        } else if (k == DIVIDE) {
                            if (Math.abs(list.get(j)) < EPSILON) {
                                continue;
                            } else {
                                list2.add(list.get(i) / list.get(j));
                            }
                        }
                        if (solve(list2)) {
                            return true;
                        }
                        list2.remove(list2.size() - 1);
                    }
                }
            }
        }
        return false;
    }

    public static boolean judgePoint24my(int[] cards) {
        // 边界条件
        if(cards.length==0){
            return false;
        }
        if(cards.length==1){
            return cards[0]==24;
        }
        double[] chair =  new double[cards.length];
        for(int i=0; i<cards.length; i++){
            chair[i] = (double)cards[i];
        }
        boolean[] state = new boolean[cards.length];

        return dfs(chair,state,0,4-cards.length);
    }

    private static boolean dfs(double[] chair, boolean[] state, double num, int tip){
        //截止条件
        System.out.println("num"+num);
        if(tip == 3){
            return (int)num == 24;
        }
        //筛选节点，两两之间相互计算
        for(int i=0; i<chair.length; i++){
            if(!state[i]){
                state[i]=true;
                for(int j=0; j<chair.length; j++){
                    if(!state[i]){
                        double old = chair[j];
                        tip++;
                        //+
                        System.out.println(chair[i]+"+"+chair[j]);
                        chair[j] = chair[i]+chair[j];
                        if(dfs(chair,state,chair[j],tip)){
                            return true;
                        }
                        chair[j] = old;
                        //-
                        chair[j] = chair[i]-chair[j];
                        System.out.println(chair[i]+"-"+chair[j]);
                        if(dfs(chair,state,chair[j],tip)){
                            return true;
                        }
                        chair[j] = old;
                        //*
                        chair[j] = chair[i]*chair[j];
                        System.out.println(chair[i]+"*"+chair[j]);
                        if(dfs(chair,state,chair[j],tip)){
                            return true;
                        }
                        chair[j] = old;
                        ///
                        chair[j] = chair[i]/chair[j];
                        System.out.println(chair[i]+"/"+chair[j]);
                        if(dfs(chair,state,chair[j],tip)){
                            return true;
                        }
                        chair[j] = old;
                        tip--;
                    }
                }
                state[i]=false;
            }
        }
        return false;
    }

    class Solution {
        int[] cards = new int[7];
        public boolean isUnique(String astr) {
            int k = 0;
            for(int i=0; i<astr.length(); i++){
                char c = astr.charAt(i);
                if((k & 1<<(c-'a'))>0){
                    return false;
                }else{
                    k |= 1<<(c-'a');
                }
            }
            return true;
        }
    }

//    public class Solution {
        /**
         * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
         *
         *
         * @param p char字符型二维数组
         * @param w string字符串
         * @return bool布尔型
         */
        public boolean hasPath (char[][] p, String w) {
            // write code here
            if(p.length==0||p[0].length==0||w.length()==0){
                return false;
            }
            boolean[][] pb = new boolean[p.length][p[0].length];
            for(int i=0; i<p.length; i++){
                for(int j=0; j<p[0].length; j++){
                    if(p[i][j]==w.charAt(0)){
                        if(dfs(p,pb,i,j,w,0)){
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        private boolean dfs(char[][] p, boolean[][] pb,int i, int j, String w,int index){
            // 截止条件
            if(p[i][j] == w.charAt(index)){
                index++;
                pb[i][j] = true;
                if(index == w.length()){
                    return true;
                }
            }
            // 筛选条件
            for(int[] t:getBounds(i,j,p.length,p[0].length)){
                if(!pb[t[0]][t[1]]){
                    if(dfs(p,pb,t[0],t[1],w,index)){
                        return true;
                    }
                }
            }
            return false;
        }

        //获取可用边界
        private List<int[]> getBounds(int i,int j,int x,int y){
            List<int[]> res = new ArrayList();
            if(i-1>0){res.add(new int[]{i-1,j});}
            if(j-1>0){res.add(new int[]{i,j-1});}
            if(i+1<x){res.add(new int[]{i+1,j});}
            if(j+1<y){res.add(new int[]{i,j+1});}
            return res;
        }
//    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    //谷歌的那个笔试题
    public int[] levelOrder(TreeNode t) {
        List<Integer> res = new ArrayList();
        if (t != null) {
            dfs(t, res);
        }
        return res.stream().mapToInt(i -> i).toArray();
    }

    private void dfs(TreeNode t, List<Integer> res) {

        res.add(t.val);

        if (t.left != null) {
            dfs(t.left, res);
        }
        if (t.right == null) {
            return;
        } else {
            dfs(t.right, res);
        }
    }

    //    class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList();
        //边界条件
        if (n > 0) {
            dfs(n, n, "", res);
        }
        return res;
    }

    private void dfs(int l, int r, String s, List<String> res) {
        //筛选条件
        if (l == 0 && r == 0) {
            res.add(s);
            return;
        }
        //候选目标
        for (int i = 0; i < l; i++) {
            s = s + "(";
            l--;
            dfs(l, r, s, res);
            l++;
        }
        for (int i = 0; i < r; i++) {
            if (r > l) {
                s = s + ")";
                r--;
                dfs(l, r, s, res);
                r++;
            }
        }
    }
//    }

    private void dfs(int len, int[] pk, int[] pv, List<Integer> chair, List<List<Integer>> res) {
        //截止条件
        if (len == chair.size()) {
            res.add(new ArrayList(chair));
            return;
        }
        //候选节点
        for (int i = 0; i < pk.length; i++) {
            int c = pk[i];
            //筛选条件
            if (pv[i] > 0) {
                pv[i]--;
                chair.add(c);
                dfs(len, pk, pv, chair, res);
                pv[i]++;
                chair.remove(chair.size() - 1);
            }
        }
    }


    //    class Solution {
    public List<List<Integer>> permute(int[] p) {
        boolean[] r = new boolean[p.length];
        List<List<Integer>> t = new ArrayList<>();
        dfs(p, 0, r, new ArrayList<Integer>(), t);
        return t;
    }

    private void dfs(int[] p, int index, boolean[] r, List<Integer> l, List<List<Integer>> t) {
        //截止条件
        if (index == p.length) {
            t.add(new ArrayList(l));
            return;
        }
        for (int i = 0; i < p.length; i++) {
            int c = p[i];
            if (!r[i]) {
                r[i] = true;
                l.add(c);
                dfs(p, index + 1, r, l, t);
                l.remove(l.size() - 1);
                r[i] = false;
            }
        }
    }
//    }

    //    class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> answer = new ArrayList();
        if (candidates.length > 0)
            dfs(candidates, target, new ArrayList<Integer>(), answer);
        return answer;
    }

    private void dfs(int[] ints, int target, List<Integer> list, List<List<Integer>> answer) {
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum = list.get(i) + sum;
        }
        // 筛选条件
        if (sum == target) {
            //去掉解集中重复的组合
            Collections.sort(list);
            if (!answer.contains(list))
                answer.add(list);
            return;
        } else if (sum < target) {
            for (int i : ints) {
                List<Integer> newList = new ArrayList<>(list);
                newList.add(i);
                dfs(ints, target, newList, answer);
            }
        }
    }
//    }


    private String[][] nums = {
            {},
            {},
            {"a", "b", "c"},
            {"d", "e", "f"},
            {"g", "h", "i"},
            {"j", "k", "l"},
            {"m", "n", "o"},
            {"p", "q", "r", "s"},
            {"t", "u", "v"},
            {"w", "x", "y", "z"}
    };

    public List<String> letterCombinations(String digits) {
        List<String> answers = new ArrayList<>();
        if (digits.length() == 0) {
            return answers;
        }
        dfs(digits, 0, new StringBuilder(), answers);
        return answers;

    }

    private void dfs(String str, int index, StringBuilder sb, List<String> res) {
        if (index == str.length()) {
            res.add(String.valueOf(sb));
            return;
        }
        int c = Integer.parseInt(str.substring(index, index + 1));
        for (int i = 0; i < nums[c].length; i++) {
            sb.append(nums[c][i]);
            dfs(str, index + 1, sb, res);
//            res.add(String.valueOf(sb));
            sb.deleteCharAt(sb.length() - 1);
        }
    }

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

    public static class ListNode {
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

        @Override
        public String toString() {
            String s = "["+val;
            ListNode listNode = next;
            while (listNode!=null){
                s+=","+listNode.val;
                listNode = listNode.next;
            }
            s+="]";

            return "ListNode{" +
                    s+
                    '}';
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



}
