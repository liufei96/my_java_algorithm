package com.liufei.hash;

import java.util.*;

/**
 * 349. 两个数组的交集  [ https://leetcode-cn.com/problems/intersection-of-two-arrays/ ]
 * <p>
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[9,4]
 * <p>
 * 说明：
 * <p>
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 */
public class Top349_simple {

    public static void main(String[] args) {
        Top349_simple top349_simple = new Top349_simple();
        int[] nums1 = { 1,2,2,1 };
        int[] nums2 = { 2,2 };
        int[] intersection = top349_simple.intersection2(nums1, nums2);
        System.out.println(Arrays.toString(intersection));
    }

    /**
     * 两个集合
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        Set<Integer> set = new HashSet<>();
        Set<Integer> ans = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            if (!set.contains(nums1[i])) {
                set.add(nums1[i]);
            }
        }
        for (int i = 0; i < nums2.length; i++) {
            if (set.contains(nums2[i]) && !ans.contains(nums2[i])) {
                ans.add(nums2[i]);
            }
        }
        int[] ansArray = new int[ans.size()];
        int index = 0;
        for (Integer i : ans) {
            ansArray[index] = i;
            index++;
        }
        return ansArray;
    }

    /**
     * 排序  + 双指针
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] ans = new int[len1 + len2];
        int  index = 0, l1 = 0, l2 =0;
        while (l1 < len1 && l2 < len2) {
           if (nums1[l1] > nums2[l2]) {
               l2++;
           } else if (nums1[l1] < nums2[l2]) {
               l1++;
           } else {
               if (index == 0 || ans[index - 1] != nums1[l1]) {
                   ans[index++] = nums1[l1];
               }
               l1++;
               l2++;
           }
        }
        return Arrays.copyOfRange(ans, 0, index);
    }
}
