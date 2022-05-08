package top100;

/**
 * 70. 爬楼梯 [ https://leetcode-cn.com/problems/climbing-stairs/ ]
 * <p>
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 注意：给定 n 是一个正整数。
 * <p>
 * 示例 1：
 * <p>
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * <p>
 * 示例 2：
 * <p>
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 */
public class Top070_simple {

    public static void main(String[] args) {
        Top070_simple top070Simple = new Top070_simple();
        int n = 3;
        int res = top070Simple.climbStairs(n);
        System.out.println(res);
    }


    public int climbStairs(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n <= 3) {
            return n;
        }
        int pre = 2, cur = 3;
        for (int i = 4; i <= n; i++) {
            int sum = pre + cur;
            pre = cur;
            cur = sum;
        }
        return cur;
    }
}
