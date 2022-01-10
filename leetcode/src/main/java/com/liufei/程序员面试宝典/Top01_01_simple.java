package com.liufei.程序员面试宝典;

/**
 *
 * 面试题 01.01. 判定字符是否唯一  [https://leetcode-cn.com/problems/is-unique-lcci/]
 *
 * 实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
 * <p>
 * 示例 1：
 * <p>
 * 输入: s = "leetcode"
 * 输出: false
 * 示例 2：
 * <p>
 * 输入: s = "abc"
 * 输出: true
 * 限制：
 * <p>
 * 0 <= len(s) <= 100
 * 如果你不使用额外的数据结构，会很加分。
 */
public class Top01_01_simple {

    public static void main(String[] args) {
        Top01_01_simple top01_01_simple = new Top01_01_simple();
        String str = "'abc";
        boolean unique = top01_01_simple.isUnique(str);
        System.out.println(unique);
        boolean unique2 = top01_01_simple.isUnique2(str);
        System.out.println(unique2);
    }

    /**
     * 双层环迅。使用 lastIndexOf
     *
     * @param astr
     * @return
     */
    public boolean isUnique(String astr) {
        if (astr == null || astr.length() == 0) return true;
        for (int i = 0; i < astr.length(); i++) {
            if (astr.lastIndexOf(astr.charAt(i)) != i) {
                return false;
            }
        }
        return true;
    }

    public boolean isUnique2(String astr) {
        return astr.chars().distinct().count() == astr.length();
    }

}
