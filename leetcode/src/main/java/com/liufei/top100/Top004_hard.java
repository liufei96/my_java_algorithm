package com.liufei.top100;

public class Top004_hard {


    public static void main(String[] args) {
        int[] num1 = { 1, 3 };
        int[] num2 = { 2, 4 };
        double medianSortedArrays = findMedianSortedArrays(num1, num2);
        System.out.println(medianSortedArrays);
    }

    /**
     * 题目：寻找两个正序数组的中位数
     * 难度：困难
     * <p>
     * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。
     * <p>
     * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
     *
     * <pre>
     *     如：输入：nums1 = [1,3], nums2 = [2]。   输出：2.00000。 解释：合并数组 = [1,2,3] ，中位数 2
     *     如：输入：nums1 = [1,2], nums2 = [3,4]。 输出：2.50000。 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
     *     如：输入：nums1 = [0,0], nums2 = [0,0]。 输出：0.00000
     *     如：输入：nums1 = [], nums2 = [1]。      输出：1.00000
     *     如：输入：nums1 = [2], nums2 = []。      输出：2.00000
     * </pre>
     */


    /**
     * 看到这个时间复杂度，就应该知道肯定需要使用 二分法
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int left = (m + n + 1) / 2;
        int right = (m + n + 2) / 2;
        return (findKth(nums1, 0, nums2, 0, left) + findKth(nums1, 0, nums2, 0, right)) / 2.0;
    }

    /**
     * 查找k的位置
     *
     * @param nums1
     * @param i     nums1的起始位置
     * @param nums2
     * @param j     num2的起始位置
     * @return
     */
    public static int findKth(int[] nums1, int i, int[] nums2, int j, int k) {
        if( i >= nums1.length) return nums2[j + k - 1];//nums1为空数组
        if( j >= nums2.length) return nums1[i + k - 1];//nums2为空数组
        if(k == 1){
            return Math.min(nums1[i], nums2[j]);
        }
        int midVal1 = (i + k / 2 - 1 < nums1.length) ? nums1[i + k / 2 - 1] : Integer.MAX_VALUE;
        int midVal2 = (j + k / 2 - 1 < nums2.length) ? nums2[j + k / 2 - 1] : Integer.MAX_VALUE;
        if(midVal1 < midVal2){
            return findKth(nums1, i + k / 2, nums2, j , k - k / 2);
        }else{
            return findKth(nums1, i, nums2, j + k / 2 , k - k / 2);
        }
    }
}
