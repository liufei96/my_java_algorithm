package com.liufei.top100;

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
        ListNode node = top19.removeNthFromEnd2(head, 2);
        System.out.println(node);
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        return removeNode(head, n) == n ? head.next : head;
    }

    /**
     * 递归，return计数法
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
     *
     * //0 | 1 2 3 4 5 | 删除4
     * // 20 31 42 53
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode p = new ListNode(0);
        p.next = head;
        ListNode fast = p;
        for (int i = 0; i < n ; i++) {
            fast = fast.next;
        }
        ListNode slow = p;
        while(fast.next != null){
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return p.next;
    }


    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}


