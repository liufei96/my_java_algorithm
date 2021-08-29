package com.liufei.top100;


import java.util.ArrayList;
import java.util.List;

/**
 * 54. 螺旋矩阵  [https://leetcode-cn.com/problems/spiral-matrix/]
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 *
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class Top054_middle {

    public static void main(String[] args) {
        Top054_middle top054_middle = new Top054_middle();
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
        };
        List<Integer> ret = top054_middle.spiralOrder(matrix);
        System.out.println(ret);
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new ArrayList<>(0);
        }
        List<Integer> ret = new ArrayList<>(matrix.length * matrix[0].length);
        int rl = 0,rh = matrix.length - 1; // 记录矩形上下边缘
        int cl = 0,ch = matrix[0].length - 1; // 记录矩形的左右边缘
        while (true) {
            for (int i = cl; i <= ch ; i++) {
                ret.add(matrix[rl][i]);  // 从左到右
            }
            if (++rl > rh) break;
            for (int i = rl; i <= rh ; i++) {
                ret.add(matrix[i][ch]);  // 从上到下
            }
            if (--ch < cl) break;
            for (int i = ch; i >= cl ; i--) {
                ret.add(matrix[rh][i]);  // 从右到左
            }
            if (--rh < rl) break;
            for (int i = rh; i >= rl ; i--) {
                ret.add(matrix[i][cl]);  // 从下到上
            }
            if (++cl > ch) break;
        }
        return ret;
    }
}
