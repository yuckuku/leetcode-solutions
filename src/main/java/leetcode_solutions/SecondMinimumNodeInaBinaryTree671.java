package leetcode_solutions;

import common.TreeNode;

import java.util.HashSet;
import java.util.Set;

public class SecondMinimumNodeInaBinaryTree671 {
    //one loop to find the result
    //wrong answer
    public int findSecondMinimumValue(TreeNode root) {
        if (null == root.left) {
            return -1;
        }
        if (root.val == root.left.val && root.val == root.right.val) {
            int leftMin=findSecondMinimumValue(root.left);
            int rightMin=findSecondMinimumValue(root.left);
            if(-1==leftMin){
                return  rightMin;
            }
            if(-1==rightMin){
                return  leftMin;
            }
            return Math.min(leftMin, rightMin);
        }
        if (root.val == root.left.val && root.val != root.right.val) {
            if (null == root.left.left) {
                return root.right.val;
            } else {
                int leftMin=findSecondMinimumValue(root.left);
                if(-1==leftMin){
                   return root.right.val;
                }else{
                    return Math.min(leftMin, root.right.val);
                }
            }
        }

        if (root.val != root.left.val && root.val == root.right.val) {
            if (null == root.right.left) {
                return root.left.val;
            } else {
                int rightMin=findSecondMinimumValue(root.right);
                if(-1==rightMin){
                    return root.left.val;
                }else{
                    return Math.min(root.left.val,rightMin);
                }
            }
        }
        if(root.val != root.left.val && root.val != root.right.val){
            return Math.min(root.left.val,root.right.val);
        }
        return 0;
    }

    //use set
    public void dfs(TreeNode root, Set<Integer> uniques) {
        if (root != null) {
            uniques.add(root.val);
            dfs(root.left, uniques);
            dfs(root.right, uniques);
        }
    }
    public int findSecondMinimumValue0(TreeNode root) {
        Set<Integer> uniques = new HashSet<Integer>();
        dfs(root, uniques);

        int min1 = root.val;
        long ans = Long.MAX_VALUE;
        for (int v : uniques) {
            if (min1 < v && v < ans) ans = v;
        }
        return ans < Long.MAX_VALUE ? (int) ans : -1;
    }
}
