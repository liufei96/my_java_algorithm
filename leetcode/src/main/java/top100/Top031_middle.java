package top100;

import java.util.Arrays;

/**
 * 31. 下一个排列 [ https://leetcode-cn.com/problems/next-permutation/ ]
 * <p>
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * <p>
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * <p>
 * 必须 原地 修改，只允许使用额外常数空间。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 * <p>
 * 示例 2：
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 * <p>
 * 示例 3：
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 * <p>
 * 示例 4：
 * 输入：nums = [1]
 * 输出：[1]
 */
public class Top031_middle {


    public static void main(String[] args) {
        Top031_middle top031 = new Top031_middle();
        int[] nums = {4, 5, 2, 6, 3, 1};
        top031.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 题干的意思是：找出这个数组排序出的所有数中，刚好比当前数大的那个数
     * 比如当前 nums = [1,2,3]。这个数是123，找出1，2，3这3个数字排序可能的所有数，排序后，比123大的那个数 也就是132
     * 如果当前 nums = [3,2,1]。这就是1，2，3所有排序中最大的那个数，那么就返回1，2，3排序后所有数中最小的那个，也就是1，2，3 -> [1,2,3]
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        // 一次遍历找到需要转换的节点
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        if (i >= 0) {
            int j = nums.length - 1;
            // 这次遍历找到，需要交换的数字
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }

        reverse(nums, i + 1);
    }


    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }
}
