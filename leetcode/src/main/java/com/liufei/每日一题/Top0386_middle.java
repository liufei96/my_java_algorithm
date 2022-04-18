package com.liufei.每日一题;

import java.util.ArrayList;
import java.util.List;

/**
 * 386. 字典序排数  [https://leetcode-cn.com/problems/lexicographical-numbers/]
 * <p>
 * <p>
 * 给你一个整数 n ，按字典序返回范围 [1, n] 内所有整数。
 * <p>
 * 你必须设计一个时间复杂度为 O(n) 且使用 O(1) 额外空间的算法。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 13
 * 输出：[1,10,11,12,13,2,3,4,5,6,7,8,9]
 * 示例 2：
 * <p>
 * 输入：n = 2
 * 输出：[1,2]
 * 提示：
 * <p>
 * 1 <= n <= 5 * 104
 */
public class Top0386_middle {
    public static void main(String[] args) {
        int n = 13;
        List<Integer> ans = lexicalOrder(n);
        System.out.println(ans);
    }

    public static List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>();
        int num = 1;
        for (int i = 0; i < n; i++) {
            ans.add(num);
            if (num * 10 <= n) {
                num *= 10;
            } else {
                while (num % 10 == 9 || num + 1 > n) {
                    num /= 10;
                }
                num++;
            }
        }
        return ans;
    }

    /**
     * 利用二叉树的原理
     *
     * @param n
     * @return
     */
    public static List<Integer> lexicalOrder2(int n) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= Math.min(9, n); i++) {
            dfs(ans, i, n);
        }
        return ans;
    }

    static void dfs(List<Integer> ans, int tmp, int n) {
        if (tmp > n) {
            return;
        }
        ans.add(tmp);
        for (int j = 0; j <= 9; j++) {
            int num = tmp * 10 + j;
            // 加上这个，快很多
            if (num > n) {
                break;
            }
            dfs(ans, num, n);
        }
    }
}
