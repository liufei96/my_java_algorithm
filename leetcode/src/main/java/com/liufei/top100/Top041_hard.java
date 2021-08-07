package com.liufei.top100;

import java.util.Arrays;

/**
 * 41. 缺失的第一个正数 [ https://leetcode-cn.com/problems/first-missing-positive/ ]
 * <p>
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 * <p>
 * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,0]
 * 输出：3
 * <p>
 * 示例 2：
 * 输入：nums = [3,4,-1,1]
 * 输出：2
 * <p>
 * 示例 3：
 * 输入：nums = [7,8,9,11,12]
 * 输出：1
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 5 * 105
 * -231 <= nums[i] <= 231 - 1
 */
public class Top041_hard {

    public static void main(String[] args) {
        Top041_hard top041Hard = new Top041_hard();
        int[] nums = {1,2,0};
//        int res = top041Hard.firstMissingPositive(nums);
//        System.out.println(res);

        System.out.println(top041Hard.firstMissingPositive2(nums));
    }

    public int firstMissingPositive(int[] nums) {
        Arrays.sort(nums);
        if (nums[nums.length - 1] == 1) return 2;
        int res = 1;
        for (int i = 1; i < nums.length; i++) {
            if (1 < nums[i - 1] && i == 1) {
                return res;
            } else if (nums[i] < 0) {
                continue;
            } else if (nums[i] - nums[i - 1] == 1 && i == nums.length - 1) {
                return nums[i] + 1;
            } else if (nums[i - 1] <= 0 && nums[i] > 0 && nums[i] > 1) {
                return res;
            } else if (nums[i] - nums[i - 1] > 1 && nums[i - 1] > 0) {
                return nums[i - 1] + 1;
            } else if (nums[i - 1] == nums[i] && i == nums.length - 1) {
                return nums[i - 1] + 1;
            }
        }
        return res;
    }


    public int firstMissingPositive2(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }
}
