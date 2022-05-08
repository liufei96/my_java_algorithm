package 链表;
import model.ListNode;

/**
 * 206. 反转链表  [ https://leetcode-cn.com/problems/reverse-linked-list/ ]
 *
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 *
 * 示例 2：
 * 输入：head = [1,2]
 * 输出：[2,1]
 *
 * 示例 3：
 * 输入：head = []
 * 输出：[]
 *
 * 提示：
 *
 * 链表中节点的数目范围是 [0, 5000]
 * -5000 <= Node.val <= 5000
 *
 * 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
 */
public class Top026_simple {

    public static void main(String[] args) {
        Top026_simple top026_simple = new Top026_simple();
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode listNode = top026_simple.reverseList(head);
        System.out.println(listNode);
        ListNode head2 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        System.out.println(top026_simple.reverseList2(head2));
    }

    /**
     * 插入法
     * 头节点插入法。1个头节点，两个指针
     * 如单链表：Node[A] -> Node[B] -> Node[C];
     * 定义一个 Node[Tmp]
     * 开始反转：
     * 第一次插入：Node[Tmp] -> Node[A];
     * 第二次插入：Node[Tmp] -> Node[B] -> Node[A];
     * 第二次插入：Node[Tmp] -> Node[C] -> Node[B] -> Node[A];
     *
     * 时间复杂度：O(n)，其中 nn 是链表的长度。需要遍历链表一次。
     * 空间复杂度：O(1)
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode cur = head;
        while (cur != null) {
            // 先将这个保留下来
            ListNode next = cur.next;
            cur.next = dummy.next;
            dummy.next = cur;
            cur = next;
        }
        return dummy.next;
    }

    /**
     * 就地反转。相当于交换位置
     * 定义一个 Node[Tmp]
     * 开始反转：
     * 第一次反转：Node[Tmp] -> Node[B] -> Node[A] -> Node[C];
     * 第二次反转：Node[Tmp] -> Node[C] -> Node[B] -> Node[A];
     *
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
       ListNode dummy = new ListNode(0, head);
       ListNode prev = dummy.next;
       ListNode cur = prev.next;
       while (cur != null) {
           prev.next = cur.next;
           // 注意：这里不能使用pre
           cur.next = dummy.next;
           dummy.next = cur;
           // 这里也不能使用cur.next
           cur = prev.next;
       }
       return dummy.next;
    }

    /**
     * 递归法
     *
     * 复杂度分析
     * 时间复杂度：O(n)，其中 n 是链表的长度。需要对链表的每个节点进行反转操作。
     * 空间复杂度：O(n)，其中 n 是链表的长度。空间复杂度主要取决于递归调用的栈空间，最多为 n 层。
     *
     * @param head
     * @return
     */
    public ListNode reverseList3(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList3(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
