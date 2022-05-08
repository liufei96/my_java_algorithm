package top100;

/**
 * 53. 最大子序和 [ https://leetcode-cn.com/problems/maximum-subarray/ ]
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组[4,-1,2,1] 的和最大，为6 。
 * 示例 2：
 * <p>
 * 输入：nums = [1]
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：nums = [0]
 * 输出：0
 * 示例 4：
 * <p>
 * 输入：nums = [-1]
 * 输出：-1
 * 示例 5：
 * <p>
 * 输入：nums = [-100000]
 * 输出：-100000
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 3 * 104
 * -105 <= nums[i] <= 105
 */
public class Top053_simple {

    public static void main(String[] args) {
        Top053_simple top053 = new Top053_simple();
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int res = top053.maxSubArray(nums);
        System.out.println(res);
        System.out.println(top053.maxSubArray2(nums));
    }

    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        for (int i = 1; i < len; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
        }
        // 得到 nums 的最大子数组
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public int maxSubArray2(int[] nums) {
        int len = nums.length;
        int res = nums[0];
        int dp_0 = nums[0], dp_1 = 0;
        for (int i = 1; i < len; i++) {
            dp_1 = Math.max(nums[i], nums[i] + dp_0);
            dp_0 = dp_1;
            res = Math.max(res, dp_1);
        }
        return res;
    }
}
