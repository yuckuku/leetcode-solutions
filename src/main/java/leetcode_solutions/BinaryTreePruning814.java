package leetcode_solutions;

import common.TreeNode;

public class BinaryTreePruning814 {
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
        public TreeNode pruneTree(TreeNode root) {
            prune(root);
            return root;
        }

        private void prune(TreeNode t) {
            if (isAllZero(t.left)) {
                t.left = null;
            } else {
                prune(t.left);
            }
            if (isAllZero(t.right)) {
                t.right = null;
            } else {
                prune(t.right);
            }
        }

        private boolean isAllZero(TreeNode t) {
            if (t == null) return true;
            if (t.val == 1) return false;
            return isAllZero(t.left) && isAllZero(t.right);
        }
    }
}
