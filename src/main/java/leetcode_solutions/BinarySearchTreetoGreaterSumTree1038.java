package leetcode_solutions;

import common.TreeNode;
import org.junit.Test;

public class BinarySearchTreetoGreaterSumTree1038 {
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
        public TreeNode bstToGst(TreeNode root) {
            add(root, 0);
            return root;
        }

        private void add(TreeNode t, int pValue) {
            if (null == t) return;
            //计算右子数
            add(t.right, pValue);
            //计算当前节点
            int v = t.val;
            if (t.right == null) {
                v += pValue;
                t.val = v;
            } else {
                TreeNode tmp = t.right;
                while (tmp.left != null) {
                    tmp = tmp.left;
                }
                v += tmp.val;
                t.val = v;
            }
            //计算左子树
            add(t.left, t.val);
        }
    }

    class Solution1 {
        public TreeNode bstToGst(TreeNode root) {
            def(root);
            return root;
        }

        int sum = 0;

        public void def(TreeNode root) {
            if (root == null) return;
            def(root.right);
            root.val += sum;
            sum = root.val;
            def(root.left);
        }
    }


    @Test
    public void test() {
        TreeNode root = new TreeNode(4);
        TreeNode t1 = new TreeNode(1);
        TreeNode t0 = new TreeNode(0);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t6 = new TreeNode(6);
        TreeNode t5 = new TreeNode(5);
        TreeNode t7 = new TreeNode(7);
        TreeNode t8 = new TreeNode(8);

        root.left = t1;
        root.right = t6;
        t1.left = t0;
        t1.right = t2;
        t2.right = t3;
        t6.left = t5;
        t6.right = t7;
        t7.right = t8;
        System.out.println(root.levelTraverse());

        root = new Solution().bstToGst(root);
        System.out.println(root.levelTraverse());
    }
}
