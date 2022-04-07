package com.liufei.每日一题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 796. 旋转字符串 [https://leetcode-cn.com/problems/rotate-string/]
 *
 * 给定两个字符串, s 和 goal。如果在若干次旋转操作之后，s 能变成 goal ，那么返回 true 。
 *
 * s 的 旋转操作 就是将 s 最左边的字符移动到最右边。
 *
 * 例如, 若 s = 'abcde'，在旋转一次之后结果就是'bcdea' 。
 *
 *
 * 示例 1:
 *
 * 输入: s = "abcde", goal = "cdeab"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "abcde", goal = "abced"
 * 输出: false
 *
 *
 * 提示:
 *
 * 1 <= s.length, goal.length <= 100
 * s 和 goal 由小写英文字母组成
 *
 */
class Top0796_simple {

    public static void main(String[] args) {
        Top0796_simple top0796simple = new Top0796_simple();
        String s = "abcde";
        String goal = "cdeab";
        boolean b = top0796simple.rotateString4(s, goal);
        System.out.println(b);
    }

    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        int len = s.length();
        Map<Character, List<Character>> map = new HashMap<>();
        map.put(s.charAt(len - 1), new ArrayList<>());
        // 最后一个元素指向第一个元素，形成一个环
        map.get(s.charAt(len - 1)).add(s.charAt(0));
        for (int i = 0; i < len - 1; i++) {
            if (map.get(s.charAt(i)) == null) {
                map.put(s.charAt(i), new ArrayList<>());
            }
            map.get(s.charAt(i)).add(s.charAt(i + 1));
        }
        for (int i = 0; i < len; i++) {
            int next = i + 1 < len ? i + 1 : 0;
            if (map.get(goal.charAt(i)) == null || !map.get(goal.charAt(i)).contains(goal.charAt(next))) {
                return false;
            }
        }
        return true;
    }

    public boolean rotateString2(String s, String goal) {
        return s.length() == goal.length() && (s + s).contains(goal);
    }

    /**
     * 模拟。双重for循环
     *
     * @param s
     * @param goal
     * @return
     */
    public boolean rotateString3(String s, String goal) {
        int m = s.length(), n = goal.length();
        if (m != n) {
            return false;
        }
        for (int i = 0; i < m; i++) {
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if (s.charAt((i + j) % n) != goal.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return true;
            }
        }
        return false;
    }

    /**
     * KMP
     *
     * @param s
     * @param goal
     * @return
     */
    public boolean rotateString4(String s, String goal) {
        int m = s.length(), n = goal.length();
        if (m != n) {
            return false;
        }
        int[] next = getNext(goal);
        s = s + s;
        m = s.length();
        int i = 0, j = 0;
        while (i < m) {
            if (s.charAt(i) == goal.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
                if (j == -1) {
                    i++;
                    j++;
                }
            }
            if (j >= n) return true;
        }
        return false;
    }

    int[] getNext(String goal) {
        int n = goal.length();
        int[] next = new int[n];
        int index = 1;
        int len = 0;
        while (index < n) {
            if (goal.charAt(index) == goal.charAt(len)) {
                next[index++] = ++len;
            } else {
                if (len > 0) {
                    // 斜着移动
                    len = next[len - 1];
                } else {
                    next[index++] = 0;
                }
            }
        }
        System.out.println(Arrays.toString(next));
        // 整体向右移动一位
        for (int i = n - 1; i > 0; i--) {
            next[i] = next[i - 1];
        }
        next[0] = -1;
        return next;
    }
}