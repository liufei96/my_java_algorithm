package 动态规划;

/**
 * 712. 两个字符串的最小ASCII删除和 [ https://leetcode-cn.com/problems/minimum-ascii-delete-sum-for-two-strings/ ]
 * <p>
 * 给定两个字符串s1, s2，找到使两个字符串相等所需删除字符的ASCII值的最小和。
 * <p>
 * 示例 1:
 * <p>
 * 输入: s1 = "sea", s2 = "eat"
 * 输出: 231
 * 解释: 在 "sea" 中删除 "s" 并将 "s" 的值(115)加入总和。
 * 在 "eat" 中删除 "t" 并将 116 加入总和。
 * 结束时，两个字符串相等，115 + 116 = 231 就是符合条件的最小和。
 * <p>
 * 示例 2:
 * <p>
 * 输入: s1 = "delete", s2 = "leet"
 * 输出: 403
 * 解释: 在 "delete" 中删除 "dee" 字符串变成 "let"，
 * 将 100[d]+101[e]+101[e] 加入总和。在 "leet" 中删除 "e" 将 101[e] 加入总和。
 * 结束时，两个字符串都等于 "let"，结果即为 100+101+101+101 = 403 。
 * 如果改为将两个字符串转换为 "lee" 或 "eet"，我们会得到 433 或 417 的结果，比答案更大。
 * <p>
 * 注意:
 * <p>
 * 0 < s1.length, s2.length <= 1000。
 * 所有字符串中的字符ASCII值在[97, 122]之间。
 */
public class Leetcode_712_middle {

    public static void main(String[] args) {
        Leetcode_712_middle top712 = new Leetcode_712_middle();
        String s1 = "delete";
        String s2 = "leet";
        int res = top712.minimumDeleteSum(s1, s2);
        System.out.println(res);
        s1 = "delete";
        s2 = "leet";
        System.out.println(top712.minimumDeleteSum2(s1, s2));
    }

    /**

     * @param s1
     * @param s2
     * @return
     */
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        int total_sum = 0;
        for (int i = 0; i < m; ++i) {
            total_sum += s1.charAt(i);
        }
        for (int j = 0; j < n; ++j) {
            total_sum += s2.charAt(j);
        }
        if (m == 0 || n == 0) {
            return total_sum;
        }
        int maxSum = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + s1.charAt(i - 1);
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
                maxSum = Math.max(maxSum, dp[i][j]);
            }
        }
        return total_sum - 2 * maxSum;
    }

    /**
     * 1.s1[i-1] == s2[j-1]，新增的两个字符相等的情况下，没有必要删除之前的结果，因此dp[i][j] = dp[i-1][j-1]
     * 2.s1[i-1] != s2[j-1]，取三者的最小值
     *   (1）保留s2串，删除s1串的字符，dp[i][j] = dp[i-1][j] + s1.charAt(i-1)
     *  （2）保留s1串，删除s2串的字符，dp[i][j] = dp[i][j-1] + s1.charAt(j-1)
     *   (3）删除s1、s2串的字符，dp[i][j] = dp[i-1][j-1] + s1.charAt(i-1) + s2.charAt(j-1)
     *
     * @param s1
     * @param s2
     * @return
     */
    public int minimumDeleteSum2(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        //s2为空字符串，需要删掉s1所有字符
        for (int i = 1; i <= m; ++i) {
            dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
        }
        //s1为空字符串，需要删掉s2所有字符
        for (int j = 1; j <= n; ++j) {
            dp[0][j] = dp[0][j - 1] + s2.charAt(j - 1);
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i][j - 1] + s2.charAt(j - 1), dp[i - 1][j] + s1.charAt(i - 1));
                }
            }
        }
        return dp[m][n];
    }
}
