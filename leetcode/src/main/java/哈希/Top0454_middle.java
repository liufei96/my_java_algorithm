package 哈希;

import java.util.*;

/**
 * 454. 四数相加 II  [https://leetcode-cn.com/problems/4sum-ii/]
 * <p>
 * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l)，使得 A[i] + B[j] + C[k] + D[l] = 0。
 * <p>
 * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 2^31 - 1 。
 * <p>
 * 例如:
 * <p>
 * 输入:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 * <p>
 * 输出:
 * 2
 * <p>
 * 解释:
 * 两个元组如下:
 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 */
public class Top0454_middle {

    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {-2, -1};
        int[] nums3 = {-1, 2};
        int[] nums4 = {0, 2};
        int ans = fourSumCount(nums1, nums2, nums3, nums4);
        System.out.println(ans);
    }

    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int ans = 0;
        Map<Integer, Integer> record = new HashMap<>();
        int tmp;
        for (int i : nums1) {
            for (int j : nums2) {
                tmp = i + j;
                record.compute(tmp, (k, v) -> v == null ? 1 : v + 1);
            }
        }
        for (int i : nums3) {
            for (int j : nums4) {
                tmp = i + j;
                if (record.containsKey(-tmp)) {
                    ans += record.get(-tmp);
                }
            }
        }
        return ans;
    }
}
