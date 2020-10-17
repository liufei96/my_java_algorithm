package com.yiyang.sort;

import java.util.Arrays;

/**
 * 十大经典算法
 *
 * @author liuyiyang
 * @date 2020/5/31 10:01 上午
 */
public class SortTotal {

    public static void main(String[] args) {
        int[] arr = {6, 5, 78, 56, 12, 45, 23, 1};
        int[] sort = new BubbleSort().sort(arr);
        System.out.println(Arrays.toString(sort));
    }

}

class BubbleSort implements IArraySort {

    /**
     * 冒泡排序
     * 基本思想：对比相邻的两个元素值，如果满足条件就交换元素值，把较小的元素移到前面，把较大的元素移到后面（交换两个元素的位置），
     * 这样整个数组元素就像气泡一样从底部上升到顶部。
     *
     * @param sourceArray
     * @return
     */
    @Override
    public int[] sort(int[] sourceArray) {
        for (int i = sourceArray.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (sourceArray[j] > sourceArray[j + 1]) {
                    int tmp = sourceArray[j];
                    sourceArray[j] = sourceArray[j + 1];
                    sourceArray[j + 1] = tmp;
                }
            }
        }
        return sourceArray;
    }
}
