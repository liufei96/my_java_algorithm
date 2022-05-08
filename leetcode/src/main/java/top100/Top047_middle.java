package top100;

import java.util.*;

/**
 * 47. 全排列 II  [ https://leetcode-cn.com/problems/permutations-ii/ ]
 * <p>
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 * [1,2,1],
 * [2,1,1]]
 */
public class Top047_middle {

    public static void main(String[] args) {
        Top047_middle top047Middle = new Top047_middle();
        int[] nums = {1, 1, 2};
        List<List<Integer>> res = top047Middle.permuteUnique(nums);
        System.out.println(res);

        System.out.println(top047Middle.permuteUnique2(nums));
    }

    Set<List<Integer>> res = new HashSet<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        // 保存值
        List<Integer> trace = new ArrayList<>();
        // 保存索引。索引不能重复，去掉了一部分数据
        List<Integer> index = new ArrayList<>();
        backtrace(nums, trace, index);
        return new ArrayList<>(res);
    }

    /**
     * 这个效率还是有点、低
     *
     * @param nums
     * @param trace
     * @param index
     */
    void backtrace(int[] nums, List<Integer> trace, List<Integer> index) {
        if (nums.length == trace.size()) {
            // 这里需要重新new一个数组
            ArrayList<Integer> integers = new ArrayList<>(trace);
            res.add(integers);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (index.contains(i) && !res.contains(trace)) {
                continue;
            }
            index.add(i);
            trace.add(nums[i]);
            backtrace(nums, trace, index);
            // 取消选择。因为这里面是把所有的元素都移除了
            trace.remove(trace.size() - 1);
            index.remove(index.size() - 1);
        }
    }


    boolean[] vis;

    public List<List<Integer>> permuteUnique2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // 保存值
        List<Integer> trace = new ArrayList<>();
        // 先排序
        Arrays.sort(nums);
        vis = new boolean[nums.length];
        backtrace2(nums, res, trace);
        return res;
    }

    void backtrace2(int[] nums, List<List<Integer>> res, List<Integer> trace) {
        if (trace.size() == nums.length) {
            res.add(new ArrayList<>(trace));
        }
        for (int i = 0; i < nums.length; i++) {
            // 主要是这个逻辑
            if (vis[i] || (i > 0 && nums[i] == nums[i - 1] && !vis[i - 1])) {
                continue;
            }
            trace.add(nums[i]);
            vis[i] = true;
            backtrace2(nums, res, trace);
            // 取消选中
            trace.remove(trace.size() - 1);
            vis[i] = false;
        }
    }
}
