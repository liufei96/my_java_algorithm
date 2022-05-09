package 每日一题;

import java.util.Arrays;

/**
 * 942. 增减字符串匹配  [ https://leetcode.cn/problems/di-string-match/ ]
 *
 * 由范围 [0,n] 内所有整数组成的 n + 1 个整数的排列序列可以表示为长度为 n 的字符串 s ，其中:
 *
 * 如果 perm[i] < perm[i + 1] ，那么 s[i] == 'I'
 * 如果 perm[i] > perm[i + 1] ，那么 s[i] == 'D'
 * 给定一个字符串 s ，重构排列 perm 并返回它。如果有多个有效排列perm，则返回其中 任何一个 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "IDID"
 * 输出：[0,4,1,3,2]
 * 示例 2：
 *
 * 输入：s = "III"
 * 输出：[0,1,2,3]
 * 示例 3：
 *
 * 输入：s = "DDI"
 * 输出：[3,2,0,1]
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 105
 * s 只包含字符 "I" 或 "D"
 */
public class Top0942_simple {

    public static void main(String[] args) {
        String s = "IDID";
        int[] ints = diStringMatch(s);
        System.out.println(Arrays.toString(ints));
    }

    /**
     * 依次开始，遇到连续D，一直，交换，效率低
     * @param s
     * @return
     */
    public static int[] diStringMatch2(String s) {
        int[] arr = new int[s.length() + 1];
        for (int i = 1; i < arr.length; i++) {
            arr[i] = i;
            int index = i - 1;
            while (index >= 0 && s.charAt(index) == 'D') {
                int tmp = arr[index + 1];
                arr[index + 1] = arr[index];
                arr[index] = tmp;
                index--;
            }
        }
        return arr;
    }

    /**
     * 降序的时候，从最大处开始
     * @param s
     * @return
     */
    public static int[] diStringMatch(String s) {
        int n = s.length(), lo = 0, hi = n;
        int[] perm = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            perm[i] = s.charAt(i) == 'I' ? lo++ : hi--;
        }
        // 最后剩下一个数，此时 lo == hi
        perm[n] = lo;
        return perm;
    }
}
