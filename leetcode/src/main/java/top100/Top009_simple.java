package top100;

import java.util.Scanner;

/**
 * 回文数  [ https://leetcode-cn.com/problems/palindrome-number/ ]
 *
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 *
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
 *
 */
public class Top009_simple {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        boolean res = isPalindrome2(num);
        System.out.println(res);
    }

    /**
     * 第一个想法是将数字转换为字符串
     * 使用字符串，但是需要额外的空间
     * @param x
     * @return
     */
    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        String str = String.valueOf(x);
        int len = str.length();
        int begin = 0;
        int end = len - 1;
        while (end - begin >= 1) {
            if (str.charAt(begin) != str.charAt(end)) {
                return false;
            }
            begin++;
            end--;
        }
        return true;
    }

    /**
     * 第二个想法是将数字本身反转，然后将反转后的数字与原始数字进行比较，如果它们是相同的，那么这个数字就是回文。
     * 时间复杂度：时间复杂度：O(logn)
     * 空间复杂度：O(1)。我们只需要常数空间存放若干变量
     * @param x
     * @return
     */
    public static boolean isPalindrome2(int x) {
        /**
         * 如上所述，当 x < 0 时，x 不是回文数。
         * 同样地，如果数字的最后一位是 0，为了使该数字为回文，
         * 则其第一位数字也应该是 0
         * 只有 0 满足这一属性
         */
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }
        // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x == revertedNumber ||  x == revertedNumber / 10;
    }
}
