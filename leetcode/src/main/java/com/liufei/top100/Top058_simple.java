package com.liufei.top100;

/**
 * 58. 最后一个单词的长度 [ https://leetcode-cn.com/problems/length-of-last-word/ ]
 * <p>
 * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中最后一个单词的长度。
 * <p>
 * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "Hello World"
 * 输出：5
 * <p>
 * 示例 2：
 * <p>
 * 输入：s = "   fly me   to   the moon  "
 * 输出：4
 * <p>
 * 示例 3：
 * <p>
 * 输入：s = "luffy is still joyboy"
 * 输出：6
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 104
 * s 仅有英文字母和空格 ' ' 组成
 * s 中至少存在一个单词
 */
public class Top058_simple {

    public static void main(String[] args) {
        Top058_simple top058_simple = new Top058_simple();
        String s = "Hello World  ";
        int lastLength = top058_simple.lengthOfLastWord(s);
        System.out.println(lastLength);
    }

    public int lengthOfLastWord(String s) {
        int lastWordLength = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != ' ') {
                lastWordLength++;
            } else if (lastWordLength != 0) {
                // 这里巧妙利用lastwordLength != 0 来判断自动一个单词是不是结束
                return lastWordLength;
            }
        }
        return lastWordLength;
    }
}
