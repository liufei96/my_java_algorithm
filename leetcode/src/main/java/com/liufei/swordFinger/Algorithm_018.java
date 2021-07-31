package com.liufei.swordFinger;

/**
 * 剑指 Offer 18. 删除链表的节点 [ https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/ ]
 * <p>
 * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
 * <p>
 * 返回删除后的链表的头节点。
 * <p>
 * 注意：此题对比原题有改动
 * <p>
 * 示例 1:
 * <p>
 * 输入: head = [4,5,1,9], val = 5
 * 输出: [4,1,9]
 * 解释: 给定你链表中值为5的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
 * 示例 2:
 * <p>
 * 输入: head = [4,5,1,9], val = 1
 * 输出: [4,5,9]
 * 解释: 给定你链表中值为1的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
 * <p>
 * 说明：
 * <p>
 * 题目保证链表中节点的值互不相同
 * 若使用 C 或 C++ 语言，你不需要 free 或 delete 被删除的节点
 */
public class Algorithm_018 {

    public static void main(String[] args) {
        Algorithm_018 algorithm018 = new Algorithm_018();
        ListNode listNode = new ListNode(4, new ListNode(5, new ListNode(1, new ListNode(9))));
        int val = 9;
        ListNode res = algorithm018.deleteNode(listNode, val);
        System.out.println(res);

        ListNode listNode2 = new ListNode(4, new ListNode(5, new ListNode(1, new ListNode(9))));
        System.out.println(algorithm018.deleteNode2(listNode2, val));

        ListNode listNode3 = new ListNode(4, new ListNode(5, new ListNode(1, new ListNode(9))));
        System.out.println(algorithm018.deleteNode3(listNode3, val));
    }




    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) return null;
        ListNode pre = null;
        ListNode cur = head;
        if(cur.val == val) return head.next;
        while (cur.val != val) {
            pre = cur;
            cur = cur.next;
        }
        pre.next = pre.next.next;
        return head;
    }


    /**
     * 使用递归
     * @param head
     * @param val
     * @return
     */
    public ListNode deleteNode2(ListNode head, int val) {
        //1.递归结束的条件（这个条件能够直接得到递归结果）。
        //①这里最能直接得到结果的是head == null，因此刚开始可能结束条件只写了这一点。②但是当后面写出函数等价关系式后，要回过来检查是否有遗漏的条件。本题中：若要删除的节点为head节点的话，后面会报空指针异常。
        if (head == null) return null;
        if(head.val == val) return head.next;
        //2.函数等价关系式（这个关系式可以化简原函数关系式）。
        //①原关系式是：删除含有n个节点的链表中某个节点。②等价关系式是：删除链表中第2个到第n个共n-1个节点中的某个节点+特殊处理第一个1个节点。
        head.next = deleteNode2(head.next, val);
        return head;
    }


    public ListNode deleteNode3(ListNode head, int val) {
        if (head == null) return null;
        ListNode cur = head;
        if(cur.val == val) return head.next;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
                break;
            }
            cur = cur.next;
        }
        return head;
    }
}
