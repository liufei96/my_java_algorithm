package top100;

/**
 * 27. 移除元素。 [ https://leetcode-cn.com/problems/remove-element/ ]
 * <p>
 * 给你一个数组 nums和一个值 val，你需要 原地 移除所有数值等于val的元素，并返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * <p>
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 说明:
 * <p>
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * <p>
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * <p>
 * 你可以想象内部操作如下:
 * <p>
 * // nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝
 * int len = removeElement(nums, val);
 * <p>
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
 * for (int i = 0; i < len; i++) {
 * print(nums[i]);
 * }
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,2,2,3], val = 3
 * 输出：2, nums = [2,2]
 * 解释：函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。你不需要考虑数组中超出新长度后面的元素。例如，函数返回的新长度为 2 ，而 nums = [2,2,3,3] 或 nums = [2,2,0,0]，也会被视作正确答案。
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [0,1,2,2,3,0,4,2], val = 2
 * 输出：5, nums = [0,1,4,0,3]
 * 解释：函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。注意这五个元素可为任意顺序。你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 100
 * 0 <= nums[i] <= 50
 * 0 <= val <= 100
 */
public class Top027_simple {


    public static void main(String[] args) {
        Top027_simple top027 = new Top027_simple();
        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
        int val = 2;
        int result = top027.removeElement2(nums, val);
        System.out.println(result);
        int[] nums2 = {0, 1, 2, 2, 3, 0, 4, 2};
        System.out.println(top027.removeElement2(nums2, val));
        int[] nums3 = {0, 1, 2, 2, 3, 0, 4, 2};
        System.out.println(top027.removeElement3(nums3, val));
    }


    /**
     * 快慢指针
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int len = nums.length;
        int slowIndex = 0;
        for (int fastIndex = 0; fastIndex < len; fastIndex++) {
            if (nums[fastIndex] != val) {
                nums[slowIndex++] = nums[fastIndex];
            }
        }
        return slowIndex;
    }


    /**
     * 优化
     * 如果要移除的元素恰好在数组的开头，例如序列 [1,2,3,4,5]，当 val 为 1 时，
     * 我们需要把每一个元素都左移一位。注意到题目中说：「元素的顺序可以改变」。
     * 实际上我们可以直接将最后一个元素 5 移动到序列开头，取代元素 1，得到序列 [5,2,3,4]，同样满足题目要求。这个优化在序列中 val 元素的数量较少时非常有效。
     * <p>
     * 实现方面，我们依然使用双指针，两个指针初始时分别位于数组的首尾，向中间移动遍历该序列。
     *
     */
    public int removeElement2(int[] nums, int val) {
        // 这样的方法两个指针在最坏的情况下合起来只遍历了数组一次。与方法一不同的是，方法二避免了需要保留的元素的重复赋值操作。
        int len = nums.length;
        if (len == 0) return 0;
        int left = 0;
        int right = len - 1;
        while (left < right) {
            if (nums[left] == val) {
                nums[left] = nums[right - 1];
                right--;
            } else {
                left++;
            }
        }
        return left;
    }

    /**
     * 暴力法
     * 这个题目暴力的解法就是两层for循环，一个for循环遍历数组元素 ，第二个for循环更新数组。
     * @param nums
     * @param val
     * @return
     */
    public int removeElement3(int[] nums, int val) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            // 发现需要移除的元素，就将数组集体向前移动一位
            if (nums[i] == val) {
                for (int j = i + 1; j < len; j++) {
                    nums[j - 1] = nums[j];
                }
                i--; // 因为下表i以后的数值都向前移动了一位，所以i也向前移动一位
                len--; // 此时数组的大小-1
            }
        }
        return len;
    }
}
