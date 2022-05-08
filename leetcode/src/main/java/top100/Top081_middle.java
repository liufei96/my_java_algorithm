package top100;

/**
 * 81. 搜索旋转排序数组 II  [https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/]
 *
 * 示例 1：
 * 输入：nums = [2,5,6,0,0,1,2], target = 0
 * 输出：true
 *
 * 示例 2：
 * 输入：nums = [2,5,6,0,0,1,2], target = 3
 * 输出：false
 *
 */
public class Top081_middle {

    public static void main(String[] args) {
        Top081_middle top081_middle = new Top081_middle();
        //int[] nums = {1, 0, 1, 1, 1};
        int[] nums = {0, 0, 1, 1, 2, 0};
        int target = 2;
        boolean search = top081_middle.search(nums, target);
        System.out.println(search);
    }


    public boolean search(int[] nums, int target) {
        int n = nums.length;
        if (n == 1) {
            return nums[0] == target;
        }
        int l = 0, r = n;
        while (l < r) {
            // 处理重复的数字
            while (l + 1 < r && nums[l] == nums[l + 1]) {
                l++;
            }
            while (r - 2 >= 0 && nums[r - 1] == nums[r - 2]) {
                r--;
            }
            int mid = (r + l) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[l] <= nums[mid]) {
                // 左边有序
                if (nums[l] <= target && target < nums[mid]) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            } else {
                // 右边有序
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
        }
        return false;
    }
}
