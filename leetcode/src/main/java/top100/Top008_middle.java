package top100;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串转换整数 (atoi) [ https://leetcode-cn.com/problems/string-to-integer-atoi/ ]
 *
 * 请你来实现一个myAtoi(string s)函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
 *
 * 函数myAtoi(string s) 的算法如下：
 *
 * 读入字符串并丢弃无用的前导空格
 * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
 * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
 * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
 * 如果整数数超过 32 位有符号整数范围 [−2^31, 2^31− 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −2^31 的整数应该被固定为 −2^31 ，大于 2^31− 1 的整数应该被固定为 2^31− 1 。
 * 返回整数作为最终结果。
 *
 * 注意：
 *
 * 本题中的空白字符只包括空格字符 ' ' 。
 * 除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。
 *
 * 示例 1：
 * 输入：s = "42"
 * 输出：42
 *
 * 示例 2：
 * 输入：s = "   -42"
 * 输出：-42
 *
 * 示例 3:
 * 输入：s = "4193 with words"
 * 输出：4193
 *
 * 示例 4：
 * 输入：s = "words and 987"
 * 输出：0
 *
 * 示例 5：
 * 输入：s = "-91283472332"
 * 输出：-2147483648
 *
 * 提示：
 *
 * 0 <= s.length <= 200
 * s 由英文字母（大写和小写）、数字（0-9）、' '、'+'、'-' 和 '.' 组成
 */
public class Top008_middle {

    /**
     * 注意几点：
     * 1. 连续两个符号在一起的，直接返回停止遍历。如：-+12，结果是：0
     * 2. 需要考虑到小数点的情况
     * 3. 前导是空格的可以忽略，中间是空格的直接停止遍历。  如：1 23，结果是：1
     * 4. 注意越界情况
     * 5. 只输入 - 或者 + 的情况
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int res = myAtoi2(s);
        System.out.println(res);
    }

    public static int myAtoi(String s) {
        int len = s.length();
        Deque<Character> deque = new ArrayDeque();
        // 是否停止遍历
        boolean stop = false;
        // 判断是不是有除了0之前的其他数值
        boolean notZero = false;
        for (int i = 0; i < len; i++) {
            char symbol = s.charAt(i);
            if (symbol >= '0' && symbol <= '9') {
                // 判断前一个字符是不是0
                if (symbol == '0') {
                  if (deque.size() > 0 && !notZero) {
                      // 对于前一个是0，现在还是0的，不加入到队列中，同时把之前的移除掉
                      if (deque.peekLast() == '0') {
                          deque.pop();
                      }
                  } else {
                      deque.add(symbol);
                  }
                } else {
                    notZero = true;
                    deque.add(symbol);
                }
                continue;
            }
            switch (symbol) {
                case '.':
                    if (deque.size() == 0) {
                        return 0;
                    } else {
                        deque.add(symbol);break;
                    }
                case '-':
                case '+':
                    if (deque.size() > 0) {
                        if (deque.peekLast() == '-' || deque.peekLast() == '+') {
                            // 前一个是 - 或 + 号
                            return 0;
                        } else if (deque.peekLast() >= '0' && deque.peekLast() <= '9') {
                            stop = true;
                        }
                    } else {
                        deque.add(symbol);
                    }
                    break;
                case ' ':
                    // 读入字符串并丢弃无用的前导空格。注意是前导空格，中间有空格直接返回
                    if (deque.size() > 0) {
                        stop = true;
                    }
                    break;
                default:
                    // 其他符号
                    stop = true;
            }
            if (stop) {
                break;
            }
        }
        if (deque.size() == 0) {
            return 0;
        } else {
            // 只有一个字符，但不是数字
            if (deque.size() == 1 && (deque.peek() < '0' || deque.peek() > '9')) {
                return 0;
            }
            StringBuilder sb = new StringBuilder();
            while (deque.size() > 0) {
                sb.append(deque.pop());
            }
            double num = Double.parseDouble(sb.toString());
            return (int) (num > 0 ? Math.min(num, Integer.MAX_VALUE) : Math.max(num, Integer.MIN_VALUE));
        }
    }


    public static int myAtoi2(String s) {
        String pattern = "^[\\+\\-]?\\d+";
        Pattern r = Pattern.compile(pattern);
        Matcher matcher = r.matcher(s);
        if (matcher.find()) {
            System.out.println(matcher.group());
        }
        return 0;
    }
}
