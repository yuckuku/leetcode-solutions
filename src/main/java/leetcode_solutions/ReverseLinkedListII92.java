package leetcode_solutions;

import common.ListNode;
import org.junit.Test;

public class ReverseLinkedListII92 {

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    class Solution {
        public ListNode reverseBetween(ListNode head, int m, int n) {
            int index = 1;
            ListNode cur = head;
            ListNode left = null;
            while (index < m) {
                left = cur;
                cur = cur.next;
                index++;
            }
            //开始转置
            ListNode last = cur;
            ListNode pre = cur;
            cur = cur.next;
            index++;
            if (left != null) left.next = null;
            while (index <= n) {
                ListNode tmp = cur;
                cur = cur.next;
                tmp.next = pre;
                pre = tmp;
                index++;
            }
            last.next = cur;
            if (left != null) {
                //m>1
                left.next = pre;
                return head;
            } else {
                return pre;
            }
        }
    }

    class SolutionRecursive {

        // Object level variables since we need the changes
        // to persist across recursive calls and Java is pass by value.
        private boolean stop;
        private ListNode left;

        public void recurseAndReverse(ListNode right, int m, int n) {

            // base case. Don't proceed any further
            if (n == 1) {
                return;
            }

            // Keep moving the right pointer one step forward until (n == 1)
            right = right.next;

            // Keep moving left pointer to the right until we reach the proper node
            // from where the reversal is to start.
            if (m > 1) {
                this.left = this.left.next;
            }

            // Recurse with m and n reduced.
            this.recurseAndReverse(right, m - 1, n - 1);

            // In case both the pointers cross each other or become equal, we
            // stop i.e. don't swap data any further. We are done reversing at this
            // point.
            if (this.left == right || right.next == this.left) {
                this.stop = true;
            }

            // Until the boolean stop is false, swap data between the two pointers
            if (!this.stop) {
                int t = this.left.val;
                this.left.val = right.val;
                right.val = t;

                // Move left one step to the right.
                // The right pointer moves one step back via backtracking.
                this.left = this.left.next;
            }
        }

        public ListNode reverseBetween(ListNode head, int m, int n) {
            this.left = head;
            this.stop = false;
            this.recurseAndReverse(head, m, n);
            return head;
        }
    }


    @Test
    public void test() {
        ListNode n5 = new ListNode(5);
        ListNode n4 = new ListNode(4, n5);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode head = new ListNode(1, n2);

        Solution solution = new Solution();
        ListNode re = solution.reverseBetween(head, 2, 4);
        System.out.println(re);
    }
}
