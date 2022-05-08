package top100;

import java.util.*;

/**
 * 15. 三数之和 [ https://leetcode-cn.com/problems/3sum/ ]
 * <p>
 * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = []
 * 输出：[]
 * <p>
 * 示例 3：
 * <p>
 * 输入：nums = [0]
 * 输出：[]
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 */
public class Top015_middle {

    public static void main(String[] args) {
        /**
         * 测试易错用例
         * [-2,0,0,2,2], [0,0,0,0]
         * [-1,-1,0,1]
         */
        Top015_middle top15 = new Top015_middle();
        int[] nums = {-1, -1, 0, 1};
        List<List<Integer>> res = top15.threeSum(nums);
        System.out.println(res);
    }

    /**
     * 注意几点：
     * 1. 去重
     * 2. 0的处理
     * 思路：三个数之和是0，那么只要确定第一个数，后面两个数，使用双向指针去查找，因为是查找所有符合条件的，
     * 记得，查找到一个之后，左右指针各向后(前)一个。注意处理，重复的元素
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length <= 2) {
            return new ArrayList<>();
        }
        // 排序
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        // 记录是元素是0的个数，0的单独处理
        int zeroCount = 0;
        int pre = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            int m = nums[i];
            // 三个数，一定有一个正数，一个负数，当第一个数，大于0时，后面全是正数，不会有三数之和等于0的，直接跳出循环
            if (m > 0) {
                break;
            }
            if (m == 0) {
                zeroCount++;
                continue;
            }
            // 这个是判断m元素有没有重复的。如果有重复的直接跳过。如：{-1,-1,0,1}，当m是第一个-1时，参加查找，当m是第二个-1时，直接跳过，第n个-1也是直接跳过
            if (m == pre) continue;
            pre = m;
            // 使用双指针
            findTarget(i + 1, len - 1, m, nums, res);
        }
        if (zeroCount > 2) {
            res.add(Arrays.asList(0, 0, 0));
        }
        return res;
    }

    public void findTarget(int l, int r, int m, int[] nums, List<List<Integer>> res) {
        int right = Integer.MAX_VALUE;
        while (r > l) {
            int sum = m + nums[l] + nums[r];
            if (sum < 0) {
                l++;
            } else if (sum > 0) {
                r--;
            } else {
                // 保证指针右边找到的值，不是重复的，如果是，则不添加。如：{ -2,0,0,2,2 }
                if (nums[r] != right) {
                    res.add(Arrays.asList(m, nums[l], nums[r]));
                    right = nums[r];
                }
                // 这里不要跳出循环，因为可能还会有别的符号要求的值。如：{ -4, -1, -1, 0, 1, 2 },当m是第一个-1时，左右指针分别是2,5，这个是满足条件的，但是左右指针在3,4时也是满足条件的
                l++;
                r--;
            }
        }
    }
}
