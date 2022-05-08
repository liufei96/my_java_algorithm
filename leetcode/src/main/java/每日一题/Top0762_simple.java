package 每日一题;

/**
 * 762. 二进制表示中质数个计算置位  [ https://leetcode-cn.com/problems/prime-number-of-set-bits-in-binary-representation/ ]
 *
 * 给你两个整数left和right ，在闭区间 [left, right]范围内，统计并返回 计算置位位数为质数 的整数个数。
 *
 * 计算置位位数 就是二进制表示中 1 的个数。
 *
 * 例如， 21的二进制表示10101有 3 个计算置位。
 *
 * 示例 1：
 *
 * 输入：left = 6, right = 10
 * 输出：4
 * 解释：
 * 6 -> 110 (2 个计算置位，2 是质数)
 * 7 -> 111 (3 个计算置位，3 是质数)
 * 9 -> 1001 (2 个计算置位，2 是质数)
 * 10-> 1010 (2 个计算置位，2 是质数)
 * 共计 4 个计算置位为质数的数字。
 * 示例 2：
 *
 * 输入：left = 10, right = 15
 * 输出：5
 * 解释：
 * 10 -> 1010 (2 个计算置位, 2 是质数)
 * 11 -> 1011 (3 个计算置位, 3 是质数)
 * 12 -> 1100 (2 个计算置位, 2 是质数)
 * 13 -> 1101 (3 个计算置位, 3 是质数)
 * 14 -> 1110 (3 个计算置位, 3 是质数)
 * 15 -> 1111 (4 个计算置位, 4 不是质数)
 * 共计 5 个计算置位为质数的数字。
 *
 *
 * 提示：
 *
 * 1 <= left <= right <= 106
 * 0 <= right - left <= 104
 *
 */
public class Top0762_simple {
    public static void main(String[] args) {
        Top0762_simple top0762_simple = new Top0762_simple();
        int left = 10, right = 15;
        int i = top0762_simple.countPrimeSetBits(left, right);
        System.out.println(i);
    }

    public int countPrimeSetBits2(int left, int right) {
        int count = 0;
        for (int i = left; i <= right; i++) {
            count += 665772 >> Integer.bitCount(i) & 1;
        }
        return count;
    }

    public int countPrimeSetBits(int left, int right) {
        int count = 0;
        for (int i = left; i <= right; i++) {
            int n = Integer.bitCount(i);
            if (isPrime(n)) {
                count++;
            }
        }
        return count;
    }


    public static boolean isPrime(int n) {
        if (n <= 3) {
            return n > 1;
        }
        if (n % 2 == 0) {
            return false;
        }
        //只需判断一个数能否被小于sqrt(n)的奇数整除
        int sqrt = (int) Math.sqrt(n);
        for (int i = 3; i <= sqrt; i += 2) {
            if (n % 2 == 0 || n % i == 0) {
                return false;
            }
        }
        return true;
    }

}
