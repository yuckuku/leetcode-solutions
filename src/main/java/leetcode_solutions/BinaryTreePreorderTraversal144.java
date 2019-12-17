package leetcode_solutions;

import common.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreePreorderTraversal144 {
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
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> re = new ArrayList<>();
            preOrder(root, re);
            return re;
        }

        private void preOrder(TreeNode node, List<Integer> re) {
            if (null != node) {
                re.add(node.val);
                preOrder(node.left, re);
                preOrder(node.right, re);
            }
        }
    }

    //迭代
    class Solution1 {
        public List<Integer> preorderTraversal(TreeNode root) {
            Deque<TreeNode> stack = new LinkedList<>();
            TreeNode p = root;
            List<Integer> res = new ArrayList<>();
            while (p != null || !stack.isEmpty()) {
                while (p != null) {
                    res.add(p.val);
                    stack.push(p);
                    p = p.left;
                }
                p = stack.pop().right;
            }
            return res;
        }
    }

}
