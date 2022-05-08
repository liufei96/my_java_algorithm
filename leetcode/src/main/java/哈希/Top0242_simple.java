package 哈希;

import java.util.Arrays;

/**
 * 242. 有效的字母异位词  [ https://leetcode-cn.com/problems/valid-anagram/s ]
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: s = "rat", t = "car"
 * 输出: false
 * <p>
 * 提示:
 * <p>
 * 1 <= s.length, t.length <= 5 * 104
 * s 和 t 仅包含小写字母
 */
public class Top0242_simple {

    public static void main(String[] args) {
        Top0242_simple top0242_simple = new Top0242_simple();
        String s = "anagram";
        String t = "nagaram";
        boolean anagram = top0242_simple.isAnagram(s, t);
        System.out.println(anagram);
    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        char[] chars = s.toCharArray();
        char[] chars1 = t.toCharArray();
        Arrays.sort(chars);
        Arrays.sort(chars1);
        return Arrays.equals(chars, chars1);
    }

    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] record = new int[26];
        int len = s.length();
        for (int i = 0; i < len; i++) {
            record[s.charAt(i) - 'a']++;
            record[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (record[i] != 0) {
                // record数组如果有的元素不为零0，说明字符串s和t 一定是谁多了字符或者谁少了字符。
                return false;
            }
        }
        return true;
    }
}
