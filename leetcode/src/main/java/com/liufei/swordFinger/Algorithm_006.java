package com.liufei.swordFinger;

/**
 * 剑指 Offer 16. 数值的整数次方 [ https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/ ]
 */
public class Algorithm_006 {

    public static void main(String[] args) {
        Algorithm_006 algorithm_006 = new Algorithm_006();
        double d = 2.0000;
        int n = 10;
        double res = algorithm_006.myPow(d, n);
        System.out.println(res);
        System.out.println(algorithm_006.myPow2(d, n));
    }


    public double myPow(double x, int n) {
        double res = 1.0;
        for (int i = 0; i < Math.abs(n); i++) {
            res *= x;
        }
        if (n >= 0) {
            return res;
        } else {
            return 1 / res;
        }
    }

    /**
     * 二分法。
     * @param x
     * @param n
     * @return
     */
    public double myPow2(double x, int n) {
        if (x == 0) {
            return 0;
        }
        long b = n;
        double res = 1.0;
        if (b < 0) {
            x = 1 / x;
            b = -b;
        }
        while (b > 0) {
            // 是奇数还是偶数，如果 == 1说明是奇数，偶数结果是0
            if  ((b & 1) == 1) {
                res  *= x;
            }
            x *= x;
            b >>= 1;
        }
        return res;
    }
}
