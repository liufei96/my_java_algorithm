package top100;

import java.util.Arrays;

/**
 * 在排序数组中查找元素的第一个和最后一个位置 [ https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/ ]
 * <p>
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 如果数组中不存在目标值 target，返回[-1, -1]。
 * <p>
 * 进阶：你可以设计并实现时间复杂度为O(log n)的算法解决此问题吗？
 * <p>
 * 示例 1：
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * <p>
 * 示例 2：
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * <p>
 * 示例 3：
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 105
 * -109<= nums[i]<= 109
 * nums是一个非递减数组
 * -109<= target<= 109
 */
public class Top034_middle {

    public static void main(String[] args) {
        Top034_middle top034 = new Top034_middle();
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;
        int[] res = top034.searchRange3(nums, target);
        System.out.println(Arrays.toString(res));
    }

    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};
        for (int i = 0; i < nums.length; i++) {
            if (target == nums[i] && res[0] == -1) {
                res[0] = i;
                continue;
            }
            if (target == nums[i]) {
                res[1] = i;
            }
        }
        if (res[0] != -1 && res[1] == -1) {
            res[1] = res[0];
        }
        return res;
    }

    /**
     * 使用二分法
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange2(int[] nums, int target) {
        int[] res = {-1, -1};
        int n = nums.length;
        if (n == 0) {
            return res;
        }
        if (n == 1 && nums[0] == target) {
            res[0] = res[1] = 0;
            return res;
        }
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        if (nums[r] != target) return res;
        res[0] = r;
        l = 0;
        r = n;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] <= target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        res[1] = l - 1;
        return res;
    }

    /**
     * 官方答案
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange3(int[] nums, int target) {
        int leftIdx = binarySearch(nums, target, true);
        int rightIdx = binarySearch(nums, target, false) - 1;
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return new int[]{leftIdx, rightIdx};
        }
        return new int[]{-1, -1};
    }

    public int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0,right = nums.length - 1,ans = nums.length;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

}
