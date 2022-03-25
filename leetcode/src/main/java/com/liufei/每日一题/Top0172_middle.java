package com.liufei.每日一题;

/**
 * 给定一个整数 n ，返回 n! 结果中尾随零的数量。
 *
 * 提示 n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：0
 * 解释：3! = 6 ，不含尾随 0
 * 示例 2：
 *
 * 输入：n = 5
 * 输出：1
 * 解释：5! = 120 ，有一个尾随 0
 * 示例 3：
 *
 * 输入：n = 0
 * 输出：0
 *
 * 提示：
 *
 * 0 <= n <= 104
 *
 */
public class Top0172_middle {

    public static void main(String[] args) {
        int n = 50;
        // int res = trailingZeroes(n);
        int res = trailingZeroes2(n);
        System.out.println(res);
        System.out.println(18 % 10);
    }


    public static int trailingZeroes(int n) {
        int count = 0;
        for (int i = 5; i <= n; i *= 5) {
            count += n / i;
        }
        return count;
    }

    /**
     *
     * 对除数  a / b
     *    1. 如果 a < b 结果等于0
     *
     * 对于  a % b
     *    1. 如果 a < b  结果等于a
     *    2. 如果 a > b  结果等于  a - b / a * b
     *
     * @param n
     * @return
     */
    public static int trailingZeroes2(int n) {
        int count = 0;
        long sum = 1;
        for (int i = 1; i <= n; i++) {
            sum *= i;
            // 因为会超出范围。所以这里采用/ 10 来计数。不要使用 sum / 100
            while (sum % 10 == 0) {
                count++;
                // 记完数之后，去掉 0 ，避免超过长度
                sum /= 10;
            }
            sum %= 100000;
        }
        return count;
    }
}
