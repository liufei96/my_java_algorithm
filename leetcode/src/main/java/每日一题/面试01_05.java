package 每日一题;
/**
 * [面试题 01.05. 一次编辑] https://leetcode.cn/problems/one-away-lcci
 *
 * 字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
 *
 * 示例 1:
 *
 * 输入:
 * first = "pale"
 * second = "ple"
 * 输出: True
 *
 * 示例 2:
 *
 * 输入:
 * first = "pales"
 * second = "pal"
 * 输出: False
 */
public class 面试01_05 {

    public static void main(String[] args) {
        String first = "teacher";
        String second = "treacher";
        boolean b = oneEditAway(first, second);
        System.out.println(b);
    }

    public static boolean oneEditAway(String first, String second) {
        if (first.equals(second)) {
            return true;
        }
        int len1 = first.length(), len2 = second.length();
        if (Math.abs(len1 - len2) >= 2) {
            return false;
        }
        int i = 0, j = 0, count = 0;
        while (i < len1 || j < len2) {
            char ch1 = 0, ch2 = 0;
            if (i < len1) {
                ch1 = first.charAt(i);
            }
            if (j < len2) {
                ch2 = second.charAt(j);
            }
            if (ch1 == ch2) {
                i++;
                j++;
                continue;
            }
            // 不等于，有三种方式
            if (len1 > len2) {
                i++;
            } else if (len2 > len1) {
                j++;
            } else {
                // 替换
                i++;
                j++;
            }
            count++;
            if (count >= 2) {
                return false;
            }
        }
        return true;
    }
}
