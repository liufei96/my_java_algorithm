package com.liufei.top100;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 84. 柱状图中最大的矩形 [ https://leetcode-cn.com/problems/largest-rectangle-in-histogram/ ]
 * <p>
 * 示例 1:
 * <p>
 * 输入：heights = [2,1,5,6,2,3]
 * 输出：10
 * 解释：最大的矩形为图中红色区域，面积为 10
 * <p>
 * 示例 2：
 * 输入： heights = [2,4]
 * 输出： 4
 * <p>
 * 提示：
 * <p>
 * 1 <= heights.length <=105
 * 0 <= heights[i] <= 104
 *
 * 趁热打铁再来三道「单调栈」练习题 💪
 *
 * 739. 每日温度
 * 496. 下一个更大元素 I
 * 42. 接雨水 这是三月份的一道打卡题，可以复习一波哈
 */
public class Top084_hard {

    public static void main(String[] args) {
        Top084_hard top084Hard = new Top084_hard();
        int[] heights = {1,1};
        int ans = top084Hard.largestRectangleArea(heights);
        System.out.println(ans);
        System.out.println(top084Hard.largestRectangleArea2(heights));
    }

    /**
     * 暴力法
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        int ans = 0, n = heights.length;
        // 遍历每个柱子，以当前柱子的高度作为矩形的高 h，
        // 从当前柱子向左右遍历，找到矩形的宽度 w。
        for (int i = 0; i < n; i++) {
            int w = 1, h = heights[i], j = i;
            while (--j >= 0 && heights[j] > h) {
                w++;
            }
            j = i;
            while (++j < n && heights[j] >= h) {
                w++;
            }
            ans = Math.max(ans, w * h);
        }
        return ans;
    }

    /**
     * 单调栈求解
     * 单调栈求解 以上暴力写法 Java 可以通过，但我们不妨想一下这里的双重循环是否可以优化？
     * @param heights
     * @return
     */
    public int largestRectangleArea2(int[] heights) {
        int ans = 0;
        // 这里为了代码简便，在柱体数组的头和尾加了两个高度为 0 的柱体。
        int[] tmp = new int[heights.length + 2];
        System.arraycopy(heights, 0, tmp, 1, heights.length);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < tmp.length; i++) {
            // 对栈中柱体来说，栈中的下一个柱体就是其「左边第一个小于自身的柱体」；
            // 若当前柱体 i 的高度小于栈顶柱体的高度，说明 i 是栈顶柱体的「右边第一个小于栈顶柱体的柱体」。
            // 因此以栈顶柱体为高的矩形的左右宽度边界就确定了，可以计算面积🌶️ ～
            while (!stack.isEmpty() && tmp[i] < tmp[stack.peek()]) {
                int h = tmp[stack.pop()];
                ans = Math.max(ans, h * (i - stack.peek() - 1));
            }
            stack.push(i);
        }
        return ans;
    }
}
