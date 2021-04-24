package com.liufei.calculator;

import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;

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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int result = calculate(input);
        System.out.println(result);
    }


    public static int calculate(String s) {
        s = s.replace(" ", "");
        String[] split = s.split("");
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < split.length; i++) {
            String str = split[i];
            if (!isTrueSymbol(str) && !isNumber(str)) {
                continue;
            }
            switch (str) {
                case "+":
                case "-":
                case "*":
                case "/":
                    stack.add(str);
                    break;
                default:
                    // 判断前一个数字是不是
                    if (stack.size() > 0) {
                        String pre = stack.peek();
                        if (isNumber(pre)) {
                            stack.add(stack.pop() + str);
                            break;
                        }
                    }
                    // 说明是数字，不是符号
                    if (stack.size() != 0 && stack.size() % 2 == 0) {
                        String a = stack.pop();
                        if (a.equals("*")) {
                            String b = stack.pop();
                            stack.add(String.valueOf(Integer.parseInt(b) * Integer.parseInt(str)));
                        } else if (a.equals("/")) {
                            String b = stack.pop();
                            stack.add(String.valueOf(Integer.parseInt(b) / Integer.parseInt(str)));
                        } else {
                            stack.add(a);
                            stack.add(str);
                        }
                    } else {
                        stack.add(str);
                    }
            }

            // 看是不是最后一个元素
            if (i == split.length - 1 && stack.size() >= 3) {
                String symbol = stack.get(1);
                int result = Integer.parseInt(stack.get(0));
                for (int j = 2; j < stack.size(); j++) {
                    String s1 = stack.get(i);
                    if (j % 2 != 0) {
                        symbol = s1;
                    } else {
                        if (symbol.equals("+")) {
                            result += Integer.parseInt(s1);
                        } else if (symbol.equals("-")) {
                            result -= Integer.parseInt(s1);
                        }
                    }
                }
                stack.clear();
                return result;
            }
        }
        return Integer.parseInt(stack.pop());
    }

    public static boolean isNumber(String str) {
        return Pattern.matches("[0-9]+", str);
    }

    public static boolean isTrueSymbol(String str) {
        return Pattern.matches("[+\\-*/]", str);
    }
}
