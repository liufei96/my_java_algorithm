package 每日一题;

import java.util.ArrayList;
import java.util.List;

/**
 * 442. 数组中重复的数据 [ https://leetcode-cn.com/problems/find-all-duplicates-in-an-array/ ]
 *
 * 给你一个长度为 n 的整数数组 nums ，其中 nums 的所有整数都在范围 [1, n] 内，且每个整数出现 一次 或 两次 。请你找出所有出现 两次 的整数，并以数组形式返回。
 *
 * 你必须设计并实现一个时间复杂度为 O(n) 且仅使用常量额外空间的算法解决此问题。
 *
 *
 * 示例 1：
 *
 * 输入：nums = [4,3,2,7,8,2,3,1]
 * 输出：[2,3]
 * 示例 2：
 *
 * 输入：nums = [1,1,2]
 * 输出：[1]
 * 示例 3：
 *
 * 输入：nums = [1]
 * 输出：[]
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= n <= 105
 * 1 <= nums[i] <= n
 * nums 中的每个元素出现 一次 或 两次
 *
 */
public class Top0442_middle {

    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        int x = 4 ^ 0;
        System.out.println(x);
        System.out.println(x ^ 4);
        System.out.println();
        List<Integer> duplicates = findDuplicates2(nums);
        System.out.println(duplicates);
    }

    /**
     * 方法二：使用正负号作为标记
     *
     * @param nums
     * @return
     */
    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int num = Math.abs(nums[i]);
            if (nums[num - 1] > 0) {
                nums[num - 1] *= -1;
            } else {
                ans.add(num);
            }
        }
        return ans;
    }

    /**
     * 方法一：将元素交换到对应的位置
     *
     * @param nums
     * @return
     */
    public static List<Integer> findDuplicates2(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 交换
            while (nums[i] != nums[nums[i] - 1]) {
                int tmp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = tmp;
            }
        }
        for (int i = 0; i < n; i++) {
            // 交换完成之后，还不等于，就是说明重复了
            if (i != nums[i] - 1) {
                ans.add(nums[i]);
            }
        }
        return ans;
    }
}
