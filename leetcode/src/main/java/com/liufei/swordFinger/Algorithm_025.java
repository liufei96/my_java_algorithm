package com.liufei.swordFinger;


/**
 * 剑指 Offer 25. 合并两个排序的链表。 [ https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/ ]
 * <p>
 * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
 * <p>
 * 示例1：
 * <p>
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class Algorithm_025 {

    public static void main(String[] args) {
        Algorithm_025 algorithm010 = new Algorithm_025();
        ListNode listNode1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode listNode2 = new ListNode(1, new ListNode(3, new ListNode(4, new ListNode(5))));
        ListNode listNode = algorithm010.mergeTwoLists(listNode1, listNode2);
        System.out.println(listNode);

        ListNode listNode3 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode listNode4 = new ListNode(1, new ListNode(3, new ListNode(4, new ListNode(5))));
        ListNode listNode_tmp = algorithm010.mergeTwoLists2(listNode3, listNode4);
        System.out.println(listNode_tmp);

    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 == null ? l2 : l1;
        return dummy.next;
    }

    /**
     * 递归计算
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
       if (l1 == null) {
           return l2;
       } else if (l2 == null) {
           return l1;
       } else if (l1.val < l2.val) {
           l1.next = mergeTwoLists2(l1.next, l2);
           return l1;
       } else {
           l2.next = mergeTwoLists2(l1, l2.next);
           return l2;
       }
    }
}
