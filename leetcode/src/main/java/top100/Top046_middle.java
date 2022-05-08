package top100;

import java.util.ArrayList;
import java.util.List;

/**
 * 46. 全排列 [ https://leetcode-cn.com/problems/permutations/ ]
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 示例 2：
 * <p>
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * 示例 3：
 * <p>
 * 输入：nums = [1]
 * 输出：[[1]]
 */
public class Top046_middle {

    public static void main(String[] args) {
        Top046_middle top046 = new Top046_middle();
        int[] nums = {1, 2, 3};
        List<List<Integer>> res = top046.permute(nums);
        System.out.println(res);
    }


    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        List<Integer> trace = new ArrayList<>();
        backtrace(nums, trace);
        return res;
    }

    /**
     * 至此，我们就通过全排列问题详解了回溯算法的底层原理。当然，这个算法解决全排列不是很高效，应为对链表使用 `contains`
     * 方法需要 O(N) 的时间复杂度。有更好的方法通过交换元素达到目的，但是难理解一些，这里就不写了，有兴趣可以自行搜索一下。
     *
     * 但是必须说明的是，不管怎么优化，都符合回溯框架，而且时间复杂度都不可能低于 O(N!)，
     * 因为穷举整棵决策树是无法避免的。**这也是回溯算法的一个特点，不像动态规划存在重叠子问题可以优化，回溯算法就是纯暴力穷举，复杂度一般都很高**。
     *
     * @param nums
     * @param trace
     */
    void backtrace(int[] nums, List<Integer> trace) {
        if (nums.length == trace.size()) {
            // 这里需要重新new一个数组
            res.add(new ArrayList<>(trace));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (trace.contains(nums[i])) {
                continue;
            }
            trace.add(nums[i]);
            backtrace(nums, trace);
            // 取消选择。因为这里面是把所有的元素都移除了
            trace.remove(trace.size() - 1);
        }
    }
}
