package com.liufei.top100;

/**
 * 50. Pow(x, n) [ https://leetcode-cn.com/problems/powx-n/ ]
 * <p>
 * 实现pow(x, n)，即计算 x 的 n 次幂函数（即，xn）。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 * 示例 2：
 * <p>
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 * 示例 3：
 * <p>
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/22 = 1/4 = 0.25
 */
public class Top050_middle {

    public static void main(String[] args) {
        Top050_middle top050_middle = new Top050_middle();
        double x = 2.0000;
        int n = 3;
        double res = top050_middle.myPow(x, n);
        System.out.println(res);
        System.out.println(top050_middle.myPow2(x, n));
        System.out.println(top050_middle.myPow3(x, n));

    }

    public double myPow(double x, int n) {
        return Math.pow(x, n);
    }

    /**
     * 折半计算
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow2(double x, int n) {
        double res = 1.0;
        for (int i = n; i != 0; i /= 2) {
            if (i % 2 != 0) {
                // 奇数
                res *= x;
            }
            x *= x;
        }
        return n < 0 ? 1 / res : res;
    }

    /**
     * 递归
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow3(double x, int n) {
        return n >= 0 ? quickMul(x, n) : 1 / quickMul(x, n);
    }

    public double quickMul(double x, int n) {
        if (n == 0) {
            return 1.0;
        }
        double res = quickMul(x, n / 2);
        return n % 2 == 0 ? res * res : res * res * x;
    }
}
