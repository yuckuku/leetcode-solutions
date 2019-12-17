package leetcode_solutions;

import common.TreeNode;

import java.util.*;

public class BinaryTreeRightSideView199 {
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
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> re = new ArrayList<>();
            if (null == root) return re;

            Deque<TreeNode> queue = new ArrayDeque<>();
            TreeNode pointer = new TreeNode(Integer.MAX_VALUE);
            queue.offer(root);
            queue.offer(pointer);
            while (!queue.isEmpty()) {
                TreeNode tmp = queue.poll();
                if (pointer != tmp) {
                    TreeNode node = queue.peek();
                    if (pointer == node) {
                        re.add(tmp.val);
                    }
                    if (null != tmp.left)
                        queue.offer(tmp.left);
                    if (null != tmp.right)
                        queue.offer(tmp.right);
                } else {
                    if (queue.isEmpty()) continue;
                    else queue.offer(pointer);
                }
            }
            return re;
        }
    }

    class Solution1ms {
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            if (root == null) {
                return result;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            TreeNode last = root;
            TreeNode nLast = null;
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                    nLast = node.left;
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    nLast = node.right;
                }
                if (node == last) {
                    last = nLast;
                    result.add(node.val);
                }
            }
            return result;
        }
    }
}
