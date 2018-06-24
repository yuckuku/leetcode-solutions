package leetcode_solutions;

import common.TreeNode;

/**
 * Created by Administrator on 2017/11/14.
 */
public class MinimumAbsoluteDifferenceinBST530 {
    public int getMinimumDifference(TreeNode root) {
        int dif = Integer.MAX_VALUE;
        if (root == null) {
            return dif;
        }
        if (root.left != null) {
            TreeNode temp = root.left;
            while (temp.right != null) {
                temp = temp.right;
            }
            dif = Math.min(dif, Math.abs(root.val - temp.val));
        }
        if (root.right != null) {
            TreeNode temp = root.right;
            while (temp.left != null) {
                temp = temp.left;
            }
            dif = Math.min(dif, Math.abs(root.val - temp.val));
        }
        dif = Math.min(dif, getMinimumDifference(root.left));
        dif = Math.min(dif, getMinimumDifference(root.right));
        return dif;
    }
}
