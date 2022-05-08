package top100;

import model.ListNode;

/**
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * <p>
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 * <p>
 * 示例 1：
 * 输入：head = [1,4,3,2,5,2], x = 3
 * 输出：[1,2,2,4,3,5]
 * <p>
 * 示例 2：
 * 输入：head = [2,1], x = 2
 * 输出：[1,2]
 * <p>
 * 提示：
 * <p>
 * 链表中节点的数目在范围 [0, 200] 内
 * -100 <= Node.val <= 100
 * -200 <= x <= 200
 */
public class Top086_middle {

    public static void main(String[] args) {
        Top086_middle top086_middle = new Top086_middle();
        ListNode listNode = new ListNode(1, new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(5, new ListNode(2))))));
        int x = 3;
        ListNode partition = top086_middle.partition(listNode, x);
        ListNode res = top086_middle.partition(partition, x);
        System.out.println(res);
    }

    public ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode(0);
        ListNode large = new ListNode(0);
        ListNode cur = head, tmpSmall = small, tmpLarge = large;
        while (cur != null) {
            if (cur.val < x) {
                tmpSmall.next = new ListNode(cur.val);
                tmpSmall = tmpSmall.next;
            } else {
                tmpLarge.next = new ListNode(cur.val);
                tmpLarge = tmpLarge.next;
            }
            cur = cur.next;
        }
        tmpSmall.next = large.next;
        return small.next;
    }
}
