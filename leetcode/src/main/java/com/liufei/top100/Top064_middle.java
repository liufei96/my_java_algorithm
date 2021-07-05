package com.liufei.top100;

/**
 * 64. 最小路径和 [ https://leetcode-cn.com/problems/minimum-path-sum/ ]
 * <p>
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * <p>
 * 说明：每次只能向下或者向右移动一步。
 * <p>
 * 示例 1：
 * <p>
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 * <p>
 * 示例 2：
 * <p>
 * 输入：grid = [[1,2,3],[4,5,6]]
 * 输出：12
 * <p>
 * 提示：
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 100
 */
public class Top064_middle {


    int[][] memo;

    public static void main(String[] args) {
        Top064_middle top064_middle = new Top064_middle();
        int[][] grid = {
                {1, 2, 3},
                {4, 5, 6}
        };
        int res = top064_middle.minPathSum(grid);
        System.out.println(res);
        System.out.println(top064_middle.minPathSum2(grid));
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        memo = new int[m + 1][n + 1];
        return dp(grid, m - 1, n - 1);
    }

    /**
     * 时间复杂度和空间复杂度都是 O(MN)
     * <p>
     * 标准的自顶向下动态规划解法。注意：这里是自顶向下，因为递归是反过来的
     *
     * @param grid
     * @param i
     * @param j
     * @return
     */
    int dp(int[][] grid, int i, int j) {
        // base case
        if (i == 0 && j == 0) {
            return grid[0][0];
        }
        // 如果索引出界，返回一个很大的值，
        // 保证在取 min 的时候不会被取到
        if (i < 0 || j < 0) {
            return Integer.MAX_VALUE;
        }
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        memo[i][j] = Math.min(dp(grid, i - 1, j), dp(grid, i, j - 1)) + grid[i][j];
        return memo[i][j];
    }

    /**
     * 到这里，自底向上的迭代解法也搞定了
     * @param grid
     * @return
     */
    public int minPathSum2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];

        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }
}
