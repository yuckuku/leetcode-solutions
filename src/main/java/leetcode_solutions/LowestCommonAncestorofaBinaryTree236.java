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
    //执行用时:257ms,在所有java提交中击败了5.96%的用户内存消耗:40.1MB,在所有java提交中击败了5.04%的用户
    class Solution {

        boolean find = false;

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

            if (root == null) return null;

            LinkedList<TreeNode> list1 = new LinkedList<>();
            LinkedList<TreeNode> list2 = new LinkedList<>();
//            LinkedList<TreeNode> path = new LinkedList<>();
            dfs(root, list1, p);
            find = false;
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
            if (!find)
                list.add(root);
            else return;
            if (root == desNode) {
                find = true;
                return;
            }
            if (root.left != null)
                dfs(root.left, list, desNode);
            if (root.right != null)
                dfs(root.right, list, desNode);
            if (!find)
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


        LinkedList<TreeNode> list1 = new LinkedList<>();
        solution.dfs(node3, list1, node4);
        for (int i = 0; i < list1.size(); i++) {
            System.out.println(list1.get(i).val);
        }


    }

    class Solution6ms {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null || root == p || root == q) return root;

            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);

            if (left != null && right != null) return root;
            return left == null ? right : left;
        }
    }

    class Solution8ms {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            TreeNode ret = new TreeNode(0);
            recurseTree(root, p, q, ret);
            return ret;
        }

        private boolean recurseTree(TreeNode root, TreeNode p, TreeNode q, TreeNode ret) {
            if (root == null) return false;
            int left = recurseTree(root.left, p, q, ret) ? 1 : 0;
            int right = recurseTree(root.right, p, q, ret) ? 1 : 0;
            int mid = (root.val == p.val || root.val == q.val) ? 1 : 0;

            if (left + mid + right >= 2) ret.val = root.val;
            return (left + mid + right) > 0;
        }
    }



}
