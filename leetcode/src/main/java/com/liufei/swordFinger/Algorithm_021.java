package com.liufei.swordFinger;

import java.util.Arrays;

/**
 * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面 [ https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/ ]
 * <p>
 * 示例：
 * <p>
 * 输入：nums = [1,2,3,4]
 * 输出：[1,3,2,4]
 * 注：[3,1,2,4] 也是正确的答案之一。
 */
public class Algorithm_021 {

    public static void main(String[] args) {
        Algorithm_021 algorithm_021 = new Algorithm_021();
        int[] nums = {
                1, 2, 3, 4
        };
        int[] exchange = algorithm_021.exchange(nums);
        System.out.println(Arrays.toString(exchange));
        System.out.println(Arrays.toString(algorithm_021.exchange2(nums)));
    }

    /**
     * 这道题有挺多种解法的，给大家介绍一种我觉得挺好理解的方法：
     * 我们首先统计奇数的个数假设为n,然后新建一个等长数组，然后通过循环判断原数组中的元素为偶数还是奇数。
     * 如果是则从数组下标0的元素开始，把该奇数添加到新数组；如果是偶数则从数组下标为n的元素开始把该偶数添加到新数组中。
     *
     * @param nums
     * @return
     */
    public int[] exchange(int[] nums) {
        int[] new_nums = new int[nums.length];
        int len = nums.length;
        for (int i = 0, j = 0; i + j < len; ) {
            if ((nums[i + j] & 1) == 0) {
                // 偶数
                new_nums[len - j - 1] = nums[i + j];
                j++;
            } else {
                // 奇数
                new_nums[i] = nums[i + j];
                i++;
            }
        }
        return new_nums;
    }

    /**
     * 双指针
     *
     * @param nums
     * @return
     */
    public int[] exchange2(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            if ((nums[left] & 1) == 1) {
                // 奇数
                left++;
                continue;
            }
            if ((nums[right] & 1) == 0) {
                right--;
                continue;
            }
            swap(nums, left++, right--);
        }
        return nums;
    }

    void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }
}
