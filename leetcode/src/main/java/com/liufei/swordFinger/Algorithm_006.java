package com.liufei.swordFinger;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 剑指 Offer 06. 从尾到头打印链表 [ https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/ ]
 * <p>
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 */
public class Algorithm_006 {

    public static void main(String[] args) {
        Algorithm_006 algorithm006 = new Algorithm_006();
        ListNode listNode = new ListNode(1, new ListNode(3, new ListNode(2)));
        int[] ans = algorithm006.reversePrint(listNode);
        System.out.println(Arrays.toString(ans));
        System.out.println(Arrays.toString(algorithm006.reversePrint2(listNode)));
    }

    /**
     * 使用栈
     *
     * @param head
     * @return
     */
    public int[] reversePrint(ListNode head) {
        Deque<Integer> stack = new LinkedList<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }
        int[] ans = new int[stack.size()];
        int len = stack.size();
        for (int i = 0; i < len; i++) {
            ans[i] = stack.pop();
        }
        return ans;
    }

    /**
     * 不使用栈，有人说，反正都要遍历两次，那么我干嘛还要额外开辟空间
     *
     * @param head
     * @return
     */
    public int[] reversePrint2(ListNode head) {
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        int[] ans = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            ans[i] = head.val;
            head = head.next;
        }
        return ans;
    }
}
