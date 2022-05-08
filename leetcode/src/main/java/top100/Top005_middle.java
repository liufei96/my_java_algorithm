package top100;

import java.util.Scanner;

/**
 * 最长回文数。 [ https://leetcode-cn.com/problems/longest-palindromic-substring/ ]
 *
 * 给你一个字符串 s，找到 s 中最长的回文子串。【回文的意思是正着念和倒着念一样】
 * <p>
 * 示例 1：
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * <p>
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出："bb"
 * <p>
 * 示例 3：
 * 输入：s = "a"
 * 输出："a"
 * <p>
 * 示例 4：
 * 输入：s = "ac"
 * 输出："a"
 * <p>
 * 提示：
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母（大写和/或小写）组成
 */
public class Top005_middle {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Top005_middle top005_middle = new Top005_middle();
        String res = top005_middle.longestPalindrome3(input);
        System.out.println(res);
    }


    /**
     * 暴力法。复杂度是o(n^3)
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        char[] chars = s.toCharArray();
        // 记录最大的长度
        int maxLen = 1;
        // 记录起始位置
        int begin = 0;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (j - i + 1 > maxLen && validPalindrome(chars, i, j)) {
                    begin = i;
                    maxLen = j - i + 1;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    private boolean validPalindrome(char[] chars, int left, int right) {
        // 判断是不是回文数
        while (left < right) {
            if (chars[left] != chars[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 时间复杂度：O(n^2) 其中 nn 是字符串的长度。动态规划的状态总数为 O(n^2)对于每个状态，我们需要转移的时间为 O(1)。
     *
     * 优化：使用动态规划
     * 对于一个子串，如果其是回文数，并且长度大于2，那么将它首尾两个字母去掉之后，仍然是回文数，
     */
    public String longestPalindrome2(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        boolean[][] dp = new boolean[len][len];
        // 初始化：所有长度为1的子串都是回文数
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        char[] chars = s.toCharArray();
        // 记录最大的长度
        int maxLen = 1;
        // 记录起始位置
        int begin = 0;
        // 枚举回文子串的长度，从2开始
        for (int l = 2; l < len; l++) {
            for (int i = 0; i < len; i++) {
                int r = i + l - 1;
                // 判断右边界是否超出了长度
                if (r >= len) {
                    break;
                }
                if (chars[i] != chars[r]) {
                    dp[i][r] = false;
                } else {
                    if (r - i < 3) {
                        dp[i][r] = true;
                    } else {
                        dp[i][r] = dp[i + 1][r - 1];
                    }
                }
                //  只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                if (dp[i][r] && r - i + 1 > maxLen) {
                    maxLen = r - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }


    /**
     * 中心扩展法
     */
    public String longestPalindrome3(String s) {
        int begin = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            // 当回文串是奇数时，由一个中心点向两边扩散
            int len1 = expandAroundCenter(s, i, i);
            // 当回文串是偶数时，由中间的两个中心点向两边扩散
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            // 计算对应最大回文子串的起点和终点
            if (len > end - begin) {
                begin = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(begin, end  + 1);
    }

    /**
     * 辅助函数：寻找回文数
     * @param s
     * @param left
     * @param right
     * @return
     */
    public int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return right - left - 1;
    }
}
