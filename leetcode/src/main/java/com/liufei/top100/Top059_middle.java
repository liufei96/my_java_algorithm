package com.liufei.top100;

import java.util.Arrays;

/**
 * 59. 螺旋矩阵 II   [ https://leetcode-cn.com/problems/spiral-matrix-ii/ ]
 * <p>
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 * <p>
 * 示例 1：
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 * <p>
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：[[1]]
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 20
 */
public class Top059_middle {

    public static void main(String[] args) {
        Top059_middle top059_middle = new Top059_middle();
        int n = 4;
        int[][] ints = top059_middle.generateMatrix(n);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(Arrays.toString(ints[i]));
        }
    }


    public int[][] generateMatrix(int n) {
        int[][] nums = new int[n][n];
        int num = 0;
        int left = 0, right = n - 1, top = 0, bottom = n - 1;
        while (num < n * n) {
            // 从左到右
            for (int j = left; j <= right; j++) {
                nums[top][j] = ++num;
            }
            // 从上到下
            if (++top > bottom) break;
            for (int j = top; j <= bottom; j++) {
                nums[j][right] = ++num;
            }
            // 从右到左
            if (--right < left) break;
            for (int j = right; j >= left; j--) {
                nums[bottom][j] = ++num;
            }
            // 从上到下
            if (--bottom < top) break;
            for (int j = bottom; j >= top; j--) {
                nums[j][left] = ++num;
            }
            if (++left > right) break;
        }
        return nums;
    }

    public int[][] generateMatrix2(int n) {
        int[][] nums = new int[n][n];
        int num = 0;
        int left = 0, right = n - 1, top = 0, bottom = n - 1;
        for (int i = 0; i < (n + 1) / 2; i++) {
            // 从左到右
            for (int j = left; j <= right; j++) {
                nums[top][j] = ++num;
            }
            // 从上到下
            top++;
            for (int j = top; j <= bottom; j++) {
                nums[j][right] = ++num;
            }
            // 从右到左
            right--;
            for (int j = right; j >= left; j--) {
                nums[bottom][j] = ++num;
            }
            // 从上到下
            bottom--;
            for (int j = bottom; j >= top; j--) {
                nums[j][left] = ++num;
            }
            left++;
        }
        return nums;
    }
}
