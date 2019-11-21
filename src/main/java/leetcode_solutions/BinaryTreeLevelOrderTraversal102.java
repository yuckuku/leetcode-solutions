package leetcode_solutions;

import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal102 {

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
        public List<List<Integer>> levelOrder(TreeNode root) {

            List<List<Integer>> re = new ArrayList<>();
            f(root, 0, re);
            return re;
        }

        private void f(TreeNode tmp, int level, List<List<Integer>> re) {

            //如果当前节点空，退出
            if (tmp == null) return;

            //按需增加新的list
            if (level > re.size() - 1) {
                re.add(new ArrayList<>());
            }
            //加入list
            re.get(level).add(tmp.val);

            //处理左节点
            f(tmp.left, level + 1, re);
            //处理又节点
            f(tmp.right, level + 1, re);
        }
    }

    class SolutionIteration {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> levels = new ArrayList<List<Integer>>();
            if (root == null) return levels;

            Queue<TreeNode> queue = new LinkedList<TreeNode>();
            queue.add(root);
            int level = 0;
            while (!queue.isEmpty()) {
                // start the current level
                levels.add(new ArrayList<Integer>());

                // number of elements in the current level
                int level_length = queue.size();
                for (int i = 0; i < level_length; ++i) {
                    TreeNode node = queue.remove();

                    // fulfill the current level
                    levels.get(level).add(node.val);

                    // add child nodes of the current level
                    // in the queue for the next level
                    if (node.left != null) queue.add(node.left);
                    if (node.right != null) queue.add(node.right);
                }
                // go to next level
                level++;
            }
            return levels;
        }
    }

}
