package com.liufei.dynamic;

/**
 * 583. 两个字符串的删除操作 [ https://leetcode-cn.com/problems/delete-operation-for-two-strings/  ]
 * <p>
 * 给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。
 * <p>
 * 示例：
 * <p>
 * 输入: "sea", "eat"
 * 输出: 2
 * 解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
 * <p>
 * 提示：
 * <p>
 * 给定单词的长度不超过500。
 * 给定单词中的字符只含有小写字母。
 */
public class Leetcode_583_middle {

    public static void main(String[] args) {
        Leetcode_583_middle top583 = new Leetcode_583_middle();
        String word1 = "sea";
        String word2 = "eat";
        int res = top583.minDistance(word1, word2);
        System.out.println(res);
    }


    /**
     * 这里是求删除的次数。注意
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        // 这里是求删除的次数，按照第1143，dp[m][n]是返回他们最长公共子串
        // 那么删除的次数就是 m + n - 2 * dp[m][n]
        return m + n - 2 * dp[m][n];
    }
}
