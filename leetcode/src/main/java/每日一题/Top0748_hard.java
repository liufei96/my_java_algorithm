package 每日一题;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 768. 最多能完成排序的块 II https://leetcode.cn/problems/max-chunks-to-make-sorted-ii/
 * <p>
 * 这个问题和“最多能完成排序的块”相似，但给定数组中的元素可以重复，输入数组最大长度为2000，其中的元素最大为10**8。
 * <p>
 * arr是一个可能包含重复元素的整数数组，我们将这个数组分割成几个“块”，并将这些块分别进行排序。之后再连接起来，使得连接的结果和按升序排序后的原数组相同。
 * <p>
 * 我们最多能将数组分成多少块？
 * <p>
 * 示例 1:
 * <p>
 * 输入: arr = [5,4,3,2,1]
 * 输出: 1
 * 解释:
 * 将数组分成2块或者更多块，都无法得到所需的结果。
 * 例如，分成 [5, 4], [3, 2, 1] 的结果是 [4, 5, 1, 2, 3]，这不是有序的数组。
 * 示例 2:
 * <p>
 * 输入: arr = [2,1,3,4,4]
 * 输出: 4
 * 解释:
 * 我们可以把它分成两块，例如 [2, 1], [3, 4, 4]。
 * 然而，分成 [2, 1], [3], [4], [4] 可以得到最多的块数。
 *
 * 注意:
 *
 * - arr的长度在[1, 2000]之间。
 * - arr[i]的大小在[0, 10**8]之间。
 */
public class Top0748_hard {

    public static void main(String[] args) {
        Top0748_hard top0748_hard = new Top0748_hard();
        int[] arr = {1, 1, 0, 0, 1};
        int i1 = top0748_hard.maxChunksToSorted(arr);
        int i2 = top0748_hard.maxChunksToSorted2(arr);
        System.out.println(i1);
        System.out.println(i2);
    }

    /**
     * 单调栈 (速度更快)
     *
     * @param arr
     * @return
     */
    public int maxChunksToSorted(int[] arr) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int num : arr) {
            if (stack.isEmpty() || num >= stack.peek()) {
                stack.push(num);
            } else {
                int mx = stack.poll();
                while (!stack.isEmpty() && stack.peek() > num) {
                    stack.pop();
                }
                stack.push(mx);
            }
        }
        return stack.size();
    }

    /**
     * 哈希 + 排序
     *
     * @return
     */
    public int maxChunksToSorted2(int[] arr) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int res = 0;
        int[] sortedArr = arr.clone();
        Arrays.sort(sortedArr);
        for (int i = 0; i < sortedArr.length; i++) {
            int x = arr[i], y = sortedArr[i];
            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
            if (cnt.get(x) == 0) {
                cnt.remove(x);
            }
            cnt.put(y, cnt.getOrDefault(y, 0) - 1);
            if (cnt.get(y) == 0) {
                cnt.remove(y);
            }
            if (cnt.isEmpty()) {
                res++;
            }
        }
        return res;
    }
}
