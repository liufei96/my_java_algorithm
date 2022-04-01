package com.liufei.每日一题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 954. 二倍数对数组  [ https://leetcode-cn.com/problems/array-of-doubled-pairs/ ]
 *
 * 给定一个长度为偶数的整数数组 arr，只有对 arr 进行重组后可以满足 “对于每个 0 <= i < len(arr) / 2，都有 arr[2 * i + 1] = 2 * arr[2 * i]” 时，返回 true；否则，返回 false。
 *
 * 示例 1：
 *
 * 输入：arr = [3,1,3,6]
 * 输出：false
 * 示例 2：
 *
 * 输入：arr = [2,1,2,6]
 * 输出：false
 * 示例 3：
 *
 * 输入：arr = [4,-2,2,-4]
 * 输出：true
 * 解释：可以用 [-2,-4] 和 [2,4] 这两组组成 [-2,-4,2,4] 或是 [2,4,-2,-4]
 *
 * 提示：
 *
 * 0 <= arr.length <= 3 * 104
 * arr.length 是偶数
 * -105 <= arr[i] <= 105
 *
 *
 */
public class Top0954_middle {

    public static void main(String[] args) {
        Top0954_middle top0954_middle = new Top0954_middle();
        int[] arr = {2, 4, 0, 0, 8, 1};
        boolean res = top0954_middle.canReorderDoubled2(arr);
        System.out.println(res);
    }

    /**
     * Hash做法 + 排序
     * @param arr
     * @return
     */
    public static boolean canReorderDoubled(int[] arr) {
        // 需要排序，从小打大
        Arrays.sort(arr);
        // key 存储值, value存储个数
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            // 值是0，直接舍掉
            if (arr[i] == 0) {
                continue;
            }
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
            // 注意条件。其梳理必须大于0
            if (map.containsKey(2 * arr[i]) && map.get(2 * arr[i]) > 0) {
                // 存在 2 倍的 值
                map.put(2 * arr[i], map.get(2 * arr[i]) - 1);
                map.put(arr[i], map.get(arr[i]) - 1);
            } else if (arr[i] % 2 == 0 && map.containsKey(arr[i] / 2) && map.get(arr[i] / 2) > 0) {
                // 存在 1 / 2 的值
                map.put(arr[i] / 2, map.get(arr[i] / 2) - 1);
                map.put(arr[i], map.get(arr[i]) - 1);
            }
        }
        for (Integer value : map.values()) {
            if (value != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 回溯算法，超时了
     * 讲这些组合全部排列出来，找出满足条件的，直接返回
     *
     * 但是由于  0 <= arr.length <= 3 * 104   数组的长度太大了。结果超时了
     *
     * @param arr
     * @return
     */
    public boolean canReorderDoubled2(int[] arr) {
        Arrays.sort(arr);
        List<Integer> tmp = new ArrayList<>();
        boolean[] visited = new boolean[arr.length];
        return backTrace(arr, visited, tmp);
    }

    boolean backTrace(int[] arr, boolean[] visited, List<Integer> tmp) {
        if (tmp.size() == arr.length) {
            return true;
        }
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            if (visited[i] || (tmp.size() > 0 && tmp.size() % 2 != 0 && 2 * tmp.get(tmp.size() - 1) != num)) {
                continue;
            }
            tmp.add(num);
            visited[i] = true;
            boolean res = backTrace(arr, visited, tmp);
            if (res) {
                return true;
            }
            visited[i] = false;
            tmp.remove(tmp.size() - 1);
        }
        return false;
    }
}
