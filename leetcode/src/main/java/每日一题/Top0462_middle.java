package 每日一题;

import java.util.Arrays;

/**
 * 462. 最少移动次数使数组元素相等 II   [ https://leetcode.cn/problems/minimum-moves-to-equal-array-elements-ii/ ]
 * <p>
 * 给你一个长度为 n 的整数数组 nums ，返回使所有数组元素相等需要的最少移动数。
 * <p>
 * 在一步操作中，你可以使数组中的一个元素加 1 或者减 1 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：2
 * 解释：载请注明出处。
 * 只需要两步操作（每步操作指南使一个元素加 1 或减 1）：
 * [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
 * 示例 2：
 * <p>
 * 输入：nums = [1,10,2,9]
 * 输出：16
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == nums.length
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * <p>
 */
public class Top0462_middle {

    public static void main(String[] args) {
        int[] nums = {1, 0, 0, 8, 6};
        int i = minMoves2(nums);
        System.out.println(i);
    }

    public static int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int i = 0, j = nums.length - 1, step = 0;
        while (i < j) {
            step += nums[j--] - nums[i++];
        }
        return step;
    }

    public static int minMoves(int[] nums) {
        // 关键是找到中位数
        Arrays.sort(nums);
        int n = nums.length, step = 0, mid = nums[n / 2];
        for (int i = 0; i < n; i++) {
            step += Math.abs(nums[i] - mid);
        }
        return step;
    }
}
