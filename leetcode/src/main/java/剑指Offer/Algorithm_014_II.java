package 剑指Offer;

/**
 * 剑指 Offer 14- II. 剪绳子 II  [ https://leetcode-cn.com/problems/jian-sheng-zi-ii-lcof/ ]
 *
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m - 1] 。
 * 请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 *
 * 示例 1：
 *
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1
 *
 * 示例2:
 *
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 ×4 = 36
 *
 * 提示：
 *
 * 2 <= n <= 1000
 *
 */
public class Algorithm_014_II {

    public static void main(String[] args) {
        Algorithm_014_II algorithm014Ii = new Algorithm_014_II();
        int n = 120;
        int ans = algorithm014Ii.cuttingRope(n);
        System.out.println(ans);
    }


    /**
     * 数论
     *
     * 任何大于1的数都可由2和3相加组成（根据奇偶证明）
     * 因为2*2=1*4，2*3>1*5, 所以将数字拆成2和3，能得到的积最大
     * 因为2*2*2<3*3, 所以3越多积越大 时间复杂度O(n/3)，用幂函数可以达到O(log(n/3)), 因为n不大，所以提升意义不大，我就没用。 空间复杂度常数复杂度O(1)
     *
     * @param n
     * @return
     */
    public int cuttingRope(int n) {
        if (n == 1 || n == 2)
            return 1;
        if (n == 3)
            return 2;
        long sum = 1;
        while (n > 4) {
            sum *= 3;
            sum = sum % 1000000007;
            n -= 3;
        }
        return (int) (sum * n % 1000000007);
    }
}
