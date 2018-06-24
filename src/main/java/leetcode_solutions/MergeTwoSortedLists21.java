package leetcode_solutions;

import common.ListNode;
import org.junit.Test;

public class MergeTwoSortedLists21 {


    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (null == l1) {
            return l2;
        } else if (null == l2) {
            return l1;
        }
        ListNode reRoot = null;
        ListNode preNode = null;
        ListNode presentNode = null;
        if (l1.val <= l2.val) {
            presentNode = l1;
            l1 = l1.next;
            presentNode.next = null;
        } else {
            presentNode = l2;
            l2 = l2.next;
            presentNode.next = null;
        }
        reRoot = presentNode;
        preNode = presentNode;
        while (null != l1 && null != l2) {
            if (l1.val <= l2.val) {
                presentNode = l1;
                l1 = l1.next;
                presentNode.next = null;
            } else {
                presentNode = l2;
                l2 = l2.next;
                presentNode.next = null;
            }
            preNode.next = presentNode;
            preNode = presentNode;
        }

        if (null == l1) {
            preNode.next = l2;
        } else if (null == l2) {
            preNode.next = l1;
        }

        return reRoot;
    }

    //better solution
    public ListNode mergeTwoLists0(ListNode l1, ListNode l2) {
        ListNode node = new ListNode(0);
        ListNode head = node;

        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                node.next = l2;
                l2 = l2.next;
            } else {
                node.next = l1;
                l1 = l1.next;
            }
            node = node.next;
        }

        if (l1 == null)
            node.next = l2;
        if (l2 == null)
            node.next = l1;

        return head.next;
    }

    @Test
    public void test() {
        ListNode l10 = new ListNode(1);
        ListNode l11 = new ListNode(2);
        ListNode l12 = new ListNode(4);
        l10.next = l11;
        l11.next = l12;

        ListNode l20 = new ListNode(1);
        ListNode l21 = new ListNode(3);
        ListNode l22 = new ListNode(4);
        l20.next = l21;
        l21.next = l22;

        mergeTwoLists(l10, l20);
    }
}
