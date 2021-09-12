package com.liufei.array;

import java.util.*;

/**
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 解释：平方后，数组变为 [16,1,0,9,100]
 * 排序后，数组变为 [0,1,9,16,100]
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums 已按 非递减顺序 排序
 */
public class Top0977_simple {

    public static void main(String[] args) {
        Top0977_simple top0977_simple = new Top0977_simple();
        int[] nums = {-4, -1, 0, 3, 10};
        int[] ints = top0977_simple.sortedSquares(nums);
        System.out.println(Arrays.toString(ints));
        System.out.println(Arrays.toString(top0977_simple.sortedSquares2(nums)));
        System.out.println(Arrays.toString(top0977_simple.sortedSquares3(nums)));
    }

    /**
     * 复杂度分析
     * <p>
     * 时间复杂度：O(nlogn)，其中 n 是数组 nums 的长度。
     * <p>
     * 空间复杂度：O(logn)。除了存储答案的数组以外，我们需要 O(logn) 的栈空间进行排序。
     *
     * @param nums
     * @return
     */

    public int[] sortedSquares(int[] nums) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int num = (int) Math.pow(nums[i], 2);
            res[i] = num;
        }
        Arrays.sort(res);
        return res;
    }

    /**
     * n(0)
     *
     * @param nums
     * @return
     */
    public int[] sortedSquares2(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        int left = 0, right = len - 1, index = len - 1;
        while (left <= right) {
            if (Math.abs(nums[left]) > Math.abs(nums[right])) {
                res[index] = (int) Math.pow(nums[left], 2);
                left++;
            } else if (Math.abs(nums[left]) < Math.abs(nums[right])) {
                res[index] = (int) Math.pow(nums[right], 2);
                right--;
            } else {
                res[index] = (int) Math.pow(nums[right], 2);
                if (left < right) {
                    res[--index] = (int) Math.pow(nums[right], 2);
                }
                left++;
                right--;
            }
            index--;
        }
        return res;
    }

    /**
     * 时间复杂度：O(n)，其中 nn 是数组 nums 的长度。
     * 空间复杂度：O(1)。除了存储答案的数组以外，我们只需要维护常量空间。
     *
     * @param nums
     * @return
     */
    public int[] sortedSquares3(int[] nums) {
        int len = nums.length;
        int[] ans = new int[len];
        for (int i = 0, j = len - 1, pos = len - 1; i <= j; ) {
            if (nums[i] * nums[i] > nums[j] * nums[j]) {
                ans[pos] = nums[i] * nums[i];
                i++;
            } else {
                ans[pos] = nums[j] * nums[j];
                j--;
            }
            pos--;
        }
        return ans;
    }
}
