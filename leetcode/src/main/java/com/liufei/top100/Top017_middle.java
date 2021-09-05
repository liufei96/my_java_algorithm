package com.liufei.top100;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.*;

/**
 * 17. 电话号码的字母组合 [ https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/ ]
 * <p>
 * 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * <p>
 * 2 -> abc
 * 3 -> def
 * 4 -> ghi
 * 5 -> jkl
 * 6 -> mno
 * 7 -> pqrs
 * 8 -> tuv
 * 9 -> wxyz
 * <p>
 * 示例 1：
 * <p>
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * <p>
 * 示例 2：
 * <p>
 * 输入：digits = ""
 * 输出：[]
 * <p>
 * 示例 3：
 * <p>
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 * <p>
 * 提示：
 * <p>
 * 0 <= digits.length <= 4
 * digits[i] 是范围 ['2', '9'] 的一个数字。
 */
public class Top017_middle {

    public static void main(String[] args) {
        Top017_middle top17 = new Top017_middle();
        Scanner scanner = new Scanner(System.in);
        String digits = scanner.nextLine();
        List<String> res = top17.letterCombinations3(digits);
        System.out.println(res);
        System.out.println(res.size());
    }

    /**
     * 两两组合
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        String[] reps = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        int len = digits.trim().length();
        if (len >= 1) {
            String[] a = reps[digits.charAt(0) - '0'].split("");
            int count = 1;
            while (count < digits.length()) {
                String[] b = reps[digits.charAt(count) - '0'].split("");
                a = twoNumberCombinations(a, b);
                count++;
            }
            return Arrays.asList(a);
        } else {
            return new ArrayList<>();
        }
    }

    public String[] twoNumberCombinations(String[] a, String[] b) {
        int m = a.length;
        int n = b.length;
        String[] res = new String[m * n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[i * n + j] = a[i] + b[j];
            }
        }
        return res;
    }

    /**
     * 官方答案。回溯算法（深度优先算法）
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations2(String digits) {
        List<String> combinations = new ArrayList<>();
        if (digits.length() == 0) {
            return combinations;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }

    /**
     * 举例子： 23
     * <p>
     * 2
     * a           b           c
     * 3           3           3
     * d    e    f   d  e  f    d  e   f
     * <p>
     * ad  ae   af  ad  ae af   ad ae  af
     *
     * @param combinations
     * @param phoneMap
     * @param digits
     * @param index
     * @param combination
     */
    public void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {
        if (digits.length() == index) {
            combinations.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            int letterLen = letters.length();
            for (int i = 0; i < letterLen; i++) {
                combination.append(letters.charAt(i));
                backtrack(combinations, phoneMap, digits, index + 1, combination);
                System.out.println(combination);
                combination.deleteCharAt(index);
            }
        }
    }

    /**
     * 广度优先算法。广度优先算法，需要使用队列
     * <p>
     * 先讲abc添加到队列中，然后依次取出，在添加
     */
    public List<String> letterCombinations3(String digits) {
        List<String> combinations = new ArrayList<>();
        if (digits.length() == 0) {
            return combinations;
        }
        Map<Character, String[]> phoneMap = new HashMap<Character, String[]>() {{
            put('2', new String[]{"a", "b", "c"});
            put('3', new String[]{"d", "e", "f"});
            put('4', new String[]{"g", "h", "i"});
            put('5', new String[]{"j", "k", "l"});
            put('6', new String[]{"m", "n", "o"});
            put('7', new String[]{"p", "q", "r", "s"});
            put('8', new String[]{"t", "u", "v"});
            put('9', new String[]{"w", "x", "y", "z"});
        }};
        int len = digits.length();
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            if (i == 0) {
                String[] strings = phoneMap.get(digits.charAt(i));
                for (String str : strings) {
                    queue.offer(str);
                }
            } else {
                int size = queue.size();
                while (size > 0) {
                    String poll = queue.poll();
                    String[] strings = phoneMap.get(digits.charAt(i));
                    for (String str : strings) {
                        queue.offer(poll + str);
                    }
                    size--;
                }
            }
        }
        combinations.addAll(queue);
        return combinations;
    }


}
