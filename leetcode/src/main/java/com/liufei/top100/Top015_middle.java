package com.liufei.top100;

import java.util.*;

/**
 * 15. 三数之和 [ https://leetcode-cn.com/problems/3sum/ ]
 * <p>
 * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = []
 * 输出：[]
 * <p>
 * 示例 3：
 * <p>
 * 输入：nums = [0]
 * 输出：[]
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 */
public class Top015_middle {

    public static void main(String[] args) {
        Top015_middle top15 = new Top015_middle();
        int[] nums = {0,0,0,0};
        List<List<Integer>> res = top15.threeSum(nums);
        System.out.println(res);
    }


    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length <= 2) {
            return new ArrayList<>();
        }
        // 排序
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        for (int i = 0; i < len - 1; i++) {
            int m = nums[i];
            if (m > 0) {
                break;
            }
            // 使用双指针
            findTarget(i + 1, len - 1, m, nums, res);
        }
        return res;
    }

    public void findTarget(int l, int r, int m, int[] nums, List<List<Integer>> res) {
        while (r > l) {
            int sum = m + nums[l] + nums[r];
            if (sum < 0) {
                l++;
            } else if (sum > 0) {
                r--;
            } else {
                res.add(Arrays.asList(m, nums[l], nums[r]));
                return;
            }
        }
    }
}
