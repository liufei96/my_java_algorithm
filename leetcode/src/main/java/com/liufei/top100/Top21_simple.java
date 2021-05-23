package com.liufei.top100;

import com.liufei.model.ListNode;

/**
 * 21. 合并两个有序链表 [ https://leetcode-cn.com/problems/merge-two-sorted-lists/ ]
 * <p>
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * <p>
 * 示例 1：
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * <p>
 * 示例 2：
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 */
public class Top21_simple {

    public static void main(String[] args) {
        Top21_simple top21 = new Top21_simple();
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode listNode = top21.mergeTwoLists(l1, l2);
        System.out.println(listNode);
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode cur = head;
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
        // 任一为空，直接连接另一条链表
        cur.next = l1 == null ? l2 : l1;
        return head.next;
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
           l1.next = mergeTwoLists(l1.next, l2);
           return l1;
       } else {
           l2.next = mergeTwoLists(l2.next, l1);
           return l2;
       }
    }
}
