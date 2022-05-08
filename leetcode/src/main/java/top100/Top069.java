package top100;

/**
 * 69. x 的平方根  [ https://leetcode-cn.com/problems/sqrtx/ ]
 *
 * 给你一个非负整数 x ，计算并返回 x 的 平方根 。
 *
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 *
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 *
 * 示例 1：
 * 输入：x = 4
 * 输出：2
 *
 * 示例 2：
 * 输入：x = 8
 * 输出：2
 * 解释：8 的平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
 *
 * 提示：
 *
 * 0 <= x <= 231 - 1
 */
public class Top069 {

    public static void main(String[] args) {
        Top069 top069 = new Top069();
        int x = 8;
        int ans = top069.mySqrt2(x);
        System.out.println(ans);
    }

    public int mySqrt(int x) {
       int l = 0, r = x,  ans = -1;
       while (l <= r) {
           int mid = l + (r - l) / 2;
           if (mid * mid <= x) {
               l = mid + 1;
               ans = mid;
           } else {
               r = mid - 1;
           }
       }
       return ans;
    }

    public int mySqrt2(int x) {
        if (x == 0) return 0;
        int ans = (int) Math.exp(0.5 * Math.log(x));
        return (long)(ans + 1) * (ans + 1) <= x ? ans + 1 : ans;
    }
}
