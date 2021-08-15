package com.liufei.top100;

/**
 * 44. 通配符匹配  [ https://leetcode-cn.com/problems/wildcard-matching/ ]
 * <p>
 * 给定一个字符串(s) 和一个字符模式(p) ，实现一个支持'?'和'*'的通配符匹配。
 * <p>
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 * <p>
 * 说明:
 * <p>
 * s可能为空，且只包含从a-z的小写字母。
 * p可能为空，且只包含从a-z的小写字母，以及字符?和*。
 * <p>
 * 示例1:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * <p>
 * 示例2:
 * <p>
 * 输入:
 * s = "aa"
 * p = "*"
 * 输出: true
 * 解释:'*' 可以匹配任意字符串。
 * <p>
 * 示例3:
 * <p>
 * 输入:
 * s = "cb"
 * p = "?a"
 * 输出: false
 * 解释:'?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 * <p>
 * 示例4:
 * <p>
 * 输入:
 * s = "adceb"
 * p = "*a*b"
 * 输出: true
 * 解释:第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
 * <p>
 * 示例5:
 * <p>
 * 输入:
 * s = "acdcb"
 * p = "a*c?b"
 * 输出: false
 */
public class Top044_hard {

    public static void main(String[] args) {
        Top044_hard top044Hard = new Top044_hard();
        String s = "acdcb";
        String p = "a*c?b";
        boolean match = top044Hard.isMatch(s, p);
        System.out.println(match);
        System.out.println(top044Hard.isMatch2(s, p));
    }

    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        if (m == n && m == 0) return true;

        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        for (int i = 1; i <= n; ++i) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = true;
            } else {
                break;
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                } else if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }


    /**
     * 回溯
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch2(String s, String p) {
        int i = 0;
        int j = 0;
        int star = -1;
        int k = 0; // 记录最后一次匹配的位置  + 1

        while (i < s.length()) {
            if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
                i++;
                j++;
                continue;
            }

            if (p.charAt(j) == '*') {
                star = j++;
                k = i;
            }
            // 如果失配了，回溯到 k + 1，即让 * 再多吞噬一个字符。
            if (star != -1) {
                i = ++k;
                j = star + 1;
                continue;
            }
            return false;
        }
        while (j < p.length() && p.charAt(j) == '*') ++j;
        return j == p.length();
    }
}
