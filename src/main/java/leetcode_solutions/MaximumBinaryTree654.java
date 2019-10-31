package leetcode_solutions;

import common.TreeNode;
import org.junit.Test;

public class MaximumBinaryTree654 {
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
        public TreeNode constructMaximumBinaryTree(int[] nums) {
            int len = nums.length;
            TreeNode treeNode = con(nums, 0, nums.length-1);
            return treeNode;
        }

        //如何把l到r的最大值存下来？二维数组？
        private TreeNode con(int[] n, int l, int r) {
            if (n == null || l > r) return null;
            int idx = l;
            int val = n[l];
            for (int i = l; i <= r; i++) {
                if (n[i] > val) {
                    idx = i;
                    val = n[i];
                }
            }
            TreeNode t = new TreeNode();
            t.val = val;
            t.left = con(n, l, idx - 1);
            t.right = con(n, idx + 1, r);
            return t;
        }
    }
    
    @Test
    public void test(){
        int[] nums=new int[]{3,2,1,6,0,5};
        TreeNode root=new Solution().constructMaximumBinaryTree(nums );
        System.out.println(root);
    }

}
