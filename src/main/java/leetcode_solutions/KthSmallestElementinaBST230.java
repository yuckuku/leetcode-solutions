package leetcode_solutions;

import common.TreeNode;
import org.junit.Test;

public class KthSmallestElementinaBST230 {
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
        //wrong answer
        public int kthSmallest(TreeNode root, int k) {
            int rank = rank(root, 0, k);
            return rank;
        }

        private int rank(TreeNode t, int n, int k) {
            if (t != null)
                System.out.println(t.val);
            else System.out.println(t);
            if (t == null) return n;
            int leftCount = rank(t.left, n, k);
            if (leftCount + n == k - 1) return t.val;
            else if (leftCount + n > k - 1) {
                return rank(t.left, n, k);
            } else if (t.right != null) {
                return rank(t.right, n + leftCount + 1, k);
            } else {
                return leftCount + n + 1;
            }
        }

        int count = 0;
        int re = Integer.MAX_VALUE;

        public int kthSmalles1(TreeNode root, int k) {
            inOrderTraverse(root, k);
            return re;
        }

        private void inOrderTraverse(TreeNode t, int k) {
            if (t.left != null) {
                inOrderTraverse(t.left, k);
            }
            if (re != Integer.MAX_VALUE) return;
            count++;
            if (count == k) re = t.val;
            if (t.right != null) inOrderTraverse(t.right, k);
        }
    }


    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);

        int k = 2;

        Solution solution = new Solution();
        int re = solution.kthSmallest(root, k);
        System.out.println(re);
    }


}
