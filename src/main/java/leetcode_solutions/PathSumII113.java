package leetcode_solutions;

import common.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PathSumII113 {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    //执行用时:1ms,在所有java提交中击败了100.00%的用户内存消耗:37.5MB,在所有java提交中击败了82.41%的用户
    class Solution {
        public List<List<Integer>> pathSum(TreeNode root, int sum) {
            List<List<Integer>> re = new ArrayList<>();
            if (null == root) return re;
            List<Integer> path = new ArrayList<>();
            dfs(root, re, path, 0, sum);
            return re;
        }

        private void dfs(TreeNode node, List<List<Integer>> re, List<Integer> path, int tmp, int sum) {
            if (node.left == null && node.right == null) {
                if (tmp + node.val == sum) {
                    List<Integer> newPath = new ArrayList<>();
                    newPath.addAll(path);
                    newPath.add(node.val);
                    re.add(newPath);
                }
            }
//            else if (tmp + node.val > sum) {
//                return;
//            }
            else {
                path.add(node.val);
                if (node.left != null) {
                    dfs(node.left, re, path, tmp + node.val, sum);
                }
                if (node.right != null) {
                    dfs(node.right, re, path, tmp + node.val, sum);
                }
                path.remove(path.size() - 1);
            }
        }
    }

    //看上去比我的简洁
    class Solution1 {
        public List<List<Integer>> pathSum(TreeNode root, int sum) {
            List<List<Integer>> res = new ArrayList<>();
            helper(root, sum, res, new ArrayList<Integer>());
            return res;
        }

        private void helper(TreeNode root, int sum, List<List<Integer>> res, ArrayList<Integer> tmp) {
            if (root == null) return;
            tmp.add(root.val);
            if (root.left == null && root.right == null && sum - root.val == 0) res.add(new ArrayList<>(tmp));
            helper(root.left, sum - root.val, res, tmp);
            helper(root.right, sum - root.val, res, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }


    @Test
    public void test() {

    }
}
