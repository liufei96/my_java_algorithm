package 链表;

import model.ListNode;

/**
 * 203. 移除链表元素 [ https://leetcode-cn.com/problems/remove-linked-list-elements/ ]
 * <p>
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [1,2,6,3,4,5,6], val = 6
 * 输出：[1,2,3,4,5]
 * <p>
 * 示例 2：
 * <p>
 * 输入：head = [], val = 1
 * 输出：[]
 * <p>
 * 示例 3：
 * <p>
 * 输入：head = [7,7,7,7], val = 7
 * 输出：[]
 * <p>
 * 提示：
 * <p>
 * 列表中的节点数目在范围 [0, 104] 内
 * 1 <= Node.val <= 50
 * 0 <= val <= 50
 */
public class Top0203_simple {

    public static void main(String[] args) {
        Top0203_simple top0203_simple = new Top0203_simple();
        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(6, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6, null)))))));
        int val = 6;
        ListNode ans = top0203_simple.removeElements(listNode, val);
        System.out.println(ans);
        ListNode listNode2 = new ListNode(7, new ListNode(7, new ListNode(7, new ListNode(7, null))));
        int val2 = 7;
        ListNode ans1 = top0203_simple.removeElements2(listNode2, val2);
        System.out.println(ans1);
        ListNode listNode3 = new ListNode(1, new ListNode(2, new ListNode(6, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6, null)))))));
        int val3 = 6;
        System.out.println(top0203_simple.removeElements3(listNode3, val3));
        ListNode listNode4 = new ListNode(1, new ListNode(2, new ListNode(6, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6, null)))))));
        int val4 = 6;
        System.out.println(top0203_simple.removeElements4(listNode4, val4));
    }

    /**
     * 添加虚节点方式
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return head;
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return dummy.next;
    }

    /**
     * 不添加虚节点方式
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements2(ListNode head, int val) {
        // 头结点是需要移除的单独处理
        while (head != null && head.val == val) {
            head = head.next;
        }
        if (head == null) return head;
        // 已确认head.val != val
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    /**
     * 递归
     * <p>
     * 复杂度分析
     * 时间复杂度：O(n)O(n)，其中 nn 是链表的长度。递归过程中需要遍历链表一次。
     * 空间复杂度：O(n)O(n)，其中 nn 是链表的长度。空间复杂度主要取决于递归调用栈，最多不会超过 nn 层。
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements3(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        head.next = removeElements3(head.next, val);
        return head.val == val ? head.next : head;
    }

    /**
     * 迭代
     * <p>
     * 复杂度分析
     * 时间复杂度：O(n)O(n)，其中 nn 是链表的长度。递归过程中需要遍历链表一次。
     * 空间复杂度：O(n)O(n)，其中 nn 是链表的长度。空间复杂度主要取决于递归调用栈，最多不会超过 nn 层。
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements4(ListNode head, int val) {
        ListNode dummy = new ListNode(0, head);
        ListNode tmp = dummy;
        while (tmp.next != null) {
            if (tmp.next.val == val) {
                tmp.next = tmp.next.next;
            } else {
                tmp = tmp.next;
            }
        }
        return dummy.next;
    }
}
