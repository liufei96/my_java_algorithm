package com.liufei.top100;

import java.util.*;

/**
 * 40. 组合总和 II [ https://leetcode-cn.com/problems/combination-sum-ii/ ]
 * <p>
 * 给定一个数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
 * <p>
 * candidates中的每个数字在每个组合中只能使用一次。
 * <p>
 * 注意：解集不能包含重复的组合。
 * <p>
 * 示例1:
 * <p>
 * 输入: candidates =[10,1,2,7,6,1,5], target = 8,
 * 输出:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 * 示例2:
 * <p>
 * 输入: candidates =[2,5,2,1,2], target =5,
 * 输出:
 * [
 * [1,2,2],
 * [5]
 * ]
 * <p>
 * 提示:
 * <p>
 * 1 <= candidates.length <= 100
 * 1 <= candidates[i] <= 50
 * 1 <= target <= 30
 */
public class Top040_middle {

    public static void main(String[] args) {
        Top040_middle top040Middle = new Top040_middle();
        int[] candidates = {2, 5, 2, 1, 2};
        int target = 5;
        List<List<Integer>> lists = top040Middle.combinationSum2(candidates, target);
        System.out.println(lists);
    }

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0)
            return new ArrayList<>();
        Arrays.sort(candidates);
        backtrace(candidates, new ArrayList<>(), target, 0);
        return res;
    }

    void backtrace(int[] candidates, List<Integer> trace, int target, int begin) {
        if (0 == target) {
            res.add(new ArrayList<>(trace));
            return;
        }
        for (int i = begin; i < candidates.length; i++) {
            if (candidates[i] > target) {
                break;
            }
            // 注意这里的去重，如果利用set去重，效率会降低
            if (i > begin && candidates[i] == candidates[i - 1]) {
                continue;
            }
            trace.add(candidates[i]);
            backtrace(candidates, trace, target - candidates[i], i + 1);
            trace.remove(trace.size() - 1);
        }
    }
}
