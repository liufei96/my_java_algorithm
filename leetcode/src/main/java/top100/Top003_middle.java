package top100;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 无重复字符的最长子串 [https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/]
 *
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>
 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * <p>
 * 示例 3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * 示例 4:
 * 输入: s = ""
 * 输出: 0
 * <p>
 * <p>
 * 提示：
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 */
public class Top003_middle {


    public static void main(String[] args) {
        // 测试
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
//        int result = lengthOfLongestSubstring(input);
//        System.out.println(result);
        int res = lengthOfLongestSubstring3(input);
        System.out.println(res);
    }


    /**
     * 暴力法。滑动窗口
     * 极端情况下，复杂度：n(n-1)
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        int result = 0;
        Set<Character> set = new HashSet();
        for (int i = 0; i < s.length(); i++) {
            set.add(s.charAt(i));
            for (int j = i + 1; j < s.length(); j++) {
                int size = set.size();
                set.add(s.charAt(j));
                if (set.size() == size) {
                    break;
                }
            }
            result = Math.max(result, set.size());
            set.clear();
            if (result >= (s.length() - i)) {
                break;
            }
        }
        return result;
    }


    /**
     * 优化：
     * p w w k e w
     *
     */


    /**
     * 官方解答。通过移动左右指针
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring2(String s) {
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, res = 0;
        for (int i = 0; i < n; i++) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            // 右指针移动
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                rk++;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            res = Math.max(res, rk + 1 - i);
        }
        return res;
    }

    /**
     * 暂时没有看懂
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring3(String s) {
        int n = s.length(), res = 0;
        int[] index = new int[128];
        for (int i = 0, j = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            res = Math.max(res, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return res;
    }
}
