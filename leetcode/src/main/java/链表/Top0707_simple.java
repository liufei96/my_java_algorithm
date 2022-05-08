package 链表;

import model.ListNode;
import model.ListNode2;

/**
 * 707. 设计链表  [ https://leetcode-cn.com/problems/design-linked-list/ ]
 *
 * 设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。val 是当前节点的值，next 是指向下一个节点的指针/引用。如果要使用双向链表，
 * 则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。
 *
 * 在链表类中实现这些功能：
 *
 * get(index)：获取链表中第 index个节点的值。如果索引无效，则返回-1。
 * addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
 * addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
 * addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。
 * 如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
 * deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
 *
 * 示例：
 *
 * MyLinkedList linkedList = new MyLinkedList();
 * linkedList.addAtHead(1);
 * linkedList.addAtTail(3);
 * linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
 * linkedList.get(1);            //返回2
 * linkedList.deleteAtIndex(1);  //现在链表是1-> 3
 * linkedList.get(1);            //返回3
 *
 * 提示：
 *
 * 所有val值都在 [1, 1000] 之内。
 * 操作次数将在  [1, 1000] 之内。
 * 请不要使用内置的 LinkedList 库。
 */
public class Top0707_simple {

    public static void main(String[] args) {
        MyLinkedList2 linkedList = new MyLinkedList2();
        linkedList.addAtHead(1);
        linkedList.addAtTail(3);
        linkedList.addAtIndex(1, 2);
        //返回2
        int val = linkedList.get(1);
        System.out.println(val);
        linkedList.deleteAtIndex(1);
        //返回3
        val = linkedList.get(1);
        System.out.println(val);
    }



}

class MyLinkedList {

    int size;

    // 虚拟头结点
    ListNode head;

    /** Initialize your data structure here. */
    public MyLinkedList() {
        size = 0;
        //虚拟头结点
        head = new ListNode(0);
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        ListNode tmp = head;
        // 包含一个虚拟头节点，所以查找第 index+1 个节点
        for (int i = 0; i <= index; i++) {
           tmp = tmp.next;
        }
        return tmp.val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }
        size++;
        index = Math.max(index, 0);
        ListNode tmp = head;
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }
        ListNode next = tmp.next;
        ListNode add = new ListNode(val, next);
        tmp.next = add;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        size--;
        ListNode tmp = head;
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }
        tmp.next = tmp.next.next;
    }
}

class MyLinkedList2 {

    int size;

    // 伪头和伪尾
    ListNode2 head, tail;

    /** Initialize your data structure here. */
    public MyLinkedList2() {
        size = 0;
        //虚拟头结点
        head = new ListNode2(0);
        tail = new ListNode2(0);
        head.next = tail;
        tail.prev = head;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        // 通过判断 index < (size - 1) / 2 来决定是从头结点还是尾节点遍历，提高效率
        ListNode2 tmp = head;
        if (index < (size - 1) / 2) {
            for (int i = 0; i <= index; i++) {
                tmp = tmp.next;
            }
        } else {
            tmp = tail;
            for (int i = 0; i <= (size - 1 - index); i++) {
                tmp = tmp.prev;
            }
        }
        return tmp.val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        size++;
        ListNode2 cur = head;
        ListNode2 newNode = new ListNode2(val);
        newNode.next = cur.next;
        cur.next.prev = newNode;
        cur.next = newNode;
        newNode.prev =  cur;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        size++;
        ListNode2 cur = tail;
        ListNode2 newNode = new ListNode2(val);
        newNode.prev = cur.prev;
        cur.prev.next = newNode;
        newNode.next = cur;
        cur.prev= newNode;
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }
        size++;
        index = Math.max(index, 0);
        ListNode2 tmp = head;
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }
        ListNode2 next = tmp.next;
        ListNode2 add = new ListNode2(val);
        add.prev = tmp;
        tmp.next = add;
        add.next = next;
        next.prev = add;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        size--;
        ListNode2 tmp = head;
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }
        tmp.next = tmp.next.next;
        tmp.next.next.prev = tmp.next;
    }
}
