package top100;

/**
 * 35. 搜索插入位置 [ https://leetcode-cn.com/problems/search-insert-position/ ]
 * <p>
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 你可以假设数组中无重复元素。
 * <p>
 * 示例 1:
 * 输入: [1,3,5,6], 5
 * 输出: 2
 * <p>
 * 示例2:
 * 输入: [1,3,5,6], 2
 * 输出: 1
 * <p>
 * 示例 3:
 * 输入: [1,3,5,6], 7
 * 输出: 4
 * <p>
 * 示例 4:
 * 输入: [1,3,5,6], 0
 * 输出: 0
 */
public class Top035_simple {

    public static void main(String[] args) {
        Top035_simple top35 = new Top035_simple();
        int[] nums = {1, 3, 5, 6};
        int target = 2;
        int res = top35.searchInsert(nums, target);
        System.out.println(res);
    }

    public int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        return nums.length;
    }
}
