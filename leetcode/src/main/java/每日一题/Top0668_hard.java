package 每日一题;

/**
 *
 * 668. 乘法表中第k小的数  [ https://leetcode.cn/problems/kth-smallest-number-in-multiplication-table/ ]
 *
 * 几乎每一个人都用乘法表。但是你能在乘法表中快速找到第k小的数字吗？
 * <p>
 * 给定高度m、宽度n 的一张m * n的乘法表，以及正整数k，你需要返回表中第k小的数字。
 * <p>
 * 例1：
 * <p>
 * 输入: m = 3, n = 3, k = 5
 * 输出: 3
 * 解释:
 * 乘法表:
 * 1 2 3
 * 2 4 6
 * 3 6 9
 * <p>
 * 第5小的数字是 3 (1, 2, 2, 3, 3).
 * 例 2：
 * <p>
 * 输入: m = 2, n = 3, k = 6
 * 输出: 6
 * 解释:
 * 乘法表:
 * 1 2 3
 * 2 4 6
 * <p>
 * 第6小的数字是 6 (1, 2, 2, 3, 4, 6).
 * 注意：
 * <p>
 * m 和n的范围在 [1, 30000] 之间。
 * k 的范围在 [1, m * n] 之间。
 * 通过次数21,392提交次数37,642
 */
public class Top0668_hard {

    public static void main(String[] args) {
        int m = 3, n = 3, k = 5;
        int ans = findKthNumber(m, n, k);
        System.out.println(ans);
    }

    public static int findKthNumber(int m, int n, int k) {
        // 二分查找
        int left = 0, right = m * n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (count(m, n, mid) >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    // 统计乘法表中有多少个小于等于 k 的数目
    static int count(int m, int n, int k) {
        int res = 0;
        // 统计每行小于等于 k 的数目
        for (int i = 1; i <= m; ++i) {
            res += Math.min(k / i, n);
        }
        return res;
    }
}
