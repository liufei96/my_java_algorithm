package top100;

import java.util.Scanner;

/**
 * 12. 整数转罗马数字 [ https://leetcode-cn.com/problems/integer-to-roman/ ]
 * 罗马数字包含以下七种字符：I，V，X，L，C，D和M。
 * <p>
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做II，即为两个并列的 1。12 写做XII，即为X+II。 27 写做XXVII, 即为XX+V+II。
 * <p>
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做IIII，而是IV。
 * 数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为IX。这个特殊的规则只适用于以下六种情况：
 * <p>
 * I可以放在V(5) 和X(10) 的左边，来表示 4 和 9。
 * X可以放在L(50) 和C(100) 的左边，来表示 40 和90。
 * C可以放在D(500) 和M(1000) 的左边，来表示400 和900。
 * 给你一个整数，将其转为罗马数字。
 * <p>
 * 示例 1:
 * <p>
 * 输入: num = 3
 * 输出: "III"
 * <p>
 * 示例 2:
 * <p>
 * 输入: num = 4
 * 输出: "IV"
 * <p>
 * 示例 3:
 * <p>
 * 输入: num = 9
 * 输出: "IX"
 * <p>
 * 示例 4:
 * <p>
 * 输入: num = 58
 * 输出: "LVIII"
 * 解释: L = 50, V = 5, III = 3.
 * <p>
 * 示例 5:
 * <p>
 * 输入: num = 1994
 * 输出: "MCMXCIV"
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 */
public class Top012_middle {

    public static void main(String[] args) {
        Top012_middle top12 = new Top012_middle();
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        String res = top12.intToRoman2(num);
        System.out.println(res);
    }


    public String intToRoman(int num) {
        if (num <= 3) {
            return getRoman("I", num);
        } else if (num <= 4) {
            return "IV";
        } else if (num < 9) {
            return "V" + getRoman("I", (num - 5));
        } else if (num == 9) {
            return "IX";
        } else if (num == 10) {
            return "X";
        } else if (num < 40) {
            return getRoman("X", num / 10) + intToRoman(num % 10);
        } else if (num < 50) {
            return "XL" + intToRoman(num - 40);
        } else if (num == 50) {
            return "L";
        } else if (num < 90) {
            return "L" + intToRoman(num - 50);
        } else if (num < 100) {
            return "XC" + intToRoman(num - 90);
        } else if (num == 100) {
            return "C";
        } else if (num < 400) {
            return getRoman("C", num / 100) + intToRoman(num % 100);
        } else if (num < 500) {
            return "CD" + intToRoman(num - 400);
        } else if (num == 500) {
            return "D";
        } else if (num < 900) {
            return "D" + intToRoman(num - 500);
        } else if (num < 1000) {
            return "CM" + intToRoman(num - 900);
        } else if (num == 1000) {
            return "M";
        } else if (num < 2000) {
            return "M" + intToRoman(num - 1000);
        } else if (num >= 2000) {
            return getRoman("M", num / 1000) + intToRoman(num % 1000);
        }
        return "";
    }

    private String getRoman(String symbol, int count) {
        String newSymbol = "";
        for (int i = 0; i < count; i++) {
            newSymbol += symbol;
        }
        return newSymbol;
    }

    /**
     * 推荐使用
     * @param num
     * @return
     */
    public String intToRoman2(int num) {
        int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        String[] reps = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

        int len = values.length;
        String res = "";
        for (int i = 0; i < len; i++) {
            while (num >= values[i]) {
                num -= values[i];
                res += reps[i];
            }
        }
        return res;
    }
}
