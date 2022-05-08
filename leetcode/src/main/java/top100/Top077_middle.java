package top100;

import java.util.ArrayList;
import java.util.List;

/**
 * 77. 组合  [ https://leetcode-cn.com/problems/combinations/ ]
 * <p>
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * <p>
 * 你可以按 任何顺序 返回答案。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 4, k = 2
 * 输出：
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 * 示例 2：
 * <p>
 * 输入：n = 1, k = 1
 * 输出：[[1]]
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 20
 * 1 <= k <= n
 */
public class Top077_middle {

    public static void main(String[] args) {
        Top077_middle top077_middle = new Top077_middle();
        int n = 4;
        int k = 2;
        List<List<Integer>> combine = top077_middle.combine(n, k);
        System.out.println(combine);
    }

    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> tmp = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        backtracking(n, k, 1);
        return res;
    }

    void backtracking(int n, int k, int start) {
        if (tmp.size() == k) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        // 剪枝
        for (int i = start; i <= n - (k - tmp.size()) + 1; i++) {
            tmp.add(i);
            backtracking(n, k, ++start);
            tmp.remove(tmp.size() - 1);
        }
    }
}
