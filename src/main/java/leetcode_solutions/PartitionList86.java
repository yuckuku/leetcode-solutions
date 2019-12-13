package leetcode_solutions;

import common.ListNode;
import org.junit.Test;

public class PartitionList86 {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    //执行用时:0ms,在所有java提交中击败了100.00%的用户内存消耗:35.7MB,在所有java提交中击败了57.14%的用户
    class Solution {
        public ListNode partition(ListNode head, int x) {
            if (null == head) return null;
            ListNode lh = null;
            ListNode rh = null;
            ListNode lt = null;
            ListNode rt = null;
            while (head != null) {
                if (head.val < x) {
                    if (lh == null) {
                        //初始化头尾
                        lh = head;
                        lt = head;
                    } else {
                        //加入尾部
                        lt.next = head;
                        lt = head;
                    }
                } else {
                    if (rh == null) {
                        rh = head;
                        rt = head;
                    } else {
                        rt.next = head;
                        rt = head;
                    }
                }
                head = head.next;
            }

            if (null == lt) {
                rt.next = null;
                return rh;
            } else if (null == rt) {
                lt.next = null;
                return lh;
            } else {
                rt.next = null;
                lt.next = rh;
                return lh;
            }
        }
    }

    @Test
    public void test() {
        ListNode head = new ListNode(new int[]{1, 4, 3, 2, 5, 2});
        System.out.println(head);
        Solution solution = new Solution();
        ListNode re = solution.partition(head, 3);
        System.out.println(re);
    }

    class Solution1 {
        public ListNode partition(ListNode head, int x) {

            // before and after are the two pointers used to create the two list
            // before_head and after_head are used to save the heads of the two lists.
            // All of these are initialized with the dummy nodes created.
            ListNode before_head = new ListNode(0);
            ListNode before = before_head;
            ListNode after_head = new ListNode(0);
            ListNode after = after_head;

            while (head != null) {

                // If the original list node is lesser than the given x,
                // assign it to the before list.
                if (head.val < x) {
                    before.next = head;
                    before = before.next;
                } else {
                    // If the original list node is greater or equal to the given x,
                    // assign it to the after list.
                    after.next = head;
                    after = after.next;
                }

                // move ahead in the original list
                head = head.next;
            }

            // Last node of "after" list would also be ending node of the reformed list
            after.next = null;

            // Once all the nodes are correctly assigned to the two lists,
            // combine them to form a single list which would be returned.
            before.next = after_head.next;

            return before_head.next;
        }
    }

}
