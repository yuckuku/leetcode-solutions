package leetcode_solutions;

import common.TreeNode;
import org.junit.Test;

import java.util.LinkedList;

public class LowestCommonAncestorofaBinaryTree236 {
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
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

            if (root == null) return null;

            LinkedList<TreeNode> list1 = new LinkedList<>();
            LinkedList<TreeNode> list2 = new LinkedList<>();
            dfs(root, list1, p);
            dfs(root, list2, q);

            TreeNode re = null;
            for (int i = 0; i < Math.min(list1.size(), list2.size()); i++) {

                if (list1.get(i) != list2.get(i)) {
                    break;
                } else {
                    re = list1.get(i);
                }
            }

            return re;
        }

        private void dfs(TreeNode root, LinkedList<TreeNode> list, TreeNode desNode) {
            list.add(root);
            if (root == desNode) return;
            if (root.left != null)
                dfs(root.left, list, desNode);
            if (root.right != null)
                dfs(root.right, list, desNode);
            list.removeLast();
        }

    }

    @Test
    public void test() {
//        [3,5,1,6,2,0,8,null,null,7,4]
//        5
//        4
        TreeNode node3 = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        TreeNode node1 = new TreeNode(1);
        TreeNode node6 = new TreeNode(6);
        TreeNode node2 = new TreeNode(2);
        TreeNode node0 = new TreeNode(0);
        TreeNode node8 = new TreeNode(8);
        TreeNode node7 = new TreeNode(7);
        TreeNode node4 = new TreeNode(4);

        node3.left = node5;
        node3.right = node1;
        node5.left = node6;
        node5.right = node2;
        node2.left = node7;
        node2.right = node4;
        node1.left = node0;
        node1.right = node8;

        Solution solution = new Solution();
        TreeNode re = solution.lowestCommonAncestor(node3, node5, node4);

        System.out.println(re.val);


    }

}
