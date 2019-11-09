package leetcode_solutions;

import common.ListNode;
import org.junit.Test;

public class AddTwoNumbers2 {


    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            int carry = 0;
            int value = l1.val + l2.val;
            if (value > 9) {
                carry = 1;
                value %= 10;
            }

            ListNode re = new ListNode(value);
            l1 = l1.next;
            l2 = l2.next;
            ListNode tmp = re;
            while (l1 != null && l2 != null) {
                value = l1.val + l2.val + carry;
                if (value > 9) {
                    carry = 1;
                    value %= 10;
                } else {
                    carry = 0;
                }
                ListNode cur = new ListNode(value);
                tmp.next = cur;
                tmp = cur;
                l1 = l1.next;
                l2 = l2.next;
            }

            while (l1 != null) {

                value = l1.val + carry;
                if (value > 9) {
                    carry = 1;
                    value %= 10;
                } else {
                    carry = 0;
                }
                ListNode cur = new ListNode(value);
                tmp.next = cur;
                tmp = cur;
                l1 = l1.next;
            }
            while (l2 != null) {
                value = l2.val + carry;
                if (value > 9) {
                    carry = 1;
                    value %= 10;
                } else {
                    carry = 0;
                }
                ListNode cur = new ListNode(value);
                tmp.next = cur;
                tmp = cur;
                l2 = l2.next;
            }
            if (carry == 1) {
                ListNode cur = new ListNode(1);
                tmp.next = cur;
            }

            return re;
        }
    }

    class SolutionOnLeetcode1 {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            int c = 0;
            ListNode pre = null;
            ListNode res = l1;
            while (l1 != null || l2 != null) {
                if (l1 == null) {
                    pre.next = l2;
                    l1 = l2;
                    l2 = null;
                }
                l1.val += (l2 == null) ? c : (l2.val + c);
                c = 0;
                if (l1.val > 9) {
                    l1.val %= 10;
                    c = 1;
                }
                pre = l1;
                l1 = l1.next;
                if (l2 != null) l2 = l2.next;
            }
            if (c != 0) {
                ListNode hi = new ListNode(1);
                pre.next = hi;
            }
            return res;
        }
    }

    @Test
    public void test() {
        ListNode l1 = new ListNode(9);
        l1.next = new ListNode(1);
        l1.next.next = new ListNode(6);

        ListNode l2 = new ListNode(0);

        Solution solution = new Solution();
        ListNode re = solution.addTwoNumbers(l1, l2);

        while (re != null) {
            System.out.println(re.val);
            re = re.next;
        }
    }
}
