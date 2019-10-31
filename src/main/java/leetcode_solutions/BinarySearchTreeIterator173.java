package leetcode_solutions;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTreeIterator173 {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    class BSTIterator {

        int len = 0;
        int index = 0;
        List<Integer> list = new ArrayList<>();

        public BSTIterator(TreeNode root) {
            putIntoList(root);
        }

        private void putIntoList(TreeNode root) {
            if (root == null) return;
            putIntoList(root.left);
            list.add(root.val);
            len++;
            putIntoList(root.right);
        }

        /**
         * @return the next smallest number
         */
        public int next() {
            int re = list.get(index);
            index++;
            return re;
        }

        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {
            if (index < len) return true;
            else return false;
        }
    }

    class BSTIteratorOnLeetcode1 {

        private int cur;
        List<Integer> list = new ArrayList<>();

        public BSTIteratorOnLeetcode1(TreeNode root) {
            helper(root);
            cur = 1;
        }

        public void helper(TreeNode root) {
            if (root == null)
                return;
            helper(root.left);
            list.add(root.val);
            helper(root.right);
        }

        /**
         * @return the next smallest number
         */
        public int next() {
            cur++;
            return list.get(cur - 2);
        }

        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {
            return cur <= list.size();
        }
    }

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
}
