package leetcode_solutions;

import common.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class LargestBSTSubtree333 {
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
        public int largestBSTSubtree(TreeNode root) {

            if (root == null) return 0;

            Map<TreeNode, Integer> counts = new HashMap<>();
            countNodes(root, counts);
            if (isBst(root)) {
                return counts.get(root);

            } else {
                return Math.max(largestBSTSubtree(root.left), largestBSTSubtree(root.right));
            }
        }

        //如果左子树和右子树都是bst，并且左子树最大值小于当前节点值，右子树最小值大于当前节点值，则当前树是bst
        private boolean isBst(TreeNode node) {
            if (node == null) return true;
            if (isBst(node.left) && isBst(node.right)) {
                TreeNode tmp = node.left;
                while (tmp != null && tmp.right != null) {
                    tmp = tmp.right;
                }
                if (tmp != null && tmp.val >= node.val) return false;

                tmp = node.right;
                while (tmp != null && tmp.left != null) {
                    tmp = tmp.left;
                }
                if (tmp != null && tmp.val <= node.val) return false;
                return true;
            }
            return false;
        }

        //
        private int countNodes(TreeNode node, Map<TreeNode, Integer> counts) {
            if (node == null) return 0;
            if (counts.containsKey(node)) return counts.get(node);
            int c = countNodes(node.left, counts) + countNodes(node.right, counts) + 1;
            counts.put(node, c);
            return c;
        }
    }

    class Solution0ms {
        public int firet = 1;

        public int largestBSTSubtree(TreeNode root) {
            if (root == null)
                return 0;

            findMax(root);  //左子树最小资 右子树最大值
            return firet;
        }


        public int findMax(TreeNode root) {
            if (root.left == null && root.right == null)
                return 1;
            int left = -1;
            int right = -1;
            int res = 0;
            if (root.left != null)
                left = findMax(root.left);
            if (root.right != null)
                right = findMax(root.right);
            if (left == 0 || right == 0)
                return 0;
            if (left != -1) {
                if (root.left.val < root.val && max(root.left) < root.val)
                    res += left;
                else
                    return 0;
            }
            if (right != -1) {
                if (root.right.val > root.val && min(root.right) > root.val)
                    res += right;
                else
                    return 0;
            }
            res++;
            if (res > firet)
                firet = res;
            return res;
        }

        public int max(TreeNode root) {
            while (root.right != null) root = root.right;
            return root.val;
        }

        public int min(TreeNode root) {
            while (root.left != null) root = root.left;
            return root.val;
        }
    }
}
