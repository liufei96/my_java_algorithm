package com.liufei.top100;

import java.util.Arrays;

/**
 * 16. 最接近的三数之和 [ https://leetcode-cn.com/problems/3sum-closest/ ]
 * <p>
 * 给定一个包括n 个整数的数组nums和 一个目标值target。找出nums中的三个整数，使得它们的和与target最接近。
 * 返回这三个数的和。假定每组输入只存在唯一答案。
 * <p>
 * 示例：
 * <p>
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 */
public class Top016_middle {

    public static void main(String[] args) {
        Top016_middle top16 = new Top016_middle();
        int[] nums = {-1, 2, 1, -4};
        int target = 1;
        int res = top16.threeSumClosest(nums, target);
        System.out.println(res);
    }

    /**
     * 这题相对于15题就简单多了。不需要考虑去重，
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        int closeNum = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < len - 2; i++) {
            int l = i + 1;
            int r = len - 1;
            while (r > l) {
                int threeNum = nums[i] + nums[l] + nums[r];
                if (Math.abs(threeNum - target) < Math.abs(closeNum - target)) {
                    closeNum = threeNum;
                }
                if (threeNum > target) {
                    r--;
                } else if (threeNum < target) {
                    l++;
                } else {
                    // 差距为0，直接返回
                    return target;
                }
            }
        }
        return closeNum;
    }
}
