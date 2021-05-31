package com.liufei.top100;

import com.liufei.model.ListNode;

import java.util.List;
import java.util.Stack;

/**
 * 25. K 个一组翻转链表 [ https://leetcode-cn.com/problems/reverse-nodes-in-k-group/ ]
 *
 * 给你一个链表，每k个节点一组进行翻转，请你返回翻转后的链表。
 *
 * k是一个正整数，它的值小于或等于链表的长度。
 *
 * 如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 进阶：
 *
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 *
 * 示例 2：
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 *
 * 示例 3：
 * 输入：head = [1,2,3,4,5], k = 1
 * 输出：[1,2,3,4,5]
 *
 * 示例 4：
 * 输入：head = [1], k = 1
 * 输出：[1]
 */
public class Top025_hard {


    public static void main(String[] args) {
        Top025_hard top025 = new Top025_hard();
        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode result = top025.reverseKGroup(listNode, 5);
        System.out.println(result);
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (k < 2) {
            return head;
        }
        ListNode cur = head;
        Stack<ListNode> stack = new Stack<>();
        for (int i = 1; i <= k; i++) {
            if (i != k) {
                stack.push(cur);
                cur = cur.next;
            } else {
                ListNode tmp = cur;
                while (!stack.isEmpty()) {
                    // 4 5
                    ListNode pre = stack.pop();
                    tmp.next = pre;
                    tmp = tmp.next;
                }
            }
        }
        return head;
    }

    public ListNode reverse(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        ListNode cur = head;
        for (int i = 0; i < k; i++) {
            ListNode next = cur.next;
            cur.next = dummy.next;
            dummy.next = cur;
            cur = next;
        }
        return dummy.next;
    }
}
