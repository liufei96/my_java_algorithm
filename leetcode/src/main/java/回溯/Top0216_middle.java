package 回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * 216. 组合总和 III   [ https://leetcode-cn.com/problems/combination-sum-iii/ ]
 * <p>
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 * <p>
 * 说明：
 * <p>
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 * <p>
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 示例 2:
 * <p>
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 */
public class Top0216_middle {

    public static void main(String[] args) {
        Top0216_middle top0216_middle = new Top0216_middle();
        int k = 3, n = 9;
        List<List<Integer>> lists = top0216_middle.combinationSum3(k, n);
        System.out.println(lists);
    }

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> tmp = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
       backtracking(n, k, 1, 0);
       return res;
    }

    void backtracking(int n, int k, int startIndex, int sum) {
        if (sum > n) {
            return;
        }
        if (tmp.size() == k) {
            if (sum == n) {
                res.add(new ArrayList<>(tmp));
            }
            return;
        }
        for (int i = startIndex; i <= 9 - (k - tmp.size()) + 1; i++) {
            tmp.add(i);
            sum += i;
            backtracking(n, k, ++startIndex, sum);
            tmp.remove(tmp.size() - 1);
            sum -= i;
        }
    }
}
