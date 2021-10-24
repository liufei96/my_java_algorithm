package com.liufei.dynamic;

/**
 * 背包问题
 */
public class BagQuestion {

    /**
     * 背包最大重量为4。
     * <p>
     * 物品为：
     * <p>
     * 重量	价值
     * 物品0	1	15
     * 物品1	3	20
     * 物品2	4	30
     * <p>
     * 问背包能背的物品最大价值是多少？
     *
     * @param args
     */
    public static void main(String[] args) {
        BagQuestion bagQuestion = new BagQuestion();
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagSize = 4;
        int ans = bagQuestion.testWeightBagProblem(weight, value, bagSize);
        System.out.println(ans);
    }

    public int testWeightBagProblem(int[] weight, int[] value, int bagSize) {
        //定义dp数组：dp[i][j]表示背包容量为j时，前i个物品能获得的最大价值
        int[][] dp = new int[weight.length + 1][bagSize + 1];
        for (int i = 0; i < weight.length; i++) {
            // 当背包的最大重量是0时，存储的物品也是0
            dp[i][0] = 0;
        }
        // 遍历顺序：先遍历物品，再遍历背包容量
        for (int i = 1; i <= weight.length; i++) {
            for (int j = 1; j <= bagSize; j++) {
                if (j < weight[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i - 1]] + value[i - 1]);
                }
            }
        }
        //打印dp数组
        for (int i = 0; i <= weight.length; i++) {
            for (int j = 0; j <= bagSize; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.print("\n");
        }
        return dp[weight.length][bagSize];
    }
}
