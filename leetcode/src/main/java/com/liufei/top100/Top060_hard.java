package com.liufei.top100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 60. 排列序列   [ https://leetcode-cn.com/problems/permutation-sequence/ ]
 * <p>
 * 给出集合 [1,2,3,...,n]，其所有元素共有 n! 种排列。
 * <p>
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 * <p>
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 3, k = 3
 * 输出："213"
 * 示例 2：
 * <p>
 * 输入：n = 4, k = 9
 * 输出："2314"
 * 示例 3：
 * <p>
 * 输入：n = 3, k = 1
 * 输出："123"
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 9
 * 1 <= k <= n!
 */
public class Top060_hard {

    public static void main(String[] args) {
        Top060_hard top060Hard = new Top060_hard();
        int n = 3;
        int k = 1;
        String permutation = top060Hard.getPermutation2(n, k);
        System.out.println(permutation);
        for (int i = 0; i < top060Hard.res.size(); i++) {
            System.out.println(Arrays.toString(top060Hard.res.get(i)));
        }
    }

    List<int[]> res = new ArrayList<>();


    /**
     * 通过回溯列举出所有的结果，然后存储起来
     * 耗时：741ms 超过16.9%，效率低
     *
     * @param n
     * @param k
     * @return
     */
    public String getPermutation(int n, int k) {
        for (int i = 1; i <= n; i++) {
            int[] nums = new int[n];
            boolean[] visited = new boolean[n + 1];
            nums[0] = i;
            visited[i] = true;
            backtrace(n, i, 1, nums, visited);
        }
        return Arrays.toString(res.get(k - 1)).replace(",", "").replace(" ", "").substring(1, n + 1);
    }

    public void backtrace(int n, int i, int count, int[] nums, boolean[] visited) {
        if (count == n) {
            res.add(Arrays.copyOf(nums, n));
            return;
        }
        for (int j = 1; j <= n; j++) {
            if (i == j || visited[j]) {
                continue;
            }
            nums[count] = j;
            visited[j] = true;
            count++;
            backtrace(n, i, count, nums, visited);
            count--;
            visited[j] = false;
        }
    }

    /**
     * 优化，我们通过K可以计算出，低k个元素的开头是多少。
     * 82ms
     */
    public String getPermutation2(int n, int k) {
        int num = 1;
        for (int i = 1; i < n; i++) {
            num *= i;
        }
        int i = k % num == 0 ? k / num : k / num + 1;
        int[] nums = new int[n];
        boolean[] visited = new boolean[n + 1];
        nums[0] = i;
        visited[i] = true;
        backtrace(n, i, 1, nums, visited);
        int index = k - (i - 1) * num - 1;
        return Arrays.toString(res.get(index)).replace(",", "").replace(" ", "").substring(1, n + 1);
    }
}
