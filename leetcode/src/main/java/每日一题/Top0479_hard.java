package 每日一题;

/**
 * 479. 最大回文数乘积 [ https://leetcode-cn.com/problems/largest-palindrome-product/ ]
 * <p>
 * <p>
 * 给定一个整数 n ，返回 可表示为两个 n 位整数乘积的 最大回文整数 。因为答案可能非常大，所以返回它对 1337 取余 。
 * <p>
 * 示例 1:
 * <p>
 * 输入：n = 2
 * 输出：987
 * 解释：99 x 91 = 9009, 9009 % 1337 = 987
 * 示例 2:
 * <p>
 * 输入： n = 1
 * 输出： 9
 *  
 * <p>
 * 提示:
 * <p>
 * 1 <= n <= 8
 */
public class Top0479_hard {

    public static void main(String[] args) {
        int n = 3;
        int ans = largestPalindrome(n);
        System.out.println(ans);

        long a = (long) (Math.pow(10, n) - 1);
        for (long i = a; i >= 0 ; i--) {
            long num = i;
            for (long j = i; j > 0 ; j /= 10) {
                num = num * 10 + j % 10;
            }
            System.out.println(num);
        }
    }


    public static int largestPalindrome(int n) {
        if (n == 1) {
            return 9;
        }
        long a = (long) (Math.pow(10, n) - 1);
        for (long i = a; i >= 0; i--) {
            //从大到小构造回文串
            long number = i;
            for (long j = i; j > 0; j /= 10) {
                number = number * 10 + j % 10;
            }
            //判断number是否为满足条件的最大回文串
            //每次循环都从最大n位开始判断，循环判断条件为 k*k >= number
            //为什么k*k小于number就不用判断了，因为如果k*k小于number依旧存在number%k == 0，说明绝对出现了一个
            //（大于k的数）*（一个小于k的）等于该number,而在（大于k的数）*（大于k的数）> number时就满足条件(number%(大于k的数）== 0  return掉了),所以k*k小于number了就一定不存在number % k ==0;
            for (long k = a; k * k >= number; k--) {
                if (number % k == 0) {
                    return (int) (number % 1337);
                }
            }
        }
        return -1;
    }
}
