package com.liufei.swordFinger;

/**
 * 剑指 Offer 13. 机器人的运动范围 [ https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/ ]
 * <p>
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，
 * 它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。
 * 但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 * <p>
 * 示例 1：
 * <p>
 * 输入：m = 2, n = 3, k = 1
 * 输出：3
 * <p>
 * 示例 2：
 * <p>
 * 输入：m = 3, n = 1, k = 0
 * 输出：1
 */
public class Algorithm_013 {

    public static void main(String[] args) {
        Algorithm_013 algorithm013 = new Algorithm_013();
        int m = 2, n = 3, k = 1;
        int res = algorithm013.movingCount(m, n, k);
        System.out.println(res);

        System.out.println(algorithm013.movingCount2(m, n, k));
    }

    /**
     * 深度优先算法
     *
     * @param m
     * @param n
     * @param k
     * @return
     */
    public int movingCount(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        // 从(0, 0)开始，朝右或者朝下
        return dfs(0, 0, m, n, k, visited);
    }


    int dfs(int i, int j, int m, int n, int k, boolean[][] visited) {
        if (i < 0 || j < 0 || i >= m || j >= n || (i / 10 + i % 10 + j / 10 + j % 10) > k || visited[i][j]) {
            return 0;
        }
        visited[i][j] = true;
        return dfs(i, j + 1, m, n, k, visited) + dfs(i + 1, j, m, n, k, visited) + 1;
    }


    /**
     * 递推
     *
     * @param m
     * @param n
     * @param k
     * @return
     */
    public int movingCount2(int m, int n, int k) {
        if (k == 0) {
            return 1;
        }
        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;
        int ans = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if ((i == 0 && j == 0) || (get(i) + get(j) > k)) {
                    continue;
                }
                // 边界问题
                if (i - 1 >= 0) {
                    visited[i][j] = visited[i - 1][j];
                }
                if (j - 1 >= 0) {
                    visited[i][j] |= visited[i][j - 1];
                }
                ans += visited[i][j] ? 1 : 0;
            }
        }
        return ans;
    }

    private int get(int x) {
        int res = 0;
        while (x != 0) {
            res += x % 10;
            x /= 10;
        }
        return res;
    }
}
