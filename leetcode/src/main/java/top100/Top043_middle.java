package top100;

import java.util.Arrays;

/**
 * 43. 字符串相乘  [ https://leetcode-cn.com/problems/multiply-strings/ ]
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 示例 1:
 *
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 *
 * 示例 2:
 *
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 *
 *
 * 说明：
 *
 * num1和num2的长度小于110。
 * num1 和 num2 只包含数字0-9。
 * num1 和 num2均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 *
 */
public class Top043_middle {

    public static void main(String[] args) {
        Top043_middle top043Middle = new Top043_middle();
        String num1 = "999";
        String num2 = "999";
        String ans = top043Middle.multiply(num1, num2);
        System.out.println(ans);
        System.out.println(top043Middle.multiply2(num1, num2));

    }

    /**
     *               9   9   9
     *         ×     6   7   8
     *  ----------------------
     *              72  72  72
     *          63  63  63
     *      54  54  54
     *  ----------------------
     *      54 117 189 135  72
     *  ----------------------
     *      54 117 189 142   2
     *  -----------------------
     *      54 117 203   2   2
     *  -----------------------
     *      54 137   3   2   2
     *  -----------------------
     *      67   7   3   2   2
     *  -----------------------
     *   6   7   7   3   2   2
     */
    public String multiply(String num1, String num2) {
        int n1 = num1.length();
        int n2 = num2.length();
        // 这个要加上，不然像这种 9133 * 0 结果就是0000，是不正确的
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        int[] sums = new int[n1 + n2];
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                sums[i + j] += (num1.charAt(n1 - i - 1) - '0') * (num2.charAt(n2 - j - 1) - '0');
            }
        }
        System.out.println(Arrays.toString(sums));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sums.length; i++) {
            if (sums[i] >= 10) {
              sums[i + 1] += sums[i] / 10;
              sums[i] = sums[i] % 10;
            }
            if (sums.length - 1 == i && sums[i] == 0) {
                continue;
            }
            sb.append(sums[i]);
        }
        return sb.reverse().toString();
    }


    /**
     * 做加法
     * @param num1
     * @param num2
     * @return
     */
    public String multiply2(String num1, String num2) {
        /**
         *         1  2  3
         *      x  4  5  6
         *  ----------------------
         *         6  3  8
         *      6  1  5
         *   4  9  2
         *  ----------------------
         *   5  6  0   8  8
         */
        int n1 = num1.length();
        int n2 = num2.length();
        // 这个要加上，不然像这种 9133 * 0 结果就是0000，是不正确的
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        int[] sums = new int[n1 + n2 + 1];
        for (int i = n2 - 1; i >= 0; i--) {
            for (int j = n1 - 1; j >= 0; j--) {
                int sum = (num2.charAt(i) - '0') * (num1.charAt(j) - '0');
                sums[i + j + 1] += sum % 10;
                sums[i + j] += sum / 10;
            }
        }
        System.out.println(Arrays.toString(sums));
        StringBuilder sb = new StringBuilder();
        for (int i = sums.length - 1; i >= 0 ; i--) {
            if (sums[i] >= 10) {
                sums[i - 1] += sums[i] / 10;
                sums[i] = sums[i] % 10;
            }
            if (sums.length - 1 == i || i == 0 && sums[i] == 0) {
                continue;
            }
            sb.append(sums[i]);
        }
        return sb.reverse().toString();
    }
}
