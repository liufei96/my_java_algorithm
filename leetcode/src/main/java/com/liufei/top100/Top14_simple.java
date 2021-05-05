package com.liufei.top100;

/**
 * 14. 最长公共前缀 [https://leetcode-cn.com/problems/longest-common-prefix/]
 *
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1：
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 *
 * 示例 2：
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 */
public class Top14_simple {

    public static void main(String[] args) {
        String[] str = { "flower","flow","flight" };
        String res = longestCommonPrefix2(str);
        System.out.println(res);
    }

    /**
     * 方法1：纵向扫描比较
     * 方法一是横向扫描，依次遍历每个字符串，更新最长公共前缀。另一种方法是纵向扫描。
     * 纵向扫描时，从前往后遍历所有字符串的每一列，比较相同列上的字符是否相同，如果相同则继续对下一列进行比较，
     * 如果不相同则当前列不再属于公共前缀，当前列之前的部分为最长公共前缀。
     *
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        int count = strs.length;
        int len = strs[0].length();
        for (int i = 0; i < len; i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < count; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    /**
     * 方法2：横向扫描比较，两个两个比较
     * 如：
     *   ["flower","flow","flight"]
     * 第一步：flower和flow比较 => flo
     * 第二步：flo 和 flight 比较 => fl
     * @param strs
     * @return
     */
    public static String longestCommonPrefix2(String[] strs) {
        int count = strs.length;
        String str1 = strs[0];
        for (int i = 1; i < count; i++) {
            str1 = findLongestCommonPrefix2(str1, strs[i]);
        }
        return str1;
    }

    public static String findLongestCommonPrefix2(String str1, String str2) {
        int len = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < len) {
            if (str1.charAt(index) != str2.charAt(index)) {
                return str1.substring(0, index);
            }
            index++;
        }
        return str1.substring(0, index);
    }
}
