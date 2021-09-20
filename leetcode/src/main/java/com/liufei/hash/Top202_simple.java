package com.liufei.hash;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 202. 快乐数  [ https://leetcode-cn.com/problems/happy-number/ ]
 * <p>
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * <p>
 * 「快乐数」定义为：
 * <p>
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果 可以变为 1，那么这个数就是快乐数。
 * 如果 n 是快乐数就返回 true ；不是，则返回 false 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：19
 * 输出：true
 * 解释：
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 * <p>
 * 示例 2：
 * <p>
 * 输入：n = 2
 * 输出：false
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 231 - 1
 */
public class Top202_simple {

    public static void main(String[] args) {
        Top202_simple top202_simple = new Top202_simple();
        int n = 19;
        boolean happy = top202_simple.isHappy2(n);
        System.out.println(happy);
    }

    /**
     * 快慢指针
     * @param n
     * @return
     */
    public boolean isHappy2(int n) {
        int slowRunner = n;
        int fastRunner = getNextNumber(n);
        while (slowRunner != 1 && fastRunner != slowRunner) {
            slowRunner = getNextNumber(slowRunner);
            fastRunner = getNextNumber(fastRunner);
        }
        return fastRunner == 1;
    }

    public boolean isHappy(int n) {
        Set<Integer> record = new HashSet<>();
        while (n != 1 && !record.contains(n)) {
            record.add(n);
            n = getNextNumber2(n);
        }
        return n == 1;
    }

    private int getNextNumber(int n) {
        int sum = 0;
        String str = String.valueOf(n);
        for (int i = 0; i < str.length(); i++) {
            sum += Math.pow(str.charAt(i) - '0', 2);
        }
        return sum;
    }

    private int getNextNumber2(int n) {
        int res = 0;
        while (n > 0) {
            int tmp = n % 10;
            res += tmp * tmp;
            n = n / 10;
        }
        return res;
    }
}
