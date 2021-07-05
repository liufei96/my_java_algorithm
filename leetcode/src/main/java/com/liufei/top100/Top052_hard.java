package com.liufei.top100;

import java.util.Arrays;

/**
 * 52. N皇后 II [ https://leetcode-cn.com/problems/n-queens-ii/ ]
 * <p>
 * n皇后问题 研究的是如何将 n个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 * <p>
 * 输入：n = 4
 * 输出：2
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：1
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 9
 * 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 */
public class Top052_hard {

    public static void main(String[] args) {
        Top052_hard top52 = new Top052_hard();
        int n = 4;
        int res = top52.totalNQueens(n);
        System.out.println(res);
    }

    public int totalNQueens(int n) {
        char[][] chess = new char[n][n];
        // 初始值为 .
        for (int i = 0; i < n; i++) {
            Arrays.fill(chess[i], '.');
        }
        // 已经不能放置皇后的列（被占用）,每一行只能放一个，这里可以不保存每一行的内容
        boolean[] usedCol = new boolean[n];
        // 已经不能放置皇后的正斜线 , 按右上角到左下角排列 , 共2n-1条
        boolean[] usedSlash = new boolean[2 * n - 1];
        // 已经不能放置皇后的反斜线 , 按左上角到右下角排列 , 共2n-1条
        boolean[] usedBackSlash = new boolean[2 * n - 1];
        return backtrace(chess, 0, usedCol, usedSlash, usedBackSlash);
    }


    /**
     * // 路径：chess 中小于 row 的那些行都已经成功放置了皇后
     * // 选择列表：第 row 行的所有列都是放置皇后的选择
     * // 结束条件：row 超过 棋盘最后一行
     *
     * @param chess
     * @param row
     * @param usedCol
     * @param usedSlash
     * @param usedBackSlash
     */
    int backtrace(char[][] chess, int row, boolean[] usedCol, boolean[] usedSlash, boolean[] usedBackSlash) {
        if (row == chess.length) {
            return 1;
        }
        int count = 0;
        for (int col = 0; col < chess.length; col++) {
            // 对合法选择进行回溯操作
            // 分别检查列，左上方， 右上方是否存在皇后冲突，都不冲突集为合法选择。
            if (!usedCol[col] && !usedSlash[row - col + usedCol.length - 1] && !usedBackSlash[col + row]) {
                chess[row][col] = 'Q';
                usedCol[col] = true;
                // 对坐标为[i,j]的点对应的正斜线和反斜线的索引分别为：row-col+n-1; col+row
                usedSlash[row - col + chess.length - 1] = true;
                usedBackSlash[col + row] = true;
                // 进入下一行
                count += backtrace(chess, row + 1, usedCol, usedSlash, usedBackSlash);
                // 撤销选择
                chess[row][col] = '.';
                usedCol[col] = false;
                usedSlash[row - col + chess.length - 1] = false;
                usedBackSlash[col + row] = false;
            }
        }
        return count;
    }
}
