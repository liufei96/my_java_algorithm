package com.liufei.swordFinger;

/**
 * 剑指Office第一题：斐波那契数列
 * <p>
 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项。 n<=39
 */
public class Algorithm_001 {

    public static void main(String[] args) {
        Algorithm_001 algorithm_001 = new Algorithm_001();
        int n = 5;
        int res = algorithm_001.Fibonacci(n);
        System.out.println(res);
        System.out.println(algorithm_001.Fibonacci2(n));

        int[] memo = new int[n + 1];
        System.out.println(algorithm_001.Fibonacci3(n, memo));

        System.out.println(algorithm_001.Fibonacci4(n));
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
            int sum = pre + cur;
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
