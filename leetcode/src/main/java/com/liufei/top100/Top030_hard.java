package com.liufei.top100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 30. 串联所有单词的子串 [ https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/ ]
 *
 * 给定一个字符串s和一些 长度相同 的单词words 。找出 s 中恰好可以由words 中所有单词串联形成的子串的起始位置。
 *
 * 注意子串要与words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑words中单词串联的顺序。
 *
 * 示例 1：
 *
 * 输入：s = "barfoothefoobarman", words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 *
 * 示例 2：
 *
 * 输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * 输出：[]
 *
 * 示例 3：
 *
 * 输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * 输出：[6,9,12]
 *
 */
public class Top030_hard {

    public static void main(String[] args) {
        Top030_hard top030 = new Top030_hard();
        String s = "barfoofoobarthefoobarman";
        String[] words = { "bar","foo","the", " bar" };
        List<Integer> substring = top030.findSubstring(s, words);
        System.out.println(substring);
    }

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        int firstWordLen = words[0].length();
        int allWordLen = firstWordLen * words.length;
        if (s.length() < allWordLen) {
            return res;
        }
        Map<String,Integer> map = new HashMap<>();
        for(String word: words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        for (int i = 0; i < firstWordLen; i++) {
            int left = i, right = i, count = 0;
            HashMap<String, Integer> tmp_map = new HashMap<>();
            while (right + firstWordLen <= s.length()) {
                String w = s.substring(right, right + firstWordLen);
                right += firstWordLen;
                if (!map.containsKey(w)) {
                    count = 0;
                    left = right;
                    tmp_map.clear();
                } else {
                    tmp_map.put(w, tmp_map.getOrDefault(w, 0) + 1);
                    count++;
                    while (tmp_map.get(w) > map.get(w)) {
                        String t_w = s.substring(left, left + firstWordLen);
                        count--;
                        tmp_map.put(t_w, tmp_map.getOrDefault(t_w, 0) - 1);
                        left += firstWordLen;
                    }
                    if (count == words.length) res.add(left);
                }
            }
        }
        return res;
    }
}
