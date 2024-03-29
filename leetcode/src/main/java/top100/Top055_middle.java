package top100;

/**
 * 55. 跳跃游戏  [ https://leetcode-cn.com/problems/jump-game/ ]
 *
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个下标。
 *
 * 示例1：
 *
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 *
 * 示例2：
 *
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 *
 * 提示：
 *
 * 1 <= nums.length <= 3 * 104
 * 0 <= nums[i] <= 105
 *
 * 相似题目：跳跃II （45题  https://leetcode-cn.com/problems/jump-game-ii/）
 */
public class Top055_middle {

    public static void main(String[] args) {
        Top055_middle top055Middle = new Top055_middle();
        int[] nums = { 2,3,1,1,4 };
        boolean res = top055Middle.canJump(nums);
        System.out.println(res);
    }

    /**
     * 方法一：贪心
     *
     * 我们可以用贪心的方法解决这个问题。
     *
     * 设想一下，对于数组中的任意一个位置 yy，我们如何判断它是否可以到达？根据题目的描述，只要存在一个位置 xx，它本身可以到达，
     * 并且它跳跃的最大长度为 x+nums[x]，这个值大于等于 yy，即 x+nums[x] ≥ y，那么位置 yy 也可以到达。
     *
     * 换句话说，对于每一个可以到达的位置 xx，它使得 x+1,x+2,⋯,x+nums[x] 这些连续的位置都可以到达。
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int n = nums.length - 1;
        int position = n;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] + i >= position) {
                position = i;
            }
        }
        return position == 0;
    }
}
