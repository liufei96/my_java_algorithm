package top100;

/**
 *   80. 删除有序数组中的重复项 II
 *   https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/
 *
 */
public class Top080_middle {

    public static void main(String[] args) {
        Top080_middle top080_middle = new Top080_middle();
        int[] nums = {1, 1, 1, 2, 3, 4};
        int res = top080_middle.removeDuplicates2(nums);
        System.out.println(res);
    }

    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }
        int i = 0;
        for (int n : nums) {
            if (i < 2 || n > nums[i - 2]) {
                nums[i++] = n;
            }
        }
        return i;
    }

    /**
     * 快慢指针
     * @param nums
     * @return
     */
    public int removeDuplicates2(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }
        int slow = 2, fast = 2;
        while (fast < n) {
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}
