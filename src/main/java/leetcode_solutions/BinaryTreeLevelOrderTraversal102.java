package leetcode_solutions;

import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeLevelOrderTraversal102 {

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
        public List<List<Integer>> levelOrder(TreeNode root) {

            List<List<Integer>> re=new ArrayList<>() ;

            LinkedList<TreeNode> queue=new LinkedList<>() ;
            queue.offer(root);
            while(!queue.isEmpty()){
                TreeNode tmp=queue.poll();

            }
        }

        private
    }
}
