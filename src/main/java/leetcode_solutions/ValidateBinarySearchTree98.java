package leetcode_solutions;

import common.TreeNode;

public class ValidateBinarySearchTree98 {
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
        public boolean isValidBST(TreeNode root) {
            if (null == root) return true;

            //左子树最右叶子节点小于当前值即符合要求
            TreeNode l = root.left;
            while (l != null) {
                if (l.val >= root.val) return false;
                l = l.right;
            }

            //右子数最左叶子节点大于当前值即符合要求
            TreeNode r = root.right;
            while (r != null) {
                if (r.val <= root.val) return false;
                r = r.left;
            }

            return isValidBST(root.left) && isValidBST(root.right);
        }
    }

    class Solution0ms {
        int temp = Integer.MIN_VALUE;
        TreeNode pre = null;
        int flag = 0;

        public boolean isValidBST(TreeNode root) {

            help(root);
            return flag == 0;
        }

        private void help(TreeNode root) {
            if (root == null) return;
            help(root.left);
            if (pre != null) {
                if (root.val <= pre.val) {
                    flag = -1;
                }
            }
            pre = root;
            help(root.right);
        }
    }
}
