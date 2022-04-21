package com.liufei.每日一题;

import java.util.HashSet;
import java.util.Set;

/**
 * 824. 山羊拉丁文  [ https://leetcode-cn.com/problems/goat-latin/ ]
 * <p>
 * 给你一个由若干单词组成的句子 sentence ，单词间由空格分隔。每个单词仅由大写和小写英文字母组成。
 * <p>
 * 请你将句子转换为 “山羊拉丁文（Goat Latin）”（一种类似于 猪拉丁文 - Pig Latin 的虚构语言）。山羊拉丁文的规则如下：
 * <p>
 * 如果单词以元音开头（'a', 'e', 'i', 'o', 'u'），在单词后添加"ma"。
 * 例如，单词 "apple" 变为 "applema" 。
 * 如果单词以辅音字母开头（即，非元音字母），移除第一个字符并将它放到末尾，之后再添加"ma"。
 * 例如，单词 "goat" 变为 "oatgma" 。
 * 根据单词在句子中的索引，在单词最后添加与索引相同数量的字母'a'，索引从 1 开始。
 * 例如，在第一个单词后添加 "a" ，在第二个单词后添加 "aa" ，以此类推。
 * 返回将 sentence 转换为山羊拉丁文后的句子。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：sentence = "I speak Goat Latin"
 * 输出："Imaa peaksmaaa oatGmaaaa atinLmaaaaa"
 * 示例 2：
 * <p>
 * 输入：sentence = "The quick brown fox jumped over the lazy dog"
 * 输出："heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"
 * <p>
 * 提示：
 * <p>
 * 1 <= sentence.length <= 150
 * sentence 由英文字母和空格组成
 * sentence 不含前导或尾随空格
 * sentence 中的所有单词由单个空格分隔
 */
public class Top0824_simple {

    public static void main(String[] args) {
        String sentence = "The quick brown fox jumped over the lazy dog";
        String s = toGoatLatin(sentence);
        System.out.println(s);
    }

    public static String toGoatLatin(String sentence) {
        Set<Character> vowelSet = new HashSet<Character>() {
            {
               add('a');
               add('e');
               add('i');
               add('o');
               add('u');
               add('A');
               add('E');
               add('I');
               add('O');
               add('U');
            }
        };
        StringBuilder ans = new StringBuilder();
        StringBuilder wordStr = new StringBuilder();
        String[] strs = sentence.split(" ");
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() == 0) {
                continue;
            }
            if (vowelSet.contains(strs[i].charAt(0))) {
                wordStr.append(strs[i]);
            } else {
                // 移除第一个元素的字母，放到最后一个
                char[] chars = strs[i].toCharArray();
                for (int j = 1; j < chars.length; j++) {
                    wordStr.append(chars[j]);
                }
                wordStr.append(chars[0]);
            }
            wordStr.append("ma");
            // 根据索引位置+ a
            for (int j = 0; j < i + 1; j++) {
                wordStr.append("a");
            }
            // 加上空格
            wordStr.append(" ");
            ans.append(wordStr);
            wordStr.setLength(0);
        }
        // 去掉最后一个空格
        return ans.substring(0, ans.length() - 1);
    }
}
