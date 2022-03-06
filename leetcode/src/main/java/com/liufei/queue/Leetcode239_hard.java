package com.liufei.queue;

import java.util.*;

/**
 * 239. 滑动窗口最大值  [ https://leetcode-cn.com/problems/sliding-window-maximum/ ]
 * <p>
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * <p>
 * 返回 滑动窗口中的最大值 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * 示例 2：
 * <p>
 * 输入：nums = [1], k = 1
 * 输出：[1]
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * 1 <= k <= nums.length
 */
public class Leetcode239_hard {

    public static void main(String[] args) {
//        int[] nums = {1,3,-1,-3,5,3,6,7};
//        int k = 3;
//        int[] nums = {1, -1};
//        int k = 1;
        int[] nums = {1, 3, 1, 2, 0, 5};
        int k = 3;
        int[] ints = maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(ints));

        LinkedList<Integer> queue = new LinkedList();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.offer(4);
        System.out.println(queue.poll());
        System.out.println(queue);
    }

    /**
     * 使用优先队列
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        // 保存索引
        LinkedList<Integer> queue = new LinkedList();
        int[] result = new int[nums.length - k + 1];
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (!queue.isEmpty() && nums[i] >= nums[queue.peekLast()]) {
                queue.pollLast();
            }
            queue.addLast(i);
            // 当窗口长度大于k时，下次移动的时候删除之前的数值
            if (queue.peek() <= i - k) {
                queue.poll();
            }
            // 窗口长度为k时 再保存当前窗口中最大值
            if (i - k + 1 >= 0) {
                result[i - k + 1] = nums[queue.peek()];
            }
        }
        return result;
    }
}
