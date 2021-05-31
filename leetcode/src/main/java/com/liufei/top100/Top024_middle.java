package com.liufei.top100;

import com.liufei.model.ListNode;

/**
 * 24. 两两交换链表中的节点 [ https://leetcode-cn.com/problems/swap-nodes-in-pairs/ ]
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 示例 1：
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 * <p>
 * 示例 2：
 * 输入：head = []
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：head = [1]
 * 输出：[1]
 */
public class Top024_middle {

    public static void main(String[] args) {
        Top024_middle top24 = new Top024_middle();
        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        ListNode listNode1 = top24.swapPairs2(listNode);
        System.out.println(listNode1);
    }


    /**
     * 递归法
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        return next;
    }

    /**
     * 迭代法
     * 时间复杂度：O(n)，其中 nn 是链表的节点数量。需要对每个节点进行更新指针的操作。
     * 空间复杂度：O(1)。
     * @param head
     * @return
     */
    public ListNode swapPairs2(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode tmp = dummyHead;
        while (tmp.next != null && tmp.next.next != null) {
            ListNode node1 = tmp.next;
            ListNode node2 = tmp.next.next;
            tmp.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            tmp = node1;
        }
        return dummyHead.next;
    }
}
