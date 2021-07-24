package com.liufei.swordFinger;

/**
 * 剑指 Offer 15. 二进制中1的个数 [ https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/ ]
 *
 * 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为 汉明重量).）。
 *
 *
 */
public class Algorithm_015 {

    public static void main(String[] args) {
        Algorithm_015 algorithm_015 = new Algorithm_015();
        int n = 128;
        int count = algorithm_015.hammingWeight(n);
        System.out.println(count);

        System.out.println(algorithm_015.hammingWeight2(n));
        System.out.println(algorithm_015.hammingWeight3(n));
        System.out.println(algorithm_015.hammingWeight4(n));
    }

    public int hammingWeight(int n) {
        String s = Integer.toBinaryString(n);
        return s.length() - s.replace("1", "").length();
    }

    public int hammingWeight2(int n) {
        return Integer.bitCount(n);
    }

    /**
     * 力扣官方答案。循环检查二进制位
     * 我们可以直接循环检查给定整数 nn 的二进制位的每一位是否为 11。
     *
     * 具体代码中，当检查第 ii 位时，我们可以让 nn 与 2^i2
     * i
     *   进行与运算，当且仅当 nn 的第 ii 位为 11 时，运算结果不为 00。
     *
     */
    public int hammingWeight3(int n) {
        int ret = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) {
                ret++;
            }
        }
        return ret;
    }

    /**
     * 力扣官方答案。位运算优化
     * 观察这个运算：n & (n−1)，其预算结果恰为把 nn 的二进制位中的最低位的 11 变为 00 之后的结果。
     *
     * 如：6 & (6-1) = 4, 6 = (110)_2, 4 = (100)_2
     *  ，运算结果 44 即为把 66 的二进制位中的最低位的 11 变为 00 之后的结果。
     *
     * 这样我们可以利用这个位运算的性质加速我们的检查过程，在实际代码中，我们不断让当前的 nn 与 n - 1n−1 做与运算，
     * 直到 nn 变为 00 即可。因为每次运算会使得 nn 的最低位的 11 被翻转，因此运算次数就等于 nn 的二进制位中 11 的个数。
     *
     *
     */
    public int hammingWeight4(int n) {
        int ret = 0;
       while (n != 0) {
           n &= (n - 1);
           ret++;
       }
        return ret;
    }
}
