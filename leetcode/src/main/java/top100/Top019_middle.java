package top100;

import model.ListNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 19. 删除链表的倒数第 N 个结点。[https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/]
 * <p>
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 进阶：你能尝试使用一趟扫描实现吗？
 * <p>
 * 示例 1：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * <p>
 * 示例 2：
 * 输入：head = [1], n = 1
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 * <p>
 * 提示：
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 */
public class Top019_middle {

    public static void main(String[] args) {
        Top019_middle top19 = new Top019_middle();

        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode head2 = new ListNode(1);
        ListNode node = top19.removeNthFromEnd2(head2, 1);
        System.out.println(node);
    }


    /**
     * 基本做法,方法一：计算链表长度
     * 获取链表的长度L，那么要删除的元素就是 L- n + 1,也就是L-n的下一个元素
     * 时间复杂度：O(L)，其中 L是链表的长度
     * 空间复杂度：0(1)
     *
     * @return
     */
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        int length = getLength(head);
        ListNode node = new ListNode(0, head);
        ListNode cur = node;
        for (int i = 1; i < length - n + 1; i++) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return node.next;
    }
    public int getLength(ListNode head) {
        int len = 1;
        while (head.next != null) {
            len++;
            head = head.next;
        }
        return len;
    }



    /**
     * 方法二：栈。
     * 时间复杂度：O(L)，其中 L是链表的长度
     * 空间复杂度：0(L)
     *
     * @param head
     * @return
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        Deque<ListNode> stack = new LinkedList<>();
        ListNode cur = dummy;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        // 出站
        for (int i = 0; i < n; ++i) {
            stack.pop();
        }
        ListNode peek = stack.peek();
        peek.next = peek.next.next;
        return dummy.next;
    }



    public ListNode removeNthFromEnd(ListNode head, int n) {
        return removeNode(head, n) == n ? head.next : head;
    }
    /**
     * 递归，return计数法
     *
     * @param node
     * @param n
     * @return
     */
    public int removeNode(ListNode node, int n) {
        if (node.next == null) return 1;
        int m = removeNode(node.next, n);
        if (m == n) {
            if (m == 1) {
                node.next = null;
            } else {
                node.next = node.next.next;
            }
        }
        return m + 1;
    }

    /**
     * 快慢指针
     * <p>
     * //0 | 1 2 3 4 5 | 删除4
     * // 20 31 42 53
     * 时间复杂度：O(L)，其中 L是链表的长度
     * 空间复杂度：0(1)
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd3(ListNode head, int n) {
        ListNode p = new ListNode(0);
        p.next = head;
        ListNode fast = p;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        ListNode slow = p;
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return p.next;
    }

}


