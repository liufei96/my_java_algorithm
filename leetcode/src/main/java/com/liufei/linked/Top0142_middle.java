package com.liufei.linked;
import com.liufei.model.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 142. 环形链表 II  [ https://leetcode-cn.com/problems/linked-list-cycle-ii/ ]
 *
 * 给定一个链表，返回链表开始入环的第一个节点。如果链表无环，则返回 null。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
 *
 * 说明：不允许修改给定的链表。
 *
 * 进阶：
 *
 * 你是否可以使用 O(1) 空间解决此题？
 *
 */
public class Top0142_middle {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(3, new ListNode(2, new ListNode(0, new ListNode(-4))));
        ListNode listNode1 = detectCycle(listNode);
        System.out.println(listNode1);
    }

    /**
     * 哈希表
     * 一个非常直观的思路是：我们遍历链表中的每个节点，并将它记录下来；一旦遇到了此前遍历过的节点，就可以判定链表中存在环。借助哈希表可以很方便地实现。
     *
     * 复杂度分析
     *    时间复杂度：O(N)，其中 N 为链表中节点的数目。我们恰好需要访问链表中的每一个节点。
     *    空间复杂度：O(N)，其中 N 为链表中节点的数目。我们需要将链表中的每个节点都保存在哈希表当中。
     *
     * @param head
     * @return
     */
    public static ListNode detectCycle(ListNode head) {
        Set<ListNode> visited = new HashSet<>();
        ListNode pos = head;
        while (pos != null) {
            if (visited.contains(pos)) {
                return pos;
            } else {
                visited.add(pos);
            }
            pos = pos.next;
        }
        return null;
    }

    /**
     * 快慢指针
     *
     * 复杂度分析
     * 时间复杂度：O(N)，其中 NN 为链表中节点的数目。在最初判断快慢指针是否相遇时，slow 指针走过的距离不会超过链表的总长度；随后寻找入环点时，
     * 走过的距离也不会超过链表的总长度。因此，总的执行时间为 O(N) + O(N) = O(N)。
     * 空间复杂度：O(1)。我们只使用了 slow,fast,ptr 三个指针。
     *
     * @param head
     * @return
     */
    public static ListNode detectCycle2(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast  = fast.next.next;
            // 有环
            if (slow == fast) {
                ListNode index1 = fast;
                ListNode index2 = head;
                // 两个指针，从头结点和相遇结点，各走一步，直到相遇，相遇点即为环入口
                while (index1 != index2) {
                    index1 = index1.next;
                    index2 = index2.next;
                }
                return index1;
            }
        }
        return null;
    }
}
