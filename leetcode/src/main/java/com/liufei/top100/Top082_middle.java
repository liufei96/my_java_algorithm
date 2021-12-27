package com.liufei.top100;

import com.liufei.model.ListNode;

/**
 *
 * 82. 删除排序链表中的重复元素 II [https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/]
 *
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。
 *
 * 返回同样按升序排列的结果链表。
 */
public class Top082_middle {

    public static void main(String[] args) {
        Top082_middle top082_middle = new Top082_middle();
        ListNode listNode = new ListNode(1, new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3)))));
        ListNode listNode2 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3, new ListNode(4, new ListNode(4, new ListNode(5)))))));
        ListNode res = top082_middle.deleteDuplicates(listNode);
        ListNode res2 = top082_middle.deleteDuplicates2(listNode2);
        System.out.println(res);
        System.out.println(res2);
    }

    /**
     * 一次遍历
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int x = cur.next.val;
                while (cur.next != null && cur.next.val == x) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    /**
     * 递归法
     * @param head
     * @return
     */
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        if (head.val == head.next.val) {
            int x = head.val;
            while (head.next != null && head.next.val == x) {
                head = head.next;
            }
            head = deleteDuplicates(head.next);
        } else {
            head.next = deleteDuplicates(head.next);
        }
        return head;
    }
}
