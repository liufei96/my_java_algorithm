package top100;

import java.util.*;

/**
 * 39. 组合总和  [ https://leetcode-cn.com/problems/combination-sum/ ]
 * <p>
 * 给定一个无重复元素的正整数数组candidates和一个正整数target，找出candidates中所有可以使数字和为目标数target的唯一组合。
 * <p>
 * candidates中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是唯一的。
 * <p>
 * 对于给定的输入，保证和为target 的唯一组合数少于 150 个。
 * <p>
 * 示例1：
 * <p>
 * 输入: candidates = [2,3,6,7], target = 7
 * 输出: [[7],[2,2,3]]
 * 示例2：
 * <p>
 * 输入: candidates = [2,3,5], target = 8
 * 输出: [[2,2,2,2],[2,3,3],[3,5]]
 * 示例 3：
 * <p>
 * 输入: candidates = [2], target = 1
 * 输出: []
 * 示例 4：
 * <p>
 * 输入: candidates = [1], target = 1
 * 输出: [[1]]
 * 示例 5：
 * <p>
 * 输入: candidates = [1], target = 2
 * 输出: [[1,1]]
 * <p>
 * 提示：
 * <p>
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 */
public class Top039_middle {

    public static void main(String[] args) {
        Top039_middle top039Middle = new Top039_middle();
        int[] candidates = {2, 3, 5};
        int target = 8;
        List<List<Integer>> lists = top039Middle.combinationSum(candidates, target);
        System.out.println(lists);
    }

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) return new ArrayList<>();
        Arrays.sort(candidates);
        backtrace(candidates, new ArrayList<>(), target, 0, 0);
        return res;
    }

    void backtrace(int[] candidates, List<Integer> trace, int target, int sum, int begin) {
        if (sum == target) {
            res.add(new ArrayList<>(trace));
            return;
        }
        for (int i = begin; i < candidates.length; i++) {
            int res = sum + candidates[i];
            if (res > target) {
                break;
            }
            trace.add(candidates[i]);
            backtrace(candidates, trace, target, res, i);
            trace.remove(trace.size() - 1);
        }
    }
}
