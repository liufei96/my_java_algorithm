package 每日一题;

import java.util.*;

/**
 *
 * 398. 随机数索引  [ https://leetcode-cn.com/problems/random-pick-index/ ]
 *
 * 给你一个可能含有 重复元素 的整数数组 nums ，请你随机输出给定的目标数字 target 的索引。你可以假设给定的数字一定存在于数组中。
 *
 * 实现 Solution 类：
 *
 * Solution(int[] nums) 用数组 nums 初始化对象。
 * int pick(int target) 从 nums 中选出一个满足 nums[i] == target 的随机索引 i 。如果存在多个有效的索引，则每个索引的返回概率应当相等。
 *
 * 示例：
 *
 * 输入
 * ["Solution", "pick", "pick", "pick"]
 * [[[1, 2, 3, 3, 3]], [3], [1], [3]]
 * 输出
 * [null, 4, 0, 2]
 *
 * 解释
 * Solution solution = new Solution([1, 2, 3, 3, 3]);
 * solution.pick(3); // 随机返回索引 2, 3 或者 4 之一。每个索引的返回概率应该相等。
 * solution.pick(1); // 返回 0 。因为只有 nums[0] 等于 1 。
 * solution.pick(3); // 随机返回索引 2, 3 或者 4 之一。每个索引的返回概率应该相等。
 *
 * 提示：
 *
 * 1 <= nums.length <= 2 * 104
 * -231 <= nums[i] <= 231 - 1
 * target 是 nums 中的一个整数
 * 最多调用 pick 函数 104 次
 *
 */
public class Top0397_middle {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 3, 3};
        Solution solution = new Solution(nums);
        System.out.println(solution.pick(3));
        System.out.println(solution.pick(1));
        System.out.println(solution.pick(3));
    }


    static class Solution {

        Random random;
        Map<Integer, List<Integer>> map = new HashMap<>();

        public Solution(int[] nums) {
            this.random = new Random();
            for (int i = 0; i < nums.length; i++) {
                if (!map.containsKey(nums[i])) {
                    map.put(nums[i], new ArrayList<>());
                }
                map.get(nums[i]).add(i);
            }
        }

        public int pick(int target) {
            List<Integer> indexList = map.get(target);
            return indexList.get(random.nextInt(indexList.size()));
        }
    }

    /**
     * 蓄水池算法
     */
    static class Solution2 {
        int[] nums;
        Random random;

        public Solution2(int[] nums) {
            this.nums = nums;
            random = new Random();
        }

        public int pick(int target) {
            int ans = 0;
            for (int i = 0, cnt = 0; i < nums.length; ++i) {
                if (nums[i] == target) {
                    ++cnt; // 第 cnt 次遇到 target
                    if (random.nextInt(cnt) == 0) {
                        ans = i;
                    }
                }
            }
            return ans;
        }
    }
}
