package com.liufei.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 503. 下一个更大元素 II      [ https://leetcode-cn.com/problems/next-greater-element-ii/ ]
 *
 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。
 * 数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 *
 * 示例 1:
 *
 * 输入: [1,2,1]
 * 输出: [2,-1,2]
 * 解释: 第一个 1 的下一个更大的数是 2；
 * 数字 2 找不到下一个更大的数；
 * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
 */
public class Top0503_middle {

    public static void main(String[] args) {
        int[] nums = {5, 4, 3, 2, 1};
        int[] ans = nextGreaterElements(nums);
        System.out.println(Arrays.toString(ans));
    }


    /**
     * 讲两个nums数组拼接在一起，使用单调栈计算出每一个元素的下一个最大值，最后再把结果集即result数组resize到原数组大小就可以了。
     *
     * @param nums
     * @return
     */
    public static int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n * 2];
        Arrays.fill(ans, -1);
        int[] newNums = new int[n * 2];
        System.arraycopy(nums, 0, newNums, 0, n);
        System.arraycopy(nums, 0, newNums, n, n);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < newNums.length; i++) {
            while (!stack.isEmpty() && newNums[stack.peek()] < newNums[i]) {
                int preIndex = stack.pop();
                ans[preIndex] = newNums[i];
            }
            stack.push(i);
        }
        return Arrays.copyOfRange(ans, 0, n);
    }


    public static int[] nextGreaterElements2(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < 2 * n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % n]) {
                int preIndex = stack.pop();
                ans[preIndex] = nums[i % n];
            }
            stack.push(i % n);
        }
        return ans;
    }

}
