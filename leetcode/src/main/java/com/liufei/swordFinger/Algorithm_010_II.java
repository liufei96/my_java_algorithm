package com.liufei.swordFinger;

/**
 * 剑指 Offer 10- II. 青蛙跳台阶问题  [ https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/ ]
 * <p>
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 *
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n级的台阶总共有多少种跳法。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：2
 * 示例 2：
 *
 * 输入：n = 7
 * 输出：21
 * 示例 3：
 *
 * 输入：n = 0
 * 输出：1
 * 提示：
 *
 * 0 <= n <= 100
 *
 */
public class Algorithm_010_II {

    /**
     * 问题分析：
     * 正常分析法： a.如果两种跳法，1阶或者2阶，那么假定第一次跳的是一阶，那么剩下的是n-1个台阶，跳法是f(n-1); b.假定第一次跳的是2阶，
     * 那么剩下的是n-2个台阶，跳法是f(n-2) c.由a，b假设可以得出总跳法为: f(n) = f(n-1) + f(n-2) d.然后通过实际的情况可以得出：只有一阶的时候 f(1) = 1 ,
     * 只有两阶的时候可以有 f(2) = 2 找规律分析法： f(1) = 1, f(2) = 2, f(3) = 3, f(4) = 5， 可以总结出f(n) = f(n-1) + f(n-2)的规律。
     * 但是为什么会出现这样的规律呢？假设现在6个台阶，我们可以从第5跳一步到6，这样的话有多少种方案跳到5就有多少种方案跳到6，另外我们也可以从4跳两步跳到6，
     * 跳到4有多少种方案的话，就有多少种方案跳到6，其他的不能从3跳到6什么的啦，所以最后就是f(6) = f(5) + f(4)；这样子也很好理解变态跳台阶的问题了。
     * <p>
     * 所以这道题其实就是斐波那契数列的问题。 代码只需要在上一题的代码稍做修改即可。
     * 和上一题唯一不同的就是这一题的初始元素变为 1 2 3 5 8.....而上一题为1 1 2 3 5 .......。
     * 另外这一题也可以用递归做，但是递归效率太低，所以我这里只给出了迭代方式的代码。
     */

    public static void main(String[] args) {
        Algorithm_010_II algorithm_010II = new Algorithm_010_II();
        int n = 44;
        int res = algorithm_010II.jumpFloor(n);
        System.out.println(res);
    }

    int jumpFloor(int number) {
        if (number <= 0) {
            return 1;
        }
        if (number <= 3) {
            return number;
        }
        int pre = 2, cur = 3;
        for (int i = 4; i <= number; i++) {
            int sum = pre + cur;
            pre = cur;
            cur = sum % 1000000007;
        }
        return cur;
    }
}
