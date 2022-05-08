package 剑指Offer;

/**
 * 剑指 Offer 16. 数值的整数次方 [ https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/ ]
 *
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。
 *
 *
 * 示例 1：
 *
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 * 示例 2：
 *
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 * 示例 3：
 *
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/22 = 1/4 = 0.25
 *
 * 提示：
 *
 * -100.0 < x < 100.0
 * -231 <= n <= 231-1
 * -104 <= xn <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Algorithm_016 {

    public static void main(String[] args) {
        Algorithm_016 algorithm_006 = new Algorithm_016();
        double d = 2.0000;
        int n = -2;
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
