package leetcode_solutions;

import common.TreeNode;

import java.util.TreeMap;

public class FindBottomLeftTreeValue513 {
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
        TreeMap<Integer, Integer> map = new TreeMap<>();

        public int findBottomLeftValue(TreeNode root) {
            find(root, 0);
            if (map.isEmpty()) return root.val;
            else
                return map.get(map.lastKey());
        }

        private void find(TreeNode t, int deep) {
            if (!map.containsKey(deep)) {
                map.put(deep, t.val);
            }
            if (t.left != null) {
                find(t.left, deep + 1);
            }
            if (t.right != null) {
                find(t.right, deep + 1);
            }
        }
    }

    class Solution1 {
        private int maxDepth = 0;
        private int leftValue;

        public int findBottomLeftValue(TreeNode root) {
            leftValue = root.val;
            dfs(root, 0);
            return leftValue;
        }

        private void dfs(TreeNode root, int Depth) {
            if (root == null)
                return;

            if (Depth > maxDepth) {
                leftValue = root.val;
                maxDepth = Depth;
            }
            dfs(root.left, Depth + 1);
            dfs(root.right, Depth + 1);
        }
    }
    /**
     * //方法1：迭代
     * public int findBottomLeftValue1(TreeNode root) {
     *     //层序遍历
     *     Queue<TreeNode> queue = new LinkedList<>();
     *     queue.add(root);
     *     int res = 0;
     *     while (!queue.isEmpty()) {
     *         int count = queue.size();
     *         //将每层左边第一个作为结果
     *         res = queue.peek().val;
     *         while (count-- > 0) {
     *             TreeNode cur = queue.poll();
     *             if (cur.left != null) {
     *                 queue.add(cur.left);
     *             }
     *             if (cur.right != null) {
     *                 queue.add(cur.right);
     *             }
     *         }
     *     }
     *     return res;
     * }
     *
     *
     * //方法2：递归
     * class Solution{
     *     int maxDepth = -1, res = -1;
     *
     *     public int findBottomLeftValue2(TreeNode root) {
     *         helper(root, 0);
     *         return res;
     *     }
     *
     *     private void helper(TreeNode root, int depth) {
     *         if (root == null) return;
     *         helper(root.left, depth + 1);
     *         //判断是否是最大深度
     *         if (depth > maxDepth) {
     *             maxDepth = depth;
     *             res = root.val;
     *         }
     *         helper(root.right, depth + 1);
     *     }
     * }
     *
     *
     */
}
