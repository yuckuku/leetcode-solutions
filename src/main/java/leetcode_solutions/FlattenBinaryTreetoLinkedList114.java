package leetcode_solutions;

import common.TreeNode;

public class FlattenBinaryTreetoLinkedList114 {
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
        public void flatten(TreeNode root) {
            if (null == root) return;

            if (null != root.right) {
                flatten(root.right);
            }

            if (null != root.left) {
                flatten(root.left);
                //左边最右子节点
                TreeNode tmp = root.left;
                while (null != tmp.right) {
                    tmp = tmp.right;
                }
                tmp.right = root.right;
                root.right = root.left;
                root.left = null;

            }
        }
    }

}
