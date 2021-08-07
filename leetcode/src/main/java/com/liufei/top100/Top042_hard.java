package com.liufei.top100;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 42. 接雨水  [ https://leetcode-cn.com/problems/trapping-rain-water/ ]
 *
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * 示例 1：
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 *
 * 示例 2：
 *
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 */
public class Top042_hard {

    public static void main(String[] args) {
         Top042_hard top042Hard = new Top042_hard();
         int[] height = { 4,2,0,3,2,5  };
        int trap = top042Hard.trap(height);
        System.out.println(trap);

        System.out.println(top042Hard.trap2(height));
        System.out.println(top042Hard.trap3(height));
        System.out.println(top042Hard.trap4(height));
    }


    /**
     * 暴力法
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int  res = 0;
        for (int i = 0; i < height.length; i++) {
            int leftMax =  0,rightMax =  0;
            // 计算当前柱子左侧的柱子中的最大高度
            for (int j = 0; j <= i; j++) {
                leftMax = Math.max(leftMax, height[j]);
            }
            // 计算当前柱子右侧的柱子中的最大高度
            for (int j = i; j < height.length; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }
            // 结果中累加当前柱子顶部可以储水的高度，
            // 即 当前柱子左右两边最大高度的较小者 - 当前柱子的高度。
            res += Math.min(leftMax, rightMax) - height[i];
        }
        return res;
    }

    /**
     * 动态规划，我们可以发现上面右很多事重复计算的，我们需要记忆下来  时间O(N) 空间O(N)
     *
     * int[][] dp = new int[][];
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        int n = height.length;
        if (n == 0) {
            return 0;
        }
        int[] leftDp = new int[n];
        int[] rightDp = new int[n];
        leftDp[0] = height[0];
        rightDp[n - 1] = height[n - 1];
        for (int i = 1; i < n; i++) {
            leftDp[i] = Math.max(height[i], leftDp[i - 1]);
        }
        for (int i = n - 2; i >= 0; i--) {
            rightDp[i] = Math.max(height[i], rightDp[i + 1]);
        }
        int  res = 0;
        for (int i = 1; i < n - 1; i++) {
            // 结果中累加当前柱子顶部可以储水的高度，
            // 即 当前柱子左右两边最大高度的较小者 - 当前柱子的高度。
            res += Math.min(leftDp[i], rightDp[i]) - height[i];
        }
        return res;
    }

    /**
     * 双指针
     * @param height
     * @return
     */
    public int trap3(int[] height) {
        int n = height.length;
        int  ans = 0, left = 0, right = n - 1,  leftMax = 0, rightMax = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (height[left] < height[right]) {
                ans += leftMax - height[left];
                ++left;
            } else {
                ans += rightMax - height[right];
                --right;
            }
        }
        return ans;
    }


    /**
     * 单调栈
     * 除了计算并存储每个位置两边的最大高度以外，也可以用单调栈计算能接的雨水总量。
     *
     * 维护一个单调栈，单调栈存储的是下标，满足从栈底到栈顶的下标对应的数组 {height}height 中的元素递减。
     *
     * 这道题目可以用单调栈来做。单调栈就是比普通的栈多一个性质，即维护栈内元素单调。
     *
     * 比如当前某个单调递减的栈的元素从栈底到栈顶分别是：[10, 9, 8, 3, 2]，如果要入栈元素5，需要把栈顶元素pop出去，直到满足单调递减为止，
     * 即先变成[10, 9, 8]，再入栈5，就是[10, 9, 8, 5]。
     * @param height
     * @return
     */
    public int trap4(int[] height) {
        int ans = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        int n = height.length;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                // 如果栈顶元素一直相等，那么全都pop出去，只留第一个。
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                int curWidth = i - left - 1;
                int currHeight = Math.min(height[left], height[i]) - height[top];
                ans += curWidth * currHeight;
            }
            stack.push(i);
        }
        return ans;
    }
}
