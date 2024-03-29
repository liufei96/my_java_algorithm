package top100;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 13.罗马数字转整数 [ https://leetcode-cn.com/problems/roman-to-integer/ ]
 * <p>
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * <p>
 * 例如， 罗马数字 2 写做II，即为两个并列的 1。12 写做XII，即为X+II。 27 写做XXVII, 即为XX+V+II。
 * <p>
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做IIII，而是IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为IX。这个特殊的规则只适用于以下六种情况：
 * <p>
 * I可以放在V(5) 和X(10) 的左边，来表示 4 和 9。
 * X可以放在L(50) 和C(100) 的左边，来表示 40 和90。
 * C可以放在D(500) 和M(1000) 的左边，来表示400 和900。
 * 给定一个罗马数字，将其转换成整数。输入确保在 1到 3999 的范围内。
 * <p>
 * 示例 1:
 * 输入: "III"
 * 输出: 3
 * <p>
 * 示例 2:
 * 输入: "IV"
 * 输出: 4
 * <p>
 * 示例 3:
 * 输入: "IX"
 * 输出: 9
 * <p>
 * 示例 4:
 * 输入: "LVIII"
 * 输出: 58
 * 解释: L = 50, V= 5, III = 3.
 * <p>
 * 示例 5:
 * 输入: "MCMXCIV"
 * 输出: 1994
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 15
 * s 仅含字符 ('I', 'V', 'X', 'L', 'C', 'D', 'M')
 * 题目数据保证 s 是一个有效的罗马数字，且表示整数在范围 [1, 3999] 内
 * 题目所给测试用例皆符合罗马数字书写规则，不会出现跨位等情况。
 * IL 和 IM 这样的例子并不符合题目要求，49 应该写作 XLIX，999 应该写作 CMXCIX 。
 * 关于罗马数字的详尽书写规则，可以参考 罗马数字 - Mathematics 。
 */
public class Top013_simple {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int res = romanToInt(str);
        System.out.println(res);
    }

    public static int romanToInt(String s) {
        int len = s.length();
        int res = 0;
        char pre = ' ';
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            switch (c) {
                case 'I':
                    res += 1;
                    break;
                case 'V':
                    if (pre == 'I') {
                        res += 3;
                    } else {
                        res += 5;
                    }
                    break;
                case 'X':
                    if (pre == 'I') {
                        res += 8;
                    } else {
                        res += 10;
                    }
                    break;
                case 'L':
                    if (pre == 'X') {
                        res += 30;
                    } else {
                        res += 50;
                    }
                    break;
                case 'C':
                    if (pre == 'X') {
                        res += 80;
                    } else {
                        res += 100;
                    }
                    break;
                case 'D':
                    if (pre == 'C') {
                        res += 300;
                    } else {
                        res += 500;
                    }
                    break;
                case 'M':
                    if (pre == 'C') {
                        res += 800;
                    } else {
                        res += 1000;
                    }
                    break;
                default:
            }
            pre = c;
        }
        return res;
    }

    public int romanToInt2(String s) {
        int res = 0, n = s.length();
        Map<Character, Integer> map = new HashMap<Character, Integer>() {
            {
                put('I', 1);
                put('V', 5);
                put('X', 10);
                put('L', 50);
                put('C', 100);
                put('D', 500);
                put('M', 1000);
            }
        };
        for (int i = 0; i < n; i++) {
            Integer value = map.get(s.charAt(i));
            if (i < n - 1 && value < map.get(s.charAt(i + 1))) {
                res -= value;
            } else {
                res += value;
            }
        }
        return res;
    }
}
