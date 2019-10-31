package leetcode_solutions;

import common.TreeNode;

public class InsertintoaBinarySearchTree701 {
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
        public TreeNode insertIntoBST(TreeNode root, int val) {
            if (val > root.val) {
                //insert to right
                if (root.right == null) {
                    root.right = new TreeNode(val);
                } else {
                    insertIntoBST(root.right, val);
                }
            } else {
                //insert to left
                if (root.left == null) {
                    root.left = new TreeNode(val);
                } else {
                    insertIntoBST(root.left, val);
                }
            }
            return root;
        }
    }
}
