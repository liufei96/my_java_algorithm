package 剑指Offer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 剑指 Offer 03. 数组中重复的数字 [ https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/ ]
 * <p>
 * 找出数组中重复的数字。
 * <p>
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 */
public class Algorithm_003 {

    public static void main(String[] args) {
        Algorithm_003 algorithm003 = new Algorithm_003();
        int[] nums = {2, 3, 1, 0, 2, 5, 3};
        int repeatNumber = algorithm003.findRepeatNumber(nums);
        System.out.println(repeatNumber);

        System.out.println(algorithm003.findRepeatNumber2(nums));

        System.out.println(algorithm003.findRepeatNumber3(nums));
    }

    /**
     * [ 速度最快 ]
     * @param nums
     * @return
     */
    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return nums[i];
            }
            set.add(nums[i]);
        }
        return -1;
    }

    /**
     * 排序，排序之和，只需要比较相邻的两个元素即可
     *
     * @param nums
     * @return
     */
    public int findRepeatNumber2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return nums[i];
            }
        }
        return -1;
    }

    /**
     * 一般想不到的：原地置换，将原数组作为哈希表  [ 速度最快 ]
     * <p>
     * 长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内,这样一来,本来这个数组就可以作为一个哈希表,但是其中有某几个元素捣乱了.
     * <p>
     * 那么，对于这个乱序的数组，从头开始遍历，每当遍历到一个数字 nums[i] ,如果他不等于i, (也就是他并不在自己应该在的哈希表的位置).
     * 我们就把他放到应该在的位置去,也就是把他和 nums[nums[i]] 进行交换．
     *
     * @param nums
     * @return
     */
    public int findRepeatNumber3(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
           while (i != nums[i]) {
               if (nums[i] == nums[nums[i]]) {
                   return nums[i];
               }
               int tmp = nums[i];
               nums[i] = nums[tmp];
               nums[tmp] = tmp;
           }
        }
        return -1;
    }
}
