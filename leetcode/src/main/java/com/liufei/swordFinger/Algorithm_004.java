package com.liufei.swordFinger;

/**
 * 二维数组查找
 * <p>
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * <p>
 * 这一道题还是比较简单的，我们需要考虑的是如何做，效率最快。这里有一种很好理解的思路：
 * <p>
 * 矩阵是有序的，从左下角来看，向上数字递减，向右数字递增， 因此从左下角开始查找，当要查找数字比左下角数字大时。右移 要查找数字比左下角数字小时，上移。这样找的速度最快。
 */
public class Algorithm_004 {

    public static void main(String[] args) {
        Algorithm_004 algorithm_004 = new Algorithm_004();
        int target = 5;
        int[][] nums = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
        };
        boolean res = algorithm_004.Find(target, nums);
        System.out.println(res);
    }


    /**
     * 矩阵是有序的，从左下角来看，向上数字递减，向右数字递增， 因此从左下角开始查找，当要查找数字比左下角数字大时。右移 要查找数字比左下角数字小时，上移。这样找的速度最快。
     * @param target
     * @param array
     * @return
     */
    public boolean Find(int target, int[][] array) {
        int row = array.length - 1;
        int column = 0;
        while (row >= 0 && column < array[0].length) {
            if (array[row][column] > target) {
                row--;
            } else if (array[row][column] < target) {
                column++;
            } else {
                return true;
            }
        }
        return false;
    }
}
