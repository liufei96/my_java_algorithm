package com.liufei.每日一题;

/**
 * 713. 乘积小于 K 的子数组  [ https://leetcode-cn.com/problems/subarray-product-less-than-k/ ]
 * <p>
 * 给你一个整数数组 nums 和一个整数 k ，请你返回子数组内所有元素的乘积严格小于 k 的连续子数组的数目。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [10,5,2,6], k = 100
 * 输出：8
 * 解释：8 个乘积小于 100 的子数组分别为：[10]、[5]、[2],、[6]、[10,5]、[5,2]、[2,6]、[5,2,6]。
 * 需要注意的是 [10,5,2] 并不是乘积小于 100 的子数组。
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3], k = 0
 * 输出：0
 * <p>
 * 提示:
 * <p>
 * 1 <= nums.length <= 3 * 104
 * 1 <= nums[i] <= 1000
 * 0 <= k <= 106
 */
public class Top0713_middle {


    public static void main(String[] args) {
        int[] nums = {10, 5, 2, 6};
        int k = 100;
        int ans = numSubarrayProductLessThanK(nums, k);
        System.out.println(ans);
    }

    public static int numSubarrayProductLessThanK2(int[] nums, int k) {
        int ans = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            int tmp = nums[i];
            if (tmp >= k) {
                continue;
            }
            ans++;
            for (int j = i + 1; j < n; j++) {
                tmp *= nums[j];
                if (tmp >= k) {
                    break;
                }
                ans++;
            }
        }
        return ans;
    }

    /**
     * 双指针法，如果一个子串的乘积小于k，那么他的每个子集都小于k，而一个长度为n的数组，他的所有连续子串数量是1+2+...n，但是会和前面的重复。
     * 比如例子中[10, 5, 2, 6]，第一个满足条件的子串是[10]，第二个满足的是[10, 5]，但是第二个数组的子集[10]和前面的已经重复了，
     * 因此我们只需要计算包含最右边的数字的子串数量，就不会重复了，也就是在计算[10, 5]这个数组的子串是，只加入[5]和[10, 5]，而不加入[10]，
     * 这部分的子串数量刚好是r - l + 1
     *
     * @param nums
     * @param k
     * @return
     */
    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        int ans = 0, n = nums.length;
        int tmp = 1, i = 0;
        for (int j = 0; j < n; j++) {
            tmp *= nums[j];
            while (i <= j && tmp >= k) {
                tmp /= nums[i];
                i++;
            }
            ans += j - i + 1;
        }
        return ans;
    }

}
