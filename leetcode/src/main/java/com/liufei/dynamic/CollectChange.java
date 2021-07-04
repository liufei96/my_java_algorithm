package com.liufei.dynamic;

import java.util.Arrays;

/**
 * 凑零钱。
 * <p>
 * LeetCode 322. 零钱兑换  [ https://leetcode-cn.com/problems/coin-change/ ]
 * <p>
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回-1。
 * <p>
 * 你可以认为每种硬币的数量是无限的。
 * <p>
 * 示例1：
 * <p>
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * 示例 2：
 * <p>
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * 示例 3：
 * <p>
 * 输入：coins = [1], amount = 0
 * 输出：0
 * 示例 4：
 * <p>
 * 输入：coins = [1], amount = 1
 * 输出：1
 * 示例 5：
 * <p>
 * 输入：coins = [1], amount = 2
 * 输出：2
 */
public class CollectChange {

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 100;
        System.out.println(coinChange(coins, amount));
        System.out.println(coinChange2(coins, amount));
    }

    /**
     * 暴力递归。如果不去冲，复杂度就是 2 ^ n次方，是很恐怖的
     *
     * @param coins
     * @param amount
     * @return
     */
    // coins 中是可选硬币面值，amount 是目标金额
    public static int coinChange(int[] coins, int amount) {
        if (coins.length == 0) {
            return -1;
        }
        int[] memo = new int[amount + 1];
        return dp(coins, amount, memo);
    }

    public static int dp(int[] coins, int amount, int[] memo) {
        int subproblem;
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        if (memo[amount] != 0) {
            return memo[amount];
        }
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            subproblem = dp(coins, amount - coin, memo);
            if (subproblem == -1) {
                continue;
            }
            res = Math.min(res, subproblem + 1);
        }
        memo[amount] = res != Integer.MAX_VALUE ? res : -1;
        return memo[amount];
    }


    /**
     * 3、dp 数组的迭代解法
     *
     * PS：为啥 dp 数组初始化为 amount + 1 呢，因为凑成 amount 金额的硬币数最多只可能等于 amount（全用 1 元面值的硬币），所以初始化为 amount + 1 就相当于初始化为正无穷，便于后续取最小值。
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange2(int[] coins, int amount) {
        if (coins.length == 0) {
            return -1;
        }
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int coin : coins) {
                if (i - coin < 0) {
                    continue;
                }
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}
