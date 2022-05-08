package 剑指Offer;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 剑指 Offer 31. 栈的压入、弹出序列 [ https://leetcode-cn.com/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof/ ]
 * <p>
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。
 * 例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
 * <p>
 * 示例 1：
 * <p>
 * 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * 输出：true
 * 解释：我们可以按以下顺序执行：
 * push(1), push(2), push(3), push(4), pop() -> 4,
 * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
 * 输出：false
 * 解释：1 不能在 2 之前弹出。
 * <p>
 * 提示：
 * <p>
 * 0 <= pushed.length == popped.length <= 1000
 * 0 <= pushed[i], popped[i] < 1000
 * pushed是popped的排列。
 */
public class Algorithm_031 {


    public static void main(String[] args) {
        Algorithm_031 algorithm031 = new Algorithm_031();
        int[] pushed = {1, 2, 3, 4, 5};
        int[] popped = {4, 3, 5, 1, 2};
        boolean res = algorithm031.validateStackSequences(pushed, popped);
        System.out.println(res);
    }


    /**
     * 【思路】借用一个辅助的栈，遍历压栈顺序，先讲第一个放入栈中，这里是1，然后判断栈顶元素是不是出栈顺序的第一个元素，这里是4，很显然1≠4，所以我们继续压栈，
     * 直到相等以后开始出栈，出栈一个元素，则将出栈顺序向后移动一位，直到不相等，这样循环等压栈顺序遍历完成，如果辅助栈还不为空，说明弹出序列不是该栈的弹出顺序。
     *
     * @param pushed
     * @param popped
     * @return
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        // 辅助栈
        Deque<Integer> stack = new LinkedList<>();
        int len = pushed.length;
        // 标识弹出栈序列的问题
        int popIndex = 0;
        for (int i = 0; i < len; i++) {
            stack.push(pushed[i]);
            while (!stack.isEmpty() && stack.peek() == popped[popIndex]) {
                stack.pop();
                popIndex++;
            }
        }
        return stack.isEmpty();
    }
}
