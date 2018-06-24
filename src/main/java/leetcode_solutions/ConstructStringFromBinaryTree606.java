package leetcode_solutions;

import common.TreeNode;

/**
 * Created by Administrator on 2017/10/19.
 */
public class ConstructStringFromBinaryTree606 {
    //use recursive algorithm
    //question:how to differ not exist and null from input
    public String tree2str(TreeNode t) {
        if (null == t) {
            return "";
        }
        if (null == t.left && null == t.right) {
            return "" + t.val;
        } else if (null == t.left && null != t.right) {
            return "" + t.val + "(" + tree2str(t.left) + ")" + "(" + tree2str(t.right) + ")";
        } else if (null == t.right && null != t.left) {
            return "" + t.val + "(" + tree2str(t.left) + ")";
        }
        return "" + t.val + "(" + tree2str(t.left) + ")" + "(" + tree2str(t.right) + ")";
    }
}
