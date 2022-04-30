package com.liufei.每日一题;

/**
 * 908. 最小差值 I   [ https://leetcode-cn.com/problems/smallest-range-i/ ]
 * <p>
 * 给你一个整数数组 nums，和一个整数 k 。
 * <p>
 * 在一个操作中，您可以选择 0 <= i < nums.length 的任何索引 i 。将 nums[i] 改为 nums[i] + x ，其中 x 是一个范围为 [-k, k] 的整数。对于每个索引 i ，最多 只能 应用 一次 此操作。
 * <p>
 * nums 的 分数 是 nums 中最大和最小元素的差值。
 * <p>
 * 在对 nums 中的每个索引最多应用一次上述操作后，返回 nums 的最低 分数 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1], k = 0
 * 输出：0
 * 解释：分数是 max(nums) - min(nums) = 1 - 1 = 0。
 * 示例 2：
 * <p>
 * 输入：nums = [0,10], k = 2
 * 输出：6
 * 解释：将 nums 改为 [2,8]。分数是 max(nums) - min(nums) = 8 - 2 = 6。
 * 示例 3：
 * <p>
 * 输入：nums = [1,3,6], k = 3
 * 输出：0
 * 解释：将 nums 改为 [4,4,4]。分数是 max(nums) - min(nums) = 4 - 4 = 0。
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 104
 * 0 <= k <= 104
 * 通过次数31,216提交次数43,
 */
public class Top0908_simple {

    public static void main(String[] args) {
        int[] nums = {1, 3, 6};
        int k = 3;
        System.out.println(smallestRangeI2(nums, k));
    }

    /**
     * 可以转换成先求出数组中的最大值和最小值
     * min 和 max
     * <p>
     * 加上 [-k, k] 之后
     * <p>
     * 最大值的范围  [max - k, max + k]
     * <p>
     * 最小值的范围 [min - k, min + k]
     * <p>
     * 然后从二者交集中找到差的最小的值，
     * - 如果有， [max - k, max + k]  - (min + k) >= 0 直接返回结果
     * - 如果没有交集：则结果就是 max - k - (min + k)
     *
     * @param nums
     * @param k
     * @return
     */
    public static int smallestRangeI(int[] nums, int k) {
        int len = nums.length;
        int max = nums[0];
        int min = nums[0];
        for (int i = 1; i < len; i++) {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }
        for (int i = max - k; i <= max + k; i++) {
            // min的集合，直接取最大值，然后拿 max 集合范围的值，减去 min + k , >= 0 值直接返回
            if (i - (min + k) >= 0) {
                return i - min - k;
            }
        }
        return max - k - (min + k);
    }

    public static int smallestRangeI2(int[] nums, int k) {
        int len = nums.length;
        int max = nums[0];
        int min = nums[0];
        for (int i = 1; i < len; i++) {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }
        return max - min <= 2 * k ? 0 : max - min - 2 * k;
    }
}
