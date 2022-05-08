package 链表;

public class SingleLinkedList {

    /**
     * 题目：
     * 单链表反转
     * 如:  Node[A] -> Node[B] -> Node[C]
     * 传入参数：Node[C] ->  返回一个新的链表是： Node[C] -> Node[B] -> Node[A]
     * 传入参数：Node[B] -> 返回一个新的链表是：Node[B] -> Node[A]
     */

    public static void main(String[] args) {
        ListNode nodeA = new ListNode("A");
        ListNode nodeB = new ListNode("B");
        nodeA.next = nodeB;
        ListNode nodeC = new ListNode("C");
        nodeB.next = nodeC;
        // 测试方法1
        // ListNode reverseListNode = reverseNodeLinked(nodeB);
        // System.out.println(reverseListNode);

        // 测试方法2
        ListNode reverseListNode2 = reverseNodeLinked2(nodeA);
        System.out.println(reverseListNode2);
    }

    /**
     * 头节点插入法。1个头节点，两个指针
     * 如单链表：Node[A] -> Node[B] -> Node[C];
     * 定义一个 Node[Tmp]
     * 开始反转：
     * 第一次插入：Node[Tmp] -> Node[A];
     * 第二次插入：Node[Tmp] -> Node[B] -> Node[A];
     * 第二次插入：Node[Tmp] -> Node[C] -> Node[B] -> Node[A];
     *
     * @param head  指定从哪里开始反转
     * @return
     */
    public static ListNode reverseNodeLinked(ListNode head) {
        // 里面的值随便写，这个tmp只是一个辅助工具
        ListNode tmp = new ListNode("-1");
        ListNode pCur = head;
        while (pCur != null) {
            ListNode pNext = pCur.next;
            pCur.next = tmp.next;
            tmp.next = pCur;
            pCur = pNext;
        }
        return tmp.next;
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
    public static ListNode reverseNodeLinked2(ListNode head) {
        // 里面的值随便写，这个tmp只是一个辅助工具
        ListNode tmp = new ListNode("-1");
        tmp.next = head;
        ListNode prev = tmp.next;
        ListNode pCur = prev.next;
        while (pCur != null) {
            prev.next = pCur.next;
            pCur.next = tmp.next;
            tmp.next = pCur;
            pCur = prev.next;
        }
        return tmp.next;
    }

}


/**
 * 定义一个单链表
 */
class ListNode {
    String value;
    ListNode next;

    public ListNode(String value) {
        this.value = value;
        this.next = null;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "value='" + value + '\'' +
                ", next=" + next +
                '}';
    }
}
