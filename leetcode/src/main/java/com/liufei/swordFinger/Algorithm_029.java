package com.liufei.swordFinger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer 29. 顺时针打印矩阵 [https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/]
 * <p>
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 * <p>
 * 示例 1：
 * <p>
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 示例 2：
 * <p>
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class Algorithm_029 {

    public static void main(String[] args) {
        Algorithm_029 algorithm029 = new Algorithm_029();
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
        };
        int[] ints = algorithm029.spiralOrder(matrix);
        System.out.println(Arrays.toString(ints));
    }

    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        int[] ret = new int[matrix.length * matrix[0].length];
        int rl = 0,rh = matrix.length - 1; // 记录矩形上下边缘
        int cl = 0,ch = matrix[0].length - 1; // 记录矩形的左右边缘
        int index = 0;
        while (true) {
            for (int i = cl; i <= ch ; i++) {
                ret[index] = matrix[rl][i];  // 从左到右
                index++;
            }
            if (++rl > rh) break;
            for (int i = rl; i <= rh ; i++) {
                ret[index] = matrix[i][ch];  // 从上到下
                index++;
            }
            if (--ch < cl) break;
            for (int i = ch; i >= cl ; i--) {
                ret[index] = matrix[rh][i];  // 从右到左
                index++;
            }
            if (--rh < rl) break;
            for (int i = rh; i >= rl ; i--) {
                ret[index] = matrix[i][cl];  // 从下到上
                index++;
            }
            if (++cl > ch) break;
        }
        return ret;
    }
}
