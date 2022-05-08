package 剑指Offer;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 剑指 Offer 22. 链表中倒数第k个节点 [ https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/ ]
 *
 * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
 *
 * 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。
 *
 *
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 k = 2.
 * 返回链表 4->5.
 *
 */
public class Algorithm_022 {

    public static void main(String[] args) {
        Algorithm_022 algorithm_022 = new Algorithm_022();
        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        int k = 1;
        ListNode res = algorithm_022.getKthFromEnd(listNode, k);
        System.out.println(res);
        System.out.println(algorithm_022.getKthFromEnd2(listNode, k));
    }

    /**
     * 使用队列
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        Deque<ListNode> deque = new ArrayDeque<>();
        ListNode cur = head;
        while (cur != null) {
            deque.add(cur);
            cur = cur.next;
        }
        int len = deque.size() - k;
        for (int i = 0; i < len; i++) {
            deque.poll();
        }
        return deque.peek();
    }

    /**
     * 使用快慢指针
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd2(ListNode head, int k) {
        ListNode pre = null, cur = null;
        pre = cur = head;
        int a = k;
        // 记录节点长度
        int count = 0;
        while (cur != null) {
            cur = cur.next;
            count++;
            if (k < 1) {
                pre = pre.next;
            }
            k--;
        }
        if (count < a) return null;
        return pre;
    }
}

