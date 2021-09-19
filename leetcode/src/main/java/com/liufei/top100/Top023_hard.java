package com.liufei.top100;

import com.liufei.model.ListNode;

import java.util.PriorityQueue;

/**
 * 23. 合并K个升序链表 [ https://leetcode-cn.com/problems/merge-k-sorted-lists/ ]
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * 示例 1：
 * <p>
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：lists = []
 * 输出：[]
 * <p>
 * 示例 3：
 * <p>
 * 输入：lists = [[]]
 * 输出：[]
 */
public class Top023_hard {

    public static void main(String[] args) {
        Top023_hard top23 = new Top023_hard();
        ListNode[] listNodes = {
                new ListNode(1, new ListNode(4, new ListNode(5))),
                new ListNode(1, new ListNode(3, new ListNode(4))),
                new ListNode(2, new ListNode(6))
        };
//        ListNode[] listNodes = {
//                null,
//                new ListNode(1)
//        };
        ListNode listNode = top23.mergeKLists3(listNodes);
        System.out.println(listNode);
    }

    /**
     * 顺序合并
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode ans = null;
        int len = lists.length;
        for (int i = 0; i < len; i++) {
            ans = mergeTwoLists(ans, lists[i]);
        }
        return ans;
    }


    /**
     * 分支合并
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }


    public ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        if (l > r) {
            return null;
        }
        // 右移,相当于除2
        int mid = (l + r) >> 1;
        return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }


    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
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
        return dummyHead.next;
    }

    PriorityQueue<Status> queue = new PriorityQueue<Status>();
    public ListNode mergeKLists3(ListNode[] lists) {
        for (ListNode node: lists) {
            if (node != null) {
                queue.offer(new Status(node.val, node));
            }
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (!queue.isEmpty()) {
            Status f = queue.poll();
            tail.next = f.ptr;
            tail = tail.next;
            if (f.ptr.next != null) {
                queue.offer(new Status(f.ptr.next.val, f.ptr.next));
            }
        }
        return head.next;
    }

}

class Status implements Comparable<Status> {
    int val;
    ListNode ptr;

    Status(int val, ListNode ptr) {
        this.val = val;
        this.ptr = ptr;
    }

    public int compareTo(Status status2) {
        return this.val - status2.val;
    }
}