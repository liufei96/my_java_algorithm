package 每日一题;

import java.util.Arrays;

/**
 * 905. 按奇偶排序数组  [ https://leetcode-cn.com/problems/sort-array-by-parity/ ]
 * <p>
 * 给你一个整数数组 nums，将 nums 中的的所有偶数元素移动到数组的前面，后跟所有奇数元素。
 * <p>
 * 返回满足此条件的 任一数组 作为答案。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,1,2,4]
 * 输出：[2,4,3,1]
 * 解释：[4,2,3,1]、[2,4,1,3] 和 [4,2,1,3] 也会被视作正确答案。
 * 示例 2：
 * <p>
 * 输入：nums = [0]
 * 输出：[0]
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 5000
 * 0 <= nums[i] <= 5000
 */
public class Top0905_simple {

    public static void main(String[] args) {
        int[] nums = {3, 1, 2, 4};
        int[] ints = sortArrayByParity(nums);
        System.out.println(Arrays.toString(ints));
    }

    public static int[] sortArrayByParity(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                int tmp = nums[i];
                nums[i] = nums[index];
                nums[index] = tmp;
                index++;
            }
        }
        return nums;
    }
}
