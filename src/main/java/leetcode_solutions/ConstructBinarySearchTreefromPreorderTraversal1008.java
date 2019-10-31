package leetcode_solutions;

import common.TreeNode;
import org.junit.Test;

public class ConstructBinarySearchTreefromPreorderTraversal1008 {
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
        public TreeNode bstFromPreorder(int[] preorder) {
            TreeNode root = toBt(preorder, 0, preorder.length-1);
            return root;
        }

        private TreeNode toBt(int[] preorder, int start, int end) {
            TreeNode t = new TreeNode(preorder[start]);
            if (start == end) return t;
            int firstBig = start + 1;
            //找到第一个大于start的位置
            while (firstBig <= end) {
                if (preorder[firstBig] > preorder[start]) break;
                firstBig++;
            }
            if (firstBig > end) {
                t.left = toBt(preorder, start + 1, end);
            } else if (firstBig == start + 1) {
                t.right = toBt(preorder, firstBig, end);
            } else {
                t.left = toBt(preorder, start + 1, firstBig - 1);
                t.right = toBt(preorder, firstBig, end);
            }
            return t;
        }
    }

    @Test
    public void test() {
        int[] preorder = new int[]{8, 5, 1, 7, 10, 12};
        Solution solution = new Solution();
        TreeNode root = solution.bstFromPreorder(preorder);
        System.out.println(root.levelTraverse());
    }
}
