package 每日一题;

/**
 * 1422. 分割字符串的最大得分 https://leetcode.cn/problems/maximum-score-after-splitting-a-string/
 * <p>
 * 给你一个由若干 0 和 1 组成的字符串 s ，请你计算并返回将该字符串分割成两个 非空 子字符串（即左 子字符串和 右 子字符串）所能获得的最大得分。
 * <p>
 * 「分割字符串的得分」为 左 子字符串中 0 的数量加上 右 子字符串中 1 的数量。
 * <p>
 * 示例 1：
 * 输入：s = "011101"
 * 输出：5
 * 解释：
 * 将字符串 s 划分为两个非空子字符串的可行方案有：
 * 左子字符串 = "0" 且 右子字符串 = "11101"，得分 = 1 + 4 = 5
 * 左子字符串 = "01" 且 右子字符串 = "1101"，得分 = 1 + 3 = 4
 * 左子字符串 = "011" 且 右子字符串 = "101"，得分 = 1 + 2 = 3
 * 左子字符串 = "0111" 且 右子字符串 = "01"，得分 = 1 + 1 = 2
 * 左子字符串 = "01110" 且 右子字符串 = "1"，得分 = 2 + 1 = 3
 * <p>
 * 示例 2：
 * <p>
 * 输入：s = "00111"
 * 输出：5
 * 解释：当 左子字符串 = "00" 且 右子字符串 = "111" 时，我们得到最大得分 = 2 + 3 = 5
 * 示例 3：
 * <p>
 * 输入：s = "1111"
 * 输出：3
 * <p>
 * 提示：
 * <p>
 * - 2 <= s.length <= 500
 * - 字符串 s 仅由字符 '0' 和 '1' 组成。
 */
class Leetcode_1422_simple {

    public static void main(String[] args) {
        String s = "1111";
        int maxScore = maxScore(s);
        System.out.println(maxScore);
    }

    public static int maxScore(String s) {
        // 一次遍历，获取1的总数量
        int oneTotalCount = 0, len = s.length();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '1') {
                oneTotalCount++;
            }
        }
        int zeroCount = 0, oneCount = 0, ans = 0;
        // 两个字符串必须都是非空，所以这里要 len - 1
        for (int i = 0; i < len - 1; i++) {
            if (s.charAt(i) == '0') {
                zeroCount++;
            } else {
                oneCount++;
            }
            ans = Math.max(ans, oneTotalCount - oneCount + zeroCount);
        }
        return ans;
    }
}