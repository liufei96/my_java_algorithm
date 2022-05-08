package top100;

/**
 * 整数反转 [  https://leetcode-cn.com/problems/reverse-integer/  ]
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 *
 * 如果反转后整数超过 32 位的有符号整数的范围[−2^31, 2^31− 1] ，就返回 0。
 *
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 *
 * 示例 1：
 * 输入：x = 123
 * 输出：321
 *
 * 示例 2：
 * 输入：x = -123
 * 输出：-321
 *
 * 示例 3：
 * 输入：x = 120
 * 输出：21
 *
 * 示例 4：
 * 输入：x = 0
 * 输出：0
 *
 *
 */
public class Top007_simple {

    public static void main(String[] args) {
        // TODO
        Top007_simple top007_simple = new Top007_simple();
//        int x = 123;
//        int x = -123;
        int x = -1563847412;
        System.out.println(top007_simple.reverse(x));
    }

    public int reverse(int x) {
        String str = String.valueOf(Math.abs(x));
        int res = 0;
        int tmp = 1;
        for (int i = 1; i <= str.length(); i++) {
            int num = (str.charAt(i - 1) - '0') * tmp;
            if (num / tmp != (str.charAt(i - 1) - '0')) {
                return 0;
            }
            if (x > 0 && Integer.MAX_VALUE - num < res) {
                return 0;
            }
            if (x < 0 && Integer.MIN_VALUE + num > -res) {
                return 0;
            }
            res += num;
            tmp *= 10;
        }
        return x > 0 ? res : -res;
    }
}
