package com.liufei.dynamic;

/**
 * 343. 整数拆分 [ https://leetcode-cn.com/problems/integer-break/ ]
 *
 */
public class Leetcode_343_middle {

    public static void main(String[] args) {
        Leetcode_343_middle leetcode_343_middle = new Leetcode_343_middle();
        int n = 7;
        int ans = leetcode_343_middle.integerBreak(n);
        System.out.println(ans);
        System.out.println(leetcode_343_middle.integerBreak2(n));
        System.out.println(leetcode_343_middle.integerBreak3(n));
    }


    /**
     * 贪心
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        // 当超过4的时候，一定是3 * 3 > 2 * 4
        if (n == 2) return 1;
        if (n == 3) return 2;
        if (n == 4) return 4;
        int result = 1;
        while (n > 4) {
            result *= 3;
            n -= 3;
        }
        return result * n;
    }

    public int integerBreak2(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n ; i++) {
            for (int j = 0; j < i - 1; j++) {
                //j*(i-j)代表把i拆分为j和i-j两个数相乘
                //j*dp[i-j]代表把i拆分成j和继续把(i-j)这个数拆分，取(i-j)拆分结果中的最大乘积与j相乘
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), dp[i - j] * j));
            }
        }
        return dp[n];
    }

    /**
     * 优化
     * @param n
     * @return
     */
    public int integerBreak3(int n) {
        if (n < 4) return n - 1;
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n ; i++) {
            dp[i] = Math.max(Math.max(2 * (i - 2), dp[i - 2] * 2), Math.max(3 * (i - 3), dp[i - 3] * 3));
        }
        return dp[n];
    }
}
