package top100;

import model.ListNode;

/**
 * 61. 旋转链表  [  https://leetcode-cn.com/problems/rotate-list/   ]
 * <p>
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 * <p>
 * 示例 1：
 * 输入：head = [1,2,3,4,5], k = 2
 * 细节： 5 -> 1 -> 2 -> 3 -> 4
 * 4 -> 5 -> 1 -> 2 -> 3 ->
 * 输出：[4,5,1,2,3]
 * <p>
 * <p>
 * 示例 2：
 * 输入：head = [0,1,2], k = 4
 * 细节：2 -> 0 -> 1
 * 1 -> 2 -> 0
 * 0 -> 1 -> 2
 * 2 -> 0 -> 1
 * 输出：[2,0,1]
 * <p>
 * 提示：
 * <p>
 * 链表中节点的数目在范围 [0, 500] 内
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 109
 */
public class Top061_middle {

    public static void main(String[] args) {
        Top061_middle top061Middle = new Top061_middle();
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        int k = 2;
        ListNode listNode = top061Middle.rotateRight(head, k);
        System.out.println(listNode);
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) return head;
        int len = 1;
        ListNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
            len++;
        }
        int add = len - k % len;
        if (add == len) {
            return head;
        }
        // 将链表首尾相连
        cur.next = head;
        while (add-- > 0) {
            cur = cur.next;
        }
        ListNode ret = cur.next;
        cur.next = null;
        return ret;
    }

}
