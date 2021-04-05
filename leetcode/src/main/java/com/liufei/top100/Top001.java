package com.liufei.top100;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Top001 {


    /**
     * 1. 两数之和
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那两个整数，并返回它们的数组下标。
     * <p>
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * <p>
     * 你可以按任意顺序返回答案。
     * 示例1：
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     * <p>
     * 示例2：
     * 输入：nums = [3,2,4], target = 6
     * 输出：[1,2]
     * <p>
     * 示例3：
     * 输入：nums = [3,3], target = 6
     * 输出：[0,1]
     */


    public static void main(String[] args) {
        // 测试入口
        Top001 top001 = new Top001();
        int[] nums = {2,7,11,15};
        int target = 9;
        int[] ints = top001.twoSum(nums, target);
        System.out.println(Arrays.toString(ints));

        System.out.println("=============== 暴力法 ==================");

        int[] ints2 = top001.twoSumViolence(nums, target);
        System.out.println(Arrays.toString(ints2));

    }

    /**
     * 分析：
     * 1. 只要找出一对即可
     * 2. 返回值中的下标没有顺序要求
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        // key存储数组的值，value存储数组的索引
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int remain = target - nums[i];
            if (map.containsKey(remain) && map.get(remain) != i) {
                return new int[]{map.get(remain), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[0];
    }

    /**
     * 暴力方法，双重for循环，时间复杂度：O(N^2)，空间复杂度：O(1)
     *
     */
    public int[] twoSumViolence(int[] nums, int target) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }
}
