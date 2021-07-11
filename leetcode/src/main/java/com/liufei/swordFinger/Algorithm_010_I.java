package com.liufei.swordFinger;

/**
 * 剑指 Offer 10- I. 斐波那契数列 [ https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/ ]
 * <p>
 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项。 n<=39
 *
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
 *
 * F(0) = 0, F(1)= 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：1
 * 示例 2：
 *
 * 输入：n = 5
 * 输出：5
 *
 *
 * 提示：
 * 0 <= n <= 100
 */
public class Algorithm_010_I {

    public static void main(String[] args) {
        Algorithm_010_I algorithm_010I = new Algorithm_010_I();
        int n = 5;
        int res = algorithm_010I.Fibonacci(n);
        System.out.println(res);
        System.out.println(algorithm_010I.Fibonacci2(n));

        int[] memo = new int[n + 1];
        System.out.println(algorithm_010I.Fibonacci3(n, memo));

        System.out.println(algorithm_010I.Fibonacci4(n));
    }

    /**
     * 迭代法
     *
     * @param n
     * @return
     */
    int Fibonacci(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int pre = 1, cur = 1;
        for (int i = 3; i <= n; i++) {
            int sum = (pre + cur) % 1000000007;
            pre = cur;
            cur = sum;
        }
        return cur;
    }


    /**
     * 暴力递归
     *
     * @param n
     * @return
     */
    int Fibonacci2(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return Fibonacci(n - 1) + Fibonacci(n - 2);
    }


    /**
     * 递归，带备忘录的递归
     *
     * @param n
     * @return
     */
    int Fibonacci3(int n, int[] memo) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        if (memo[n] != 0) {
            return memo[n];
        }
        memo[n] = Fibonacci(n - 1) + Fibonacci(n - 2);
        return memo[n];
    }

    /**
     * dp数组，迭代法
     *
     * @param n
     * @return
     */
    int Fibonacci4(int n) {
        if (n <= 0) {
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[1] = dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
