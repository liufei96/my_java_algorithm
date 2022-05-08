package 动态规划;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 120. 三角形最小路径和  https://leetcode-cn.com/problems/triangle/
 * <p>
 * <p>
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 * <p>
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，
 * 那么下一步可以移动到下一行的下标 i 或 i + 1 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * 输出：11
 * 解释：如下面简图所示：
 * 2
 * 3 4
 * 6 5 7
 * 4 1 8 3
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * <p>
 * 示例 2：
 * <p>
 * 输入：triangle = [[-10]]
 * 输出：-10
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= triangle.length <= 200
 * triangle[0].length == 1
 * triangle[i].length == triangle[i - 1].length + 1
 * -104 <= triangle[i][j] <= 104
 * 进阶：
 * <p>
 * 你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题吗？
 */
public class Leetcode_120_middle {

    public static void main(String[] args) {
        Leetcode_120_middle leetcode_120_middle = new Leetcode_120_middle();
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(new Integer[]{2}));
        triangle.add(Arrays.asList(new Integer[]{3, 4}));
        triangle.add(Arrays.asList(new Integer[]{6, 5, 7}));
        triangle.add(Arrays.asList(new Integer[]{4, 1, 8, 3}));
        int res = leetcode_120_middle.minimumTotal2(triangle);
        System.out.println(res);
    }

    /**
     * dp。从下至上开始查找
     * dp的状态转移公式   dp[i][j] = min(dp[i + 1][j], dp[i + 1, j + 1]) + triangle[i][j];
     *
     * 注意：这里不是下面两个数哪个小选择哪个。所以这里不能使用贪心算法。贪心算法局部最优  -> 推到出全局最优
     *
     * 时间复杂度： O(m * n)
     * 空间复杂度：O(m * n)
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int row = triangle.size(), col = triangle.get(row - 1).size();
        // 这里使用的是个二位数组。其实可以使用一维数组。
        int[][] dp = new int[row][col];
        for (int i = col - 1; i >= 0; i--) {
            dp[row - 1][i] = triangle.get(row - 1).get(i);
        }
        // 从倒数第二行开始
        for (int i = row - 2; i >= 0 ; i--) {
            for (int j = triangle.get(i).size() - 1; j >= 0 ; j--) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        // 这一行可以去掉。仅仅值打印数组
        for (int i = 0; i < dp.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[0][0];
    }

    /**
     * 使用一维数组，状态压缩
     * @param triangle
     * @return
     */
    public int minimumTotal2(List<List<Integer>> triangle) {
        int row = triangle.size(), col = triangle.get(row - 1).size();
        // 这里使用的是个二位数组。其实可以使用一维数组。
        int[] dp = new int[col];
        for (int i = 0; i < col; i++) {
            dp[i] = triangle.get(row - 1).get(i);
        }
        // 从倒数第二行开始
        for (int i = row - 2; i >= 0 ; i--) {
            for (int j = 0; j < triangle.get(i).size() ; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
}
