package 每日一题;

import java.util.Arrays;

/**
 * 2028. 找出缺失的观测数据 [ https://leetcode-cn.com/problems/find-missing-observations/ ]
 *
 * 现有一份 n + m 次投掷单个 六面 骰子的观测数据，骰子的每个面从 1 到 6 编号。观测数据中缺失了 n 份，你手上只拿到剩余 m 次投掷的数据。幸好你有之前计算过的这 n + m 次投掷数据的 平均值 。
 *
 * 给你一个长度为 m 的整数数组 rolls ，其中 rolls[i] 是第 i 次观测的值。同时给你两个整数 mean 和 n 。
 *
 * 返回一个长度为 n 的数组，包含所有缺失的观测数据，且满足这 n + m 次投掷的 平均值 是 mean 。如果存在多组符合要求的答案，只需要返回其中任意一组即可。如果不存在答案，返回一个空数组。
 *
 * k 个数字的 平均值 为这些数字求和后再除以 k 。
 *
 * 注意 mean 是一个整数，所以 n + m 次投掷的总和需要被 n + m 整除。
 *
 *
 * 示例 1：
 *
 * 输入：rolls = [3,2,4,3], mean = 4, n = 2
 * 输出：[6,6]
 * 解释：所有 n + m 次投掷的平均值是 (3 + 2 + 4 + 3 + 6 + 6) / 6 = 4 。
 * 示例 2：
 *
 * 输入：rolls = [1,5,6], mean = 3, n = 4
 * 输出：[2,3,2,2]
 * 解释：所有 n + m 次投掷的平均值是 (1 + 5 + 6 + 2 + 3 + 2 + 2) / 7 = 3 。
 * 示例 3：
 *
 * 输入：rolls = [1,2,3,4], mean = 6, n = 4
 * 输出：[]
 * 解释：无论丢失的 4 次数据是什么，平均值都不可能是 6 。
 * 示例 4：
 *
 * 输入：rolls = [1], mean = 3, n = 1
 * 输出：[5]
 * 解释：所有 n + m 次投掷的平均值是 (1 + 5) / 2 = 3 。
 *
 * 提示：
 *
 * m == rolls.length
 * 1 <= n, m <= 105
 * 1 <= rolls[i], mean <= 6
 */
public class Top2028_middle {

    public static void main(String[] args) {
        int[] roles = {4, 2, 2, 5, 4, 5, 4, 5, 3, 3, 6, 1, 2, 4, 2, 1, 6, 5, 4, 2, 3, 4, 2, 3, 3, 5, 4, 1, 4, 4, 5, 3, 6, 1, 5, 2, 3, 3, 6, 1, 6, 4, 1, 3};
        int mean = 2, n = 53;
        int[] ints = missingRolls(roles, mean, n);
        System.out.println(Arrays.toString(ints));
    }

    public static int[] missingRolls(int[] rolls, int mean, int n) {
        int len = rolls.length;
        int total = (len + n) * mean;
        int remain = total;
        for (int i = 0; i < len; i++) {
            remain -= rolls[i];
        }
        // 注意：remain < n （不够分，存在0点的） remain > n * 6 (由至少一个分的超过6点)
        if (remain < n || remain > n * 6) {
            return new int[0];
        }
        int[] res = new int[n];
        int avg = remain / n;
        for (int i = 0; i < res.length; i++) {
            res[i] = res[i] + avg;
        }
        remain = remain % n;
        for (int i = 0; i < res.length; i++) {
            // 余下的数，从第一个数开始，直接加上6。不要平均加，直接加到6，更优些
            int num = 6 - res[i];
            if (remain <= num) {
                res[i] = res[i] + remain;
                break;
            } else {
                remain -= 6 - res[i];
                res[i] = 6;
            }
        }
        return res;
    }

    /**
     * 官方答案
     * 时间复杂度：O(n + m)，其中 nn 是缺失的观测数据个数，mm 是数组 \textit{rolls}rolls 的长度，即已知的观测数据个数。需要 O(m) 的时间计算缺失的观测数据之和，需要 O(n) 的时间构造答案。
     * <p>
     * 空间复杂度：O(1)。除了返回值以外，使用的额外空间为 O(1)
     *
     * @param rolls
     * @param mean
     * @param n
     * @return
     */
    public static int[] missingRolls2(int[] rolls, int mean, int n) {
        int m = rolls.length;
        int sum = (m + n) * mean;
        int missingSum = sum;
        for (int i = 0; i < m; i++) {
            missingSum -= rolls[i];
        }
        // remain < n （不够分，存在0点的） remain > n * 6 (由至少一个分的超过6点)
        if (missingSum < n || missingSum > n * 6) {
            return new int[0];
        }
        int[] missing = new int[n];
        int quotient = missingSum / n, remainder = missingSum % n;
        for (int i = 0; i < n; i++) {
            // 注意: 这里剩下的数 remainder，平均分到每个数最多分一个
            missing[i] = quotient + (i < remainder ? 1 : 0);
        }
        return missing;
    }

}
