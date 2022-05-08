package 剑指Offer;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 剑指 Offer 09. 用两个栈实现队列 [ https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/ ]
 *
 * 用两个栈实现一个队列。队列的声明如下，
 * 请实现它的两个函数 appendTail 和 deleteHead ，
 * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead操作返回 -1 )
 *
 * 示例 1：
 *
 * 输入：
 * ["CQueue","appendTail","deleteHead","deleteHead"]
 * [[],[3],[],[]]
 * 输出：[null,null,3,-1]
 *
 * 示例 2：
 *
 * 输入：
 * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
 * [[],[],[5],[2],[],[]]
 * 输出：[null,-1,null,null,5,2]
 *
 *
 */
public class CQueue {

    Deque<Integer> stack1;
    Deque<Integer> stack2;

    public CQueue() {
        stack1 = new LinkedList<>();
        stack2 = new LinkedList<>();
    }

    public void appendTail(int value) {
        stack1.push(value);
    }

    public int deleteHead() {
        // stack1只负责进。 stack2负责出。只有当stack2为空时，才将stack1中的元素移动到stack2，防止乱序
        // 使用LinkedList的速度比Stack的快
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        if (stack2.isEmpty()) {
            return -1;
        } else {
            return stack2.pop();
        }
    }

    public static void main(String[] args) {
        CQueue queue = new CQueue();
        queue.appendTail(1);
        queue.appendTail(8);
        queue.appendTail(3);
        System.out.println(queue.deleteHead());
        queue.appendTail(4);
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
    }
}
