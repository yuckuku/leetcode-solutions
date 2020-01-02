package leetcode_solutions;

import common.ListNode;
import org.junit.Test;

import java.util.LinkedList;

public class ReorderList143 {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    //执行用时:3ms,在所有java提交中击败了39.60%的用户内存消耗:40.2MB,在所有java提交中击败了89.78%的用户
    class Solution {
        public void reorderList(ListNode head) {
            if (head == null) return;
            LinkedList<ListNode> ll = new LinkedList<ListNode>();
            ListNode tmp = head;
            while (tmp != null) {
                ll.add(tmp);
                tmp = tmp.next;
            }

            tmp = head;
            ll.removeFirst();
            boolean flag = false;//take from head direction?
            int n = ll.size();
            for (int i = 0; i < n; i++, flag = !flag) {
                ListNode toInsert;
                if (flag) {
                    toInsert = ll.pollFirst();
                } else {
                    toInsert = ll.pollLast();
                }
                tmp.next = toInsert;
                tmp = toInsert;
            }
            tmp.next = null;

        }
    }

    @Test
    public void test() {
        ListNode head = new ListNode(new int[]{1, 2, 3, 4});
        Solution solution = new Solution();
        solution.reorderList(head);
        System.out.println(head);
    }

    class Solution1ms {
        public void reorderList(ListNode head) {
            if (head == null) {
                return;
            }
            ListNode low = head;
            ListNode fast = head;
            while (fast.next != null && fast.next.next != null) {//fast and slow, find middle node
                low = low.next;
                fast = fast.next.next;
            }
            ListNode pre = null;
            ListNode middle = low.next;
            if (middle == null) {
                return;
            }
            low.next = null;
            ListNode next = middle.next;
            while (next != null) {// reverse the back half list node
                middle.next = pre;
                pre = middle;
                middle = next;
                next = next.next;
            }
            middle.next = pre;
            ListNode flag = head;
            while (flag != null && middle != null) {//rebuild list node
                ListNode flag_next = flag.next;
                ListNode middle_next = middle.next;
                flag.next = middle;
                middle.next = flag_next;
                flag = flag_next;
                middle = middle_next;
            }
        }
    }


}
