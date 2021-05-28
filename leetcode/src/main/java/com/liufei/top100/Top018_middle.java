package com.liufei.top100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18. 四数之和 [ https://leetcode-cn.com/problems/4sum/ ]
 * <p>
 * 给定一个包含n 个整数的数组nums和一个目标值target，判断nums中是否存在四个元素 a，b，c和 d，使得a + b + c + d的值与target相等？找出所有满足条件且不重复的四元组。
 * <p>
 * 注意：答案中不可以包含重复的四元组。
 * <p>
 * 示例 1：
 * 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [], target = 0
 * 输出：[]
 */
public class Top018_middle {

    public static void main(String[] args) {
        Top018_middle top018 = new Top018_middle();
        int[] nums = {-1, 0, 1, 2, -1, -4};
        int target = -1;
        List<List<Integer>> lists = top018.fourSum(nums, target);
        System.out.println(lists);
    }


    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;
        if (len < 4) {
            return result;
        }
        // 排序
        Arrays.sort(nums);
        int preI = Integer.MAX_VALUE;
        for (int i = 0; i < len - 3; i++) {
            if (nums[i] == preI) {
                continue;
            }
            preI = nums[i];
            int preJ = Integer.MAX_VALUE;
            for (int j = i + 1; j < len - 2; j++) {
                if (nums[j] == preJ) {
                    continue;
                }
                preJ = nums[j];
                // 双指针移动
                List<List<Integer>> list = new ArrayList<>();
                int newTarget = target - nums[i] - nums[j];
                findTwoSumTarget(j + 1, len - 1, nums, newTarget, list);

                if (list.size() < 1) {
                    continue;
                }
                for (List<Integer> integerList : list) {
                    integerList.add(nums[i]);
                    integerList.add(nums[j]);
                    result.add(integerList);
                }
            }
        }
        return result;
    }

    private void findTwoSumTarget(int l, int r, int[] nums, int target, List<List<Integer>> lists) {
        // 去重
        int right = Integer.MAX_VALUE;
        while (l < r) {
            if (nums[l] + nums[r] == target) {
                if (nums[r] != right) {
                    lists.add(new ArrayList<>(Arrays.asList(nums[l], nums[r])));
                    right = nums[r];
                }
                r--;
                l++;
            } else if (nums[l] + nums[r] > target) {
                r--;
            } else {
                l++;
            }
        }
    }
}
