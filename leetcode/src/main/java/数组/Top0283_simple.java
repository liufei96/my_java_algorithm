package 数组;

import java.util.Arrays;

/**
 * 283. 移动零  [ https://leetcode-cn.com/problems/move-zeroes/ ]
 * <p>
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * <p>
 * 说明:
 * <p>
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 */
public class Top0283_simple {

    public static void main(String[] args) {
        Top0283_simple top0283_simple = new Top0283_simple();
        int[] nums = {0, 1, 0, 3, 12};
        top0283_simple.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
        int[] nums2 = {0, 1, 0, 3, 12};
        top0283_simple.moveZeroes2(nums2);
        System.out.println(Arrays.toString(nums2));
    }

    /**
     * 快慢指针
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int slowIndex = 0;
        for (int fastIndex = 0; fastIndex < nums.length; fastIndex++) {
            if (nums[fastIndex] != 0) {
                nums[slowIndex++] = nums[fastIndex];
            }
            if (fastIndex >= slowIndex) {
                nums[fastIndex] = 0;
            }
        }
    }

    /**
     * 双指针
     *
     * @param nums
     */
    public void moveZeroes2(int[] nums) {
        int len = nums.length, left = 0, right = 0;
        while (right < len) {
            if (nums[right] != 0) {
                // 交换位置
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    private void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }
}
