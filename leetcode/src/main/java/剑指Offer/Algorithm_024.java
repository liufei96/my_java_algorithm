package 剑指Offer;


/**
 * 剑指 Offer 24. 反转链表。 [ https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/ ]
 *
 * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 */
public class Algorithm_024 {

    public static void main(String[] args) {
        Algorithm_024 algorithm009 = new Algorithm_024();
        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode res = algorithm009.reverseList(listNode);
        System.out.println(res);

        ListNode listNode1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        System.out.println(algorithm009.reverseList2(listNode1));

        ListNode listNode2 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        System.out.println(algorithm009.reverseList3(listNode2));
    }

    /**
     * 节点插入法
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode cur = head;
        while (cur != null) {
            ListNode pNext = cur.next;
            cur.next = dummy.next;
            dummy.next = cur;
            cur = pNext;
        }
        return dummy.next;
    }

    /**
     *  就地反转。相当于交换位置
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
       if (head == null) return null;
       ListNode dummy = new ListNode(0);
       dummy.next = head;
       ListNode prev = dummy.next;
       ListNode pCur = prev.next;
       while (pCur != null) {
           prev.next = pCur.next;
           pCur.next = dummy.next;
           dummy.next = pCur;
           pCur = prev.next;
       }
       return dummy.next;
    }

    public ListNode reverseList3(ListNode head) {
       ListNode next = null;
       ListNode prev = null;
       while (head != null) {
           // 保存要反转到头来的那个节点
           next = head.next;
           // 要反转的那个节点指向已经反转的上一个节点
           head.next = prev;
           // 上一个已经反转到头部的节点
           prev = head;
           //一直向链表尾走
           head = next;
       }
       return prev;
    }
}
