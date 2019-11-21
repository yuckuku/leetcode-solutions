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
            if (root.left != null && root.left.val >= root.val || root.right != null && root.right.val <= root.val)
                return false;
            return isValidBST(root.left) && isValidBST(root.right);
        }
    }

//    [10,5,15,null,null,6,20]
}
