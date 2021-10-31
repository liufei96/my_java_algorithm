package com.liufei.top100;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * <p>
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 示例 2：
 * <p>
 * 输入：nums = [0]
 * 输出：[[],[0]]
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 */
public class Top078_middle {

    public static void main(String[] args) {
        Top078_middle top078_middle = new Top078_middle();
        int[] nums = {1, 2, 3};
        List<List<Integer>> subsets = top078_middle.subsets(nums);
        System.out.println(subsets);
        System.out.println(top078_middle.subsets2(nums));
        System.out.println(top078_middle.subsets3(nums));
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        backtrace(nums, ans, new ArrayList<>(), visited, 0);
        return ans;
    }


    void backtrace(int[] nums, List<List<Integer>> ans, List<Integer> trace, boolean[] visited, int start) {
        ans.add(new ArrayList<>(trace));
        for (int i = start; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            trace.add(nums[i]);
            backtrace(nums, ans, trace, visited, ++start);
            visited[i] = false;
            trace.remove(trace.size() - 1);
        }
    }


    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrace2(0, nums, ans, new ArrayList<>());
        return ans;
    }


    void backtrace2(int cur, int[] nums, List<List<Integer>> ans, List<Integer> trace) {
        if (cur == nums.length) {
            ans.add(new ArrayList<>(trace));
            return;
        }
        trace.add(nums[cur]);
        backtrace2(cur + 1, nums, ans, trace);
        trace.remove(trace.size() - 1);
        backtrace2(cur + 1, nums, ans, trace);
    }


    public List<List<Integer>> subsets3(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        for (int i = 0; i < nums.length; i++) {
            int size = ans.size();
            for (int j = 0; j < size; j++) {
                List<Integer> list = new ArrayList<>(ans.get(j));
                list.add(nums[i]);
                ans.add(list);
            }
        }
        return ans;
    }
}
