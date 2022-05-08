package 每日一题;

/**
 * 868. 二进制间距 [ https://leetcode-cn.com/problems/binary-gap/ ]
 * <p>
 * 给定一个正整数 n，找到并返回 n 的二进制表示中两个 相邻 1 之间的 最长距离 。如果不存在两个相邻的 1，返回 0 。
 * <p>
 * 如果只有 0 将两个 1 分隔开（可能不存在 0 ），则认为这两个 1 彼此 相邻 。两个 1 之间的距离是它们的二进制表示中位置的绝对差。例如，"1001" 中的两个 1 的距离为 3 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 22
 * 输出：2
 * 解释：22 的二进制是 "10110" 。
 * 在 22 的二进制表示中，有三个 1，组成两对相邻的 1 。
 * 第一对相邻的 1 中，两个 1 之间的距离为 2 。
 * 第二对相邻的 1 中，两个 1 之间的距离为 1 。
 * 答案取两个距离之中最大的，也就是 2 。
 * 示例 2：
 * <p>
 * 输入：n = 8
 * 输出：0
 * 解释：8 的二进制是 "1000" 。
 * 在 8 的二进制表示中没有相邻的两个 1，所以返回 0 。
 * 示例 3：
 * <p>
 * 输入：n = 5
 * 输出：2
 * 解释：5 的二进制是 "101" 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 109
 */
public class Top0868_simple {

    public static void main(String[] args) {
        int n = 5;
        System.out.println(binaryGap(n));
    }


    public static int binaryGap(int n) {
        String s = Integer.toBinaryString(n);
        int max = 0, preIndex = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                if (preIndex != -1) {
                    max = Math.max(i - preIndex, max);
                }
                preIndex = i;
            }
        }
        return max;
    }

    /**
     * 我们可以使用一个循环从 n 二进制表示的低位开始进行遍历，并找出所有的 1。我们用一个变量 last 记录上一个找到的 1 的位置。如果当前在第 i 位找到了 1，那么就用 i−last 更新答案，再将 last 更新为 i 即可。
     * <p>
     * 在循环的每一步中，我们可以使用位运算 n & 1 获取 n 的最低位，判断其是否为 1。在这之后，我们将 n 右移一位：n = n>>1，这样在第 i 步时，n & 1 得到的就是初始 n 的第 i 个二进制位。
     *
     * @param n
     * @return
     */
    public static int binaryGap2(int n) {
        int last = -1, ans = 0;
        for (int i = 0; n != 0; ++i) {
            if ((n & 1) == 1) {
                if (last != -1) {
                    ans = Math.max(ans, i - last);
                }
                last = i;
            }
            n >>= 1;
        }
        return ans;
    }
}
