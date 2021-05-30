package com.liufei.top100;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成  [ https://leetcode-cn.com/problems/generate-parentheses/ ]
 *
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 *
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：["()"]
 *
 * 提示：
 * 1 <= n <= 8
 */
public class Top22_middle {

    public static void main(String[] args) {
        Top22_middle top22 = new Top22_middle();
        List<String> strings = top22.generateParenthesis2(3);
        System.out.println(strings);
    }

    /**
     * 暴力法
     *
     * 时间复杂度：O(2^{2n}n)O(2) 对于 2^{2n}2
     * 个序列中的每一个，我们用于建立和验证该序列的复杂度为 O(n)O(n)。
     * *
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList<>();
        generateAll(new char[2 * n], 0, combinations);
        return combinations;
    }


    public void generateAll(char[] current, int pos, List<String> result) {
       if (pos == current.length) {
           if (valid(current)) {
               result.add(new String(current));
           }
       } else {
           current[pos] = '(';
           generateAll(current, pos + 1, result);
           current[pos] = ')';
           generateAll(current, pos + 1, result);
       }
    }

    public boolean valid(char[] current) {
        int balance = 0;
        for (char c: current) {
            if ('(' == c) {
                balance++;
            } else if (')' == c) {
                balance--;
            }
            if (balance < 0) {
                return false;
            }
        }
        return balance == 0;
    }


    public List<String> generateParenthesis2(int n) {
        List<String> result = new ArrayList<>();
        dfs(n, n, new StringBuilder(), result);
        return result;
    }

    public void dfs(int left, int right, StringBuilder cur, List<String> result) {
        if (left == 0 && right == 0) {
            result.add(cur.toString());
            return;
        }

        if (left > 0) { // 如果左括号还剩余的话，可以拼接左括号
            dfs(left - 1, right, cur.append('('), result);
            cur.deleteCharAt(cur.length() - 1);
        }

        if (right > left) { // 如果右括号剩余多于左括号剩余的话，可以拼接右括号
            dfs(left, right - 1, cur.append(')'), result);
            cur.deleteCharAt(cur.length() - 1);
        }
    }
}
