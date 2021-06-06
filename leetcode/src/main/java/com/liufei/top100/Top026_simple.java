package com.liufei.top100;

import com.liufei.model.ListNode;

import java.util.Arrays;

/**
 * 26. 删除有序数组中的重复项 [ https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/ ]
 * <p>
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 说明:
 * <p>
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * <p>
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * <p>
 * 你可以想象内部操作如下:
 * <p>
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 * <p>
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
 * for (int i = 0; i < len; i++) {
 * print(nums[i]);
 * }
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,2]
 * 输出：2, nums = [1,2]
 * 解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [0,0,1,1,1,2,2,3,3,4]
 * 输出：5, nums = [0,1,2,3,4]
 * 解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 3 * 104
 * -104 <= nums[i] <= 104
 * nums 已按升序排列
 */
public class Top026_simple {


    public static void main(String[] args) {
        Top026_simple top026 = new Top026_simple();
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int result = top026.removeDuplicates2(nums);
        System.out.println(result);
    }


    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        int index = 1;
        int tmp = nums[0];
        for (int i = 1; i < len; i++) {
            if (nums[i] > tmp) {
                // 只有i > index时，即中间有间隔的其他数，才交换数值
                if (i > index) {
                    nums[index] = nums[i];
                }
                tmp = nums[index];
                index++;
            }
        }
        return index;
    }

    /**
     * 官方答案，使用快慢指针
     *
     * @param nums
     * @return
     */
    public int removeDuplicates2(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        int fast = 1, slow = 1;
        while (fast < len) {
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}
