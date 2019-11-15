package leetcode_solutions;

import common.ListNode;

public class SwapNodesinPairs24 {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    class Solution {
        public ListNode swapPairs(ListNode head) {
            ListNode re = null;
            //交换前两个
            ListNode first = head;
            ListNode previous = null;
            ListNode second = null;
            ListNode nextFirst = null;
            if (first != null && first.next != null) {
                second = first.next;
                nextFirst = second.next;
                second.next = first;
                first.next = nextFirst;
                previous = first;
                first = nextFirst;
                re = second;
            }else {
                return  first;
            }

            while (first != null && first.next != null) {
                second = first.next;
                nextFirst = second.next;
                if (previous != null) {
                    previous.next = second;
                }
                second.next = first;
                first.next = nextFirst;
                previous = first;
                first = nextFirst;
            }
            return re;
        }
    }
}
