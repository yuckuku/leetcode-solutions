package leetcode_solutions;

import common.ListNode;

public class RemoveNthNodeFromEndofList19 {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            int i = 0, j = n;
            ListNode jNode = head;
            ListNode iNode = head;
            for (int k = 0; k < j; k++) {
                jNode = jNode.next;
                if (jNode == null) break;
            }
            while (jNode != null && jNode.next != null) {
                iNode = iNode.next;
                jNode = jNode.next;
            }
            if (jNode == null) return iNode.next;
            ListNode tmp = iNode.next;
            iNode.next = tmp.next;
            tmp.next = null;
            return head;
        }
    }

    class SolutionOnLeetcode1 {
        private int cnt;

        public ListNode removeNthFromEnd(ListNode head, int n) {
            if (head == null) {
                cnt = 0;
                return head;
            }
            head.next = removeNthFromEnd(head.next, n);
            cnt++;
            if (cnt == n) {
                return head.next;
            }
            return head;
        }
    }
}
