package leetcode_solutions;

import common.ListNode;

public class RemoveDuplicatesfromSortedListII82 {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    class Solution {
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null || head.next == null) return head;
            //当前节点不需要删除
            if (head.val != head.next.val) {
                head.next = deleteDuplicates(head.next);
                return head;
            } else {
                ListNode tmp = head;
                while (tmp.next != null && tmp.val == tmp.next.val)
                    tmp = tmp.next;

                return deleteDuplicates(tmp.next);
            }
        }
    }
}
