package com.liufei.binarysearch;

/**
 * 704. 二分查找   [ https://leetcode-cn.com/problems/binary-search/ ]
 * <p>
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 * <p>
 * <p>
 * 示例 1:
 * 输入: nums = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 解释: 9 出现在 nums 中并且下标为 4
 * <p>
 * 示例  2:
 * 输入: nums = [-1,0,3,5,9,12], target = 2
 * 输出: -1
 * 解释: 2 不存在 nums 中因此返回 -1
 * <p>
 * 提示：
 * <p>
 * 你可以假设 nums 中的所有元素是不重复的。
 * n 将在 [1, 10000]之间。
 * nums 的每个元素都将在 [-9999, 9999]之间。
 */
public class Top704_simple {

    public static void main(String[] args) {
        Top704_simple top704_simple = new Top704_simple();
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int target = 9;
        int res = top704_simple.search(nums, target);
        System.out.println(res);
        System.out.println(top704_simple.search2(nums, target));
    }

    /**
     * 使用 [left, right]
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        // 当left==right，区间[left, right]依然有效，所以用 <=
        while (left <= right) {
            // 防止溢出 等同于(left + right)/2
            int middle = left + (right - right) / 2;
            if (nums[middle] < target) {
                // target 在右区间，所以[middle + 1, right]
                left = middle + 1;
            } else if (nums[middle] > target) {
                // target 在左区间，所以[left, middle - 1]
                right = middle - 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    /**
     * 使用 [left, right)
     *
     * @param nums
     * @param target
     * @return
     */
    public int search2(int[] nums, int target) {
        int left = 0, right = nums.length;
        // 当left < right，区间[left, right) 所以用 <
        while (left < right) {
            // 防止溢出 等同于(left + right)/2
            int middle = left + (right - right) / 2;
            if (nums[middle] < target) {
                left = middle + 1;
            } else if (nums[middle] > target) {
                right = middle;
            } else {
                return middle;
            }
        }
        return -1;
    }
}
