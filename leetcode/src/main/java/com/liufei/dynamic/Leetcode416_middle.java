package com.liufei.dynamic;

/**
 * 416. 分割等和子集 [ https://leetcode-cn.com/problems/partition-equal-subset-sum/ ]
 * <p>
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：数组不能分割成两个元素和相等的子集。
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 */
public class Leetcode416_middle {

    public static void main(String[] args) {
        Leetcode416_middle leetcode416_middle = new Leetcode416_middle();
        int[] nums = {1, 5, 11, 5};
        boolean ans = leetcode416_middle.canPartition(nums);
        System.out.println(ans);
    }


    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        // 为奇数直接返回
        if (sum % 2 != 0) return false;
        int target = sum / 2;
        // 建立背包
        int[][] dp = new int[n][target + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = 0;
        }
        for (int i = nums[0]; i <= target; i++) {
            dp[0][i] = nums[0];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= target; j++) {
                if (j < nums[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nums[i]] + nums[i]);
                }
            }
        }
        return dp[n - 1][target] == target;
    }

    /**
     * 优化
     */
    public boolean canPartition2(int[] nums) {
        if(nums == null || nums.length == 0) return false;
        int n = nums.length;
        int sum = 0;
        for(int num : nums){
            sum += num;
        }
        //总和为奇数，不能平分
        if(sum % 2 != 0) return false;
        int target = sum / 2;
        int[] dp = new int[target + 1];
        for(int i = 0; i < n; i++){
            for(int j = target; j >= nums[i]; j--){
                //物品 i 的重量是 nums[i]，其价值也是 nums[i]
                dp[j] = Math.max(dp[j], dp[j-nums[i]] + nums[i]);
            }
        }
        return dp[target] == target;
    }
}
