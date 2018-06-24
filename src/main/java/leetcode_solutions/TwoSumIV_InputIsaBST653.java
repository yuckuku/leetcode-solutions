package leetcode_solutions;

import common.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/10/18.
 */

public class TwoSumIV_InputIsaBST653 {

    //thinking:what if asked to return the two TreeNodes? then what the algrithm should be?
    //wrong answer; can not find all situations with violent traverse and this method below did not find all the situations.
   /* public boolean findTarget(TreeNode root, int k) {

        boolean flag = false;
        if (null == root || (null == root.left && null == root.right)) {
            return false;
        }

        TreeNode tn;
        if (null == root.left) {
            tn = root.right;
        } else {
            tn = root.left;
        }

        flag = find(root, tn,k);
        return flag;
    }

    public boolean find(TreeNode t1, TreeNode t2, int k) {
        boolean flag = false;
        if (null == t1 || null == t2) {
            return false;
        }
        if (t1.val + t2.val == k) {
            return true;
        }
        if (t1.val + t2.val > k) {
            if (t1.left == t2) {
                flag = find(t2.left, t1, k);
                if (flag) return true;
                flag = find(t2.right, t1, k);
                if (flag) return true;
            }
            if (t2.left == t1) {
                flag = find(t1.left, t2, k);
                if (flag) return true;
                flag = find(t1.right, t2, k);
                if (flag) return true;
            }
            if (t2.right == t1) {
                flag = find(t1.left, t2, k);
                if (flag) return true;
                flag = find(t1, t2.left, k);
                if (flag) return true;
            }
            if ( t1.right == t2) {
                flag = find(t1.left, t2, k);
                if (flag) return true;
                flag = find(t1, t2.left, k);
                if (flag) return true;
            }
            flag = find(t1.left, t2, k);
            if (flag) return true;
            flag = find(t1, t2.left, k);
            if (flag) return true;
            if(t1.val>t2.val){
                flag = find(t1, t2.right, k);
                if (flag) return true;
            }else{
                flag = find(t1.right, t2, k);
                if (flag) return true;
            }
        }
        if (t1.val + t2.val < k) {
            if ( t1.left == t2) {
                flag = find(t2.right, t1, k);
                if (flag) return true;
                flag = find(t2, t1.right, k);
                if (flag) return true;
            }
            if ( t2.left == t1) {
                flag = find(t2.right, t1, k);
                if (flag) return true;
                flag = find(t2, t1.right, k);
                if (flag) return true;
            }
            if ( t2.right == t1) {
                flag = find(t1.left, t2, k);
                if (flag) return true;
                flag = find(t1.right, t2, k);
                if (flag) return true;
            }
            if ( t1.right == t2) {
                flag = find(t2.left, t1, k);
                if (flag) return true;
                flag = find(t2.right, t1, k);
                if (flag) return true;
            }
            flag = find(t1.right, t2, k);
            if (flag) return true;
            flag = find(t1, t2.right, k);
            if (flag) return true;
            if(t1.val>t2.val){
                flag = find(t1.left, t2, k);
                if (flag) return true;
            }else{
                flag = find(t1, t2.left, k);
                if (flag) return true;
            }
        }
        return false;
    }*/

   //use other data structure to change the data structure.
    public boolean findTarget(TreeNode root, int k) {
        Set< Integer > set = new HashSet();
        return find(root, k, set);
    }
    public boolean find(TreeNode root, int k, Set < Integer > set) {
        if (root == null)
            return false;
        if (set.contains(k - root.val))
            return true;
        set.add(root.val);
        return find(root.left, k, set) || find(root.right, k, set);
    }
/*    public boolean findTarget(TreeNode root, int k) {
        List < Integer > list = new ArrayList();
        inorder(root, list);
        int l = 0, r = list.size() - 1;
        while (l < r) {
            int sum = list.get(l) + list.get(r);
            if (sum == k)
                return true;
            if (sum < k)
                l++;
            else
                r--;
        }
        return false;
    }
    public void inorder(TreeNode root, List< Integer > list) {
        if (root == null)
            return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }*/
}
