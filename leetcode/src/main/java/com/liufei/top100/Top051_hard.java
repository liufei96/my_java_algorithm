package com.liufei.top100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 51. N 皇后  [ https://leetcode-cn.com/problems/n-queens/ ]
 * <p>
 * n皇后问题 研究的是如何将 n个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * 给你一个整数 n ，返回所有不同的n皇后问题 的解决方案。
 * <p>
 * 每一种解法包含一个不同的n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 */
public class Top051_hard {

    public static void main(String[] args) {
        Top051_hard top51 = new Top051_hard();
        int n = 8;
        List<List<String>> res = top51.solveNQueens(n);
        System.out.println(res);
    }

    List<List<String>> res = new ArrayList<>();


    public List<List<String>> solveNQueens(int n) {
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
        backtrace(chess, 0, usedCol, usedSlash, usedBackSlash);
        return res;
    }


    /**
     *     // 路径：chess 中小于 row 的那些行都已经成功放置了皇后
     *     // 选择列表：第 row 行的所有列都是放置皇后的选择
     *     // 结束条件：row 超过 棋盘最后一行
     * @param chess
     * @param row
     * @param usedCol
     * @param usedSlash
     * @param usedBackSlash
     */
    void backtrace(char[][] chess, int row, boolean[] usedCol, boolean[] usedSlash, boolean[] usedBackSlash) {
        if (row == chess.length) {
            res.add(construct(chess));
            return;
        }
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
                backtrace(chess, row + 1, usedCol, usedSlash, usedBackSlash);
                // 撤销选择
                chess[row][col] = '.';
                usedCol[col] = false;
                usedSlash[row - col + chess.length - 1] = false;
                usedBackSlash[col + row] = false;
            }
        }
    }


    private List<String> construct(char[][] chess) {
        // 数组转List
        List<String> path = new ArrayList<>();
        for (char[] chars : chess) {
            path.add(new String(chars));
        }
        return path;
    }
}
