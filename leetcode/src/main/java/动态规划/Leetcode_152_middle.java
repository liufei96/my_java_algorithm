package 动态规划;

/**
 * 152. 乘积最大子数组 https://leetcode-cn.com/problems/maximum-product-subarray/
 * <p>
 * <p>
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * <p>
 * 测试用例的答案是一个 2-位 整数。
 * <p>
 * 子数组 是数组的连续子序列。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 * <p>
 * 输入: nums = [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 * <p>
 * 提示:
 * <p>
 * 1 <= nums.length <= 2 * 104
 * -10 <= nums[i] <= 10
 * nums 的任何前缀或后缀的乘积都 保证是一个 32-位 整数
 */
public class Leetcode_152_middle {

    public static void main(String[] args) {
        Leetcode_152_middle leetcode_152_middle = new Leetcode_152_middle();
        // int[] nums = {2, 3, -2, 4};
        // int[] nums = {-2, 0, -1};
        // int[] nums = {0, 2};
        // int[] nums = {-3,-1,-1};
        int[] nums = {-2,3,-4};
        int res = leetcode_152_middle.maxProduct(nums);
        System.out.println(res);
    }

    public int maxProduct(int[] nums) {
        int n = nums.length, res = nums[0];
        int[] max = new int[n];
        int[] min = new int[n];
        max[0] = min[0] = nums[0];
        for (int i = 1; i < n; i++) {
            max[i] = nums[i] >= 0 ? Math.max(max[i - 1] * nums[i], nums[i]) : Math.max(min[i - 1] * nums[i], nums[i]);
            min[i] = nums[i] >= 0 ? Math.min(min[i - 1] * nums[i], nums[i]) : Math.min(max[i - 1] * nums[i], nums[i]);
            res = Math.max(res, max[i]);
        }
        return res;
    }
}
