package com.liufei.array;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 844. 比较含退格的字符串  [ https://leetcode-cn.com/problems/backspace-string-compare/ ]
 *
 * 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
 *
 * 注意：如果对空文本输入退格字符，文本继续为空。
 *
 * 示例 1：
 *
 * 输入：S = "ab#c", T = "ad#c"
 * 输出：true
 * 解释：S 和 T 都会变成 “ac”。
 * 示例 2：
 *
 * 输入：S = "ab##", T = "c#d#"
 * 输出：true
 * 解释：S 和 T 都会变成 “”。
 * 示例 3：
 *
 * 输入：S = "a##c", T = "#a#c"
 * 输出：true
 * 解释：S 和 T 都会变成 “c”。
 * 示例 4：
 *
 * 输入：S = "a#c", T = "b"
 * 输出：false
 * 解释：S 会变成 “c”，但 T 仍然是 “b”。
 *
 *
 * 提示：
 *
 * 1 <= S.length <= 200
 * 1 <= T.length <= 200
 * S 和 T 只含有小写字母以及字符 '#'。
 *
 * 进阶：
 *
 * 你可以用 O(N) 的时间复杂度和 O(1) 的空间复杂度解决该问题吗？
 *
 */
public class Top0844_simple {

    public static void main(String[] args) {
        Top0844_simple top0844Simple = new Top0844_simple();
        String s = "a#c";
        String t = "b";
        boolean flag = top0844Simple.backspaceCompare(s, t);
        System.out.println(flag);
        System.out.println(top0844Simple.backspaceCompare2(s, t));
    }

    public boolean backspaceCompare(String s, String t) {
        return build(s).equals(build(t));
    }

    private String build(String t) {
        Deque<Character> stackT = new LinkedList();
        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) == '#') {
                if (!stackT.isEmpty()) {
                    stackT.pop();
                }
            } else {
                stackT.offerFirst(t.charAt(i));
            }
        }
        return stackT.toString();
    }

    /**
     * 双指针
     *
     * 具体地，我们定义 skip 表示当前待删除的字符的数量。每次我们遍历到一个字符：
     *
     * 若该字符为退格符，则我们需要多删除一个普通字符，我们让 skip 加 1；
     *
     * 若该字符为普通字符：
     *
     * 若 skip 为 00，则说明当前字符不需要删去；
     *
     * 若 skip 不为 00，则说明当前字符需要删去，我们让 \textit{skip}skip 减 11。
     *
     *
     * 时间复杂度 o(n)
     * 空间复杂度 o(1)
     * @param s
     * @param t
     * @return
     */
    public boolean backspaceCompare2(String s, String t) {
        int i = s.length() - 1, j = t.length() - 1;
        int skipS = 0, skipT = 0;
        while (i >= 0 || j >= 0) {
            while (i >= 0) {
                if (s.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    skipS--;
                    i--;
                } else {
                    break;
                }
            }
            while (j >= 0) {
                if (t.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else {
                    break;
                }
            }
            if (i >= 0 && j >= 0) {
                if (s.charAt(i) != t.charAt(j)) {
                    return false;
                }
            } else {
                if (i >= 0 || j >= 0) {
                    return false;
                }
            }
            i--;
            j--;
        }
        return true;
    }
}
