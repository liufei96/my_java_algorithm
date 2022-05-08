package top100;

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
        String[] words = { "bar","foo","the", "bar" };
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
        // 只讨论从0，1，...， oneLen-1 开始的子串情况，
        // 每次进行匹配的窗口大小为 wordsLen，每次后移一个单词长度，由左右窗口维持当前窗口位置
        for (int i = 0; i < firstWordLen; i++) {
            // 左右窗口
                int left = i, right = i, count = 0;
            // 统计每个符合要求的word
            Map<String, Integer> tmp_map = new HashMap<>();
            // 右窗口不能超出主串长度
            while (right + firstWordLen <= s.length()) {
                // 得到一个单词
                String w = s.substring(right, right + firstWordLen);
                // 右窗口右移
                right += firstWordLen;
                // words[]中没有这个单词，那么当前窗口肯定匹配失败，直接右移到这个单词后面
                if (!map.containsKey(w)) {
                    left = right;
                    // 符合要求的单词数清0
                    count = 0;
                    // 窗口内单词统计map清空，重新统计
                    tmp_map.clear();
                } else {
                    // 统计当前子串中这个单词出现的次数
                    tmp_map.put(w, tmp_map.getOrDefault(w, 0) + 1);
                    count++;
                    // 如果这个单词出现的次数大于words[]中它对应的次数，又由于每次匹配和words长度相等的子串
                    // 如 ["foo","bar","foo","the"]  "| foobarfoobar| foothe"
                    // 第二个bar虽然是words[]中的单词，但是次数超了，那么右移一个单词长度后 "|barfoobarfoo|the"
                    // bar还是不符合，所以直接从这个不符合的bar之后开始匹配，也就是将这个不符合的bar和它之前的单词(串)全移出去
                    while (tmp_map.getOrDefault(w,0) > map.getOrDefault(w, 0)) {
                        String t_w = s.substring(left, left + firstWordLen);
                        // 符合的单词数减一
                        count--;
                        tmp_map.put(t_w, tmp_map.getOrDefault(t_w, 0) - 1);
                        // 左窗口位置右移
                        left += firstWordLen;
                    }
                    // 当前窗口字符串满足要求
                    if (count == words.length) res.add(left);
                }
            }
        }
        return res;
    }
}
