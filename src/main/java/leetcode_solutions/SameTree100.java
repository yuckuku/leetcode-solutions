package leetcode_solutions;

import common.TreeNode;

/**
 * Created by Administrator on 2017/11/16.
 */
public class SameTree100 {
    //code can be transformed
    public boolean isSameTree(TreeNode p, TreeNode q) {
        System.out.println("vals: " + p.val + " " + q.val);
        if (p == null && q == null) {
            return true;
        } else if (p == null && q != null) {
            return false;
        } else if (p != null && q == null)
            return false;
        if (nodeEqual(q, p) && nodeEqual(p.left, q.left) && nodeEqual(p.right, q.right))
            return true && isSameTree(p.left, q.left) && isSameTree(q.right, p.right);
        else
            return false;
    }

    public boolean nodeEqual(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            System.out.println("this");
            return true;
        }
        if (t1 != null && t2 != null && t1.val == t2.val)
            return true;
        return false;
    }

    public boolean isSameTree1(TreeNode p, TreeNode q) {
        if (p == null & q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        } else {
            return false;
        }
    }

    public boolean isSameTree2(TreeNode p, TreeNode q) {
        return (p != null && q != null && p.val == q.val
                && isSameTree2(p.left, q.left) && isSameTree2(p.right, q.right))
                || (p == null && q == null);
    }
}
