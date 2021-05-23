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
public class Top24_middle {

    public static void main(String[] args) {
        Top24_middle top24 = new Top24_middle();
        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3)));
        ListNode listNode1 = top24.swapPairs(listNode);
        System.out.println(listNode1);
    }


    /**
     * 递归法
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

    public ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        return next;
    }
}
