package com.liufei.top100;

import java.util.Stack;

public class Top002 {

    /**
     * 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
     *
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     *
     * 你可以假设除了数字 0 之外，这两个数都不会以 0开头。
     *
     * 示例1：
     * 2 -> 4 -> 3
     * 5 -> 6 -> 4
     * 结果：
     * 7 -> 0 -> 8
     *
     * 输入：l1 = [2,4,3], l2 = [5,6,4]
     * 输出：[7,0,8]
     * 解释：342 + 465 = 807.
     *
     * 示例2：
     * 输入：l1 = [0], l2 = [0]
     * 输出：[0]
     *
     * 示例3：
     * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
     * 输出：[8,9,9,9,0,0,0,1]
     *
     * 提示：
     * . 每个链表中的节点数在范围 [1, 100] 内
     * . 0 <= Node.val <= 9
     * . 题目数据保证列表表示的数字不含前导零
     */

    public static void main(String[] args) {
        Top002 top002 = new Top002();
//        int[] num1 = new int[] { 9,9,9,9,9,9,9 };
//        int[] num2 = new int[] { 9,9,9,9 };
//        ListNode listNode1 = top002.arrayToListNode(num1);
//        ListNode listNode2 = top002.arrayToListNode(num2);
//        ListNode listNode = top002.addTwoNumbers(listNode1, listNode2);
//        System.out.println(top002.addListNodeValToStringBuilder(listNode).toString());

        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.peek());

        System.out.println(28 % 10);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode listNode = new ListNode();
        ListNode next = listNode;
        int remainder = 0;
        while (l1 != null || l2 != null) {
            int sum = l1.val + l2.val + remainder;
            next.val = sum % 10;
            remainder = sum / 10;

            l1 = l1.next;
            l2 = l2.next;
        }
        return listNode;
    }

    /**
     * 将所有的listNode的val放入到StringBuilder中
     * @param listNode
     * @return
     */
    public StringBuilder addListNodeValToStringBuilder(ListNode listNode) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(listNode.val);
        ListNode next1 = listNode.next;
        while (next1 != null) {
            stringBuilder.append(next1.val);
            next1 = next1.next;
        }
        return stringBuilder;
    }

    /**
     * 将数组转换为ListNode
     * @param nums
     */
    public ListNode arrayToListNode(int[] nums) {
        ListNode listNode = new ListNode();
        if (nums == null || nums.length == 0) {
            return null;
        }
        ListNode next = listNode;
        for (int i = 0; i < nums.length; i++) {
            next.val = nums[i];
            if (i < nums.length - 1) {
                next.next = new ListNode();
                next = next.next;
            }
        }
        return listNode;
    }
}

class ListNode {
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
}