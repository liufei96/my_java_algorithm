package com.liufei.calculator;

import java.util.Scanner;
import java.util.Stack;

/**
 * leetcode第227题。难度：中等
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * 整数除法仅保留整数部分。
 *
 * 示例 1
 * 输入：s = "3+2*2"
 * 输出：7
 *
 * 示例 2
 * 输入：s = " 3/2 "
 * 输出：1
 *
 * 示例 3：
 * 输入：s = " 3+5 / 2 "
 * 输出：5
 * 提示：
 *
 * 1 <= s.length <= 3 * 105
 * s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
 * s 表示一个 有效表达式
 * 表达式中的所有整数都是非负整数，且在范围 [0, 231 - 1] 内
 * 题目数据保证答案是一个 32-bit 整数
 *
 */
public class Calculator_Leetcode_227 {

    /**
     * 注意几点：
     * 1. 把减法当作加法计算。（去相反数）
     * 2. 乘除法优先级高直接计算
     * 3. 整数不知一位，会 >= 10
     * 4. 表达式没有括号
     * 5. 先做减法，避免int溢出
     * 6. ASCII对照表。加减乘除小于数字。https://tool.oschina.net/commons?type=4
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int result = calculate(input);
        System.out.println(result);
    }


    public static int calculate(String s) {
        // 保存上一个符号
        char sign = '+';
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        // 保存当前数字
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (cur >= '0') {
                // 减去0，防止溢出
                num = num * 10 - '0' + cur;
            }
            if (cur < '0' && cur != ' ' || i == s.length() - 1) {
                // 判断上一个符号是什么
                switch (sign) {
                    case '+': stack.push(num);break;
                    case '-': stack.push(-num);break;
                    case '*':
                        stack.push(stack.pop() * num);break;
                    case '/':
                        stack.push(stack.pop() / num);break;
                }
                // 记住当前符号
                sign = cur;
                num = 0;
            }
        }

        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }


    /**
     * 扩展：优化
     * 1. 将stack换成Deque（双向队列）
     */
}
