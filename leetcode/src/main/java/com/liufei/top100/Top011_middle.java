package com.liufei.top100;

/**
 * 11. 盛最多水的容器 [https://leetcode-cn.com/problems/container-with-most-water/]
 * <p>
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点(i,ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i的两个端点分别为(i,ai) 和 (i, 0) 。找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
 * <p>
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * <p>
 * 示例 2：
 * <p>
 * 输入：height = [1,1]
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：height = [4,3,2,1,4]
 * 输出：16
 * 示例 4：
 * <p>
 * 输入：height = [1,2,1]
 * 输出：2
 * <p>
 * 提示：
 * <p>
 * n = height.length
 * 2 <= n <= 3 * 104
 * 0 <= height[i] <= 3 * 104
 */
public class Top011_middle {

    public static void main(String[] args) {
        Top011_middle top11 = new Top011_middle();
        int[] height = {1, 2, 1};
        int result = top11.maxArea2(height);
        System.out.println(result);
    }

    /**
     * 暴力法，超出了时间限制
     * O(n^2)
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int result = 0;
        int len = height.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                int min = Math.min(height[i], height[j]);
                result = Math.max(result, (j - i) * min);
            }
        }
        return result;
    }

    /**
     * 滑动窗口，最优解
     * 基本的表达式: area = min(height[i], height[j]) * (j - i) 使用两个指针，值小的指针向内移动，
     * 这样就减小了搜索空间 因为面积取决于指针的距离与值小的值乘积，
     * 如果值大的值向内移动，距离一定减小，而求面积的另外一个乘数一定小于等于值小的值，因此面积一定减小，
     * 而我们要求最大的面积，因此值大的指针不动，而值小的指针向内移动遍历
     *
     * @param height
     * @return
     */
    public int maxArea2(int[] height) {
        int len = height.length;
        if (len <= 1) return -1;
        int left = 0, right = len - 1, res = 0;
        while (left < right) {
            int h = Math.min(height[left], height[right]);
            res = Math.max(res, h * (right - left));
            if (height[left] < height[right])
                left++;
            else
                right--;
        }
        return res;
    }
}
