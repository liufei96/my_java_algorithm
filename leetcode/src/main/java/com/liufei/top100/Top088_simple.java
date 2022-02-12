package com.liufei.top100;

import java.util.Arrays;

/**
 * 88. 合并两个有序数组
 * https://leetcode-cn.com/problems/merge-sorted-array/
 */
public class Top088_simple {

    public static void main(String[] args) {
        Top088_simple top088_simple = new Top088_simple();
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] num2 = {2, 5, 6};
        int m = 3, n = 3;

//        int[] nums1 = {1};
//        int[] num2 = {};
//        int m = 1, n = 0;

//        int[] nums1 = {0};
//        int[] num2 = {1};
//        int m = 0, n = 1;

//        int[] nums1 = {2, 0};
//        int[] num2 = {1};
//        int m = 1, n = 1;

        top088_simple.merge3(nums1, m, num2, n);
        System.out.println(Arrays.toString(nums1));
    }

    /**
     * 方法一：直接合并后排序
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }

    /**
     * 方法二：双指针
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] rest = new int[m + n];
        int i, j;
        for (i = 0, j = 0; i + j < m + n; ) {
            if (i < m && j < n) {
                if (nums1[i] < nums2[j]) {
                    rest[i + j] = nums1[i++];
                } else {
                    rest[i + j] = nums2[j++];
                }
            } else if (i < m) {
                rest[i + j] = nums1[i++];
            } else {
                rest[i + j] = nums2[j++];
            }
        }
        System.arraycopy(rest, 0, nums1, 0, n + m);
    }

    /**
     * 方法三：逆向双指针
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge3(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1, p2 = n - 1;
        int tail = m + n - 1;
        int cur;
        while (p1 >= 0 || p2 >= 0) {
            if (p1 == -1) {
                cur = nums2[p2--];
            } else if (p2 == -1) {
                cur = nums1[p1--];
            } else if (nums1[p1] > nums2[p2]) {
                cur = nums1[p1--];
            } else {
                cur = nums2[p2--];
            }
            nums1[tail--] = cur;
        }
    }
}
