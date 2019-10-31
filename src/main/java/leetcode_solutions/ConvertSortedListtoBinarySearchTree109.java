package leetcode_solutions;

import common.ListNode;
import common.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ConvertSortedListtoBinarySearchTree109 {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        public TreeNode sortedListToBST(ListNode head) {
            List<Integer> list = convertToArr(head);
            int size = list.size();

            TreeNode root = toTree(list, 0, size - 1);
            return root;
        }

        private TreeNode toTree(List<Integer> list, int l, int r) {
            if (l > r) return null;
            if (l == r) return new TreeNode(list.get(l));
            int mid = (l + r) >> 1;
            TreeNode re = new TreeNode(list.get(mid));
            re.left = toTree(list, l, mid - 1);
            re.right = toTree(list, mid + 1, r);
            return re;
        }

        private List<Integer> convertToArr(ListNode head) {
            List<Integer> re = new ArrayList<>();
            while (head != null) {
                re.add(head.val);
                head = head.next;
            }
            return re;
        }
    }

    class Solution1 {
        public TreeNode sortedListToBST(ListNode head) {
            if (head == null) return null;
            else if (head.next == null)
                return new TreeNode(head.val);
            ListNode pre = head;
            ListNode slow = pre.next;
            ListNode fast = slow.next;
            while (fast != null && fast.next != null) {
                pre = pre.next;
                slow = slow.next;
                fast = fast.next.next;
            }
            pre.next = null;
            TreeNode root = new TreeNode(slow.val);
            root.left = sortedListToBST(head);
            root.right = sortedListToBST(slow.next);
            return root;
        }
    }
    
    @Test
    public void test(){
//        [-10,-3,0,5,9]
    }
}
