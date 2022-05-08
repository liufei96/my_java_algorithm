package 动态规划;

/**
 *
 * 斐波那契数列
 *
 * 1 1 2 3 5 8 13 21 34 ...
 * 这个数列从第3项开始，每一项都等于前两项之和。
 * 所以，对于任意的f(n) = f(n-1) + f(n-2) (n >= 3)
 */
public class Fibonacci_Sequence {

    public static void main(String[] args) {
        int n = 40;
        int[] nums = new int[n + 1];
        int fib = fib2(nums, n);

        System.out.println(fib);
        System.out.println(fib3(n));
        System.out.println(fib4(n));
    }

    /**
     * 暴力递归，复杂度是 2^n
     * @param n
     * @return
     */
    public static int fib(int n) {
        if  (n == 1 || n == 2) return 1;
        return fib(n - 1) + fib(n - 2);
    }

    /**
     * 加个备忘录，已经计算的就不再计算了
     *
     * 实际上，带「备忘录」的递归算法，把一棵存在巨量冗余的递归树通过「剪枝」，改造成了一幅不存在冗余的递归图，极大减少了子问题（即递归图中节点）的个数
     *
     * 递归算法的时间复杂度怎么计算？就是用子问题个数乘以解决一个子问题需要的时间。
     *
     * 子问题个数，即图中节点的总数，由于本算法不存在冗余计算，子问题就是 f(1), f(2), f(3) … f(20)，数量和输入规模 n = 20 成正比，所以子问题个数为 O(n)。
     *
     * 解决一个子问题的时间，同上，没有什么循环，时间为 O(1)。
     *
     * 所以，本算法的时间复杂度是 O(n)。比起暴力算法，是降维打击。
     *
     * @param n
     * @return
     */
    public static int fib2(int[] nums , int n) {
        if  (n == 1 || n == 2) return 1;
        if (nums[n] != 0) return nums[n];
        nums[n] = fib2(nums, n - 1) + fib2(nums, n - 2);
        return nums[n];
    }


    /**
     * 3、dp 数组的迭代解法
     * 有了上一步「备忘录」的启发，我们可以把这个「备忘录」独立出来成为一张表，就叫做 DP table 吧，在这张表上完成「自底向上」的推算岂不美哉！
     * @param n
     * @return
     */
    public static int fib3(int n) {
        if  (n == 1 || n == 2) return 1;
        int[] dp = new int[n + 1];
        dp[1] = dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 在方法3的基础上在优化，当前状态只和之前的两个状态有关，其实并不需要那么长的一个 DP table 来存储所有的状态
     * @param n
     * @return
     */
    public static int fib4(int n) {
        if  (n == 1 || n == 2) return 1;
        int pre = 1, cur = 1;
        for (int i = 3; i <= n; i++) {
            int sum = pre + cur;
            pre = cur;
            cur = sum;
        }
        return cur;
    }
}
