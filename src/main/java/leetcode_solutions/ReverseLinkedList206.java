package leetcode_solutions;

import common.ListNode;

/**
 * Created by Administrator on 2017/11/24.
 */
public class ReverseLinkedList206 {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode t1 = head;
        ListNode t2 = head.next;
        ListNode p = head.next;
        p = p.next;
        t2.next = head;
        head.next = null;
        t1 = t2;
        if (p != null)
            t2 = p;
        while (p != null) {
            p = p.next;
            t2.next = t1;
            t1 = t2;
            if (p != null)
                t2 = p;
        }
        return t2;
    }
    //recursive
    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }
}
