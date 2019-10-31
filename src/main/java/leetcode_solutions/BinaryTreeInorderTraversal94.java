package leetcode_solutions;

import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeInorderTraversal94 {
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
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> re = new ArrayList<>();
            if (root == null) return re;
            re.addAll(inorderTraversal(root.left));
            re.add(root.val);
            re.addAll(inorderTraversal(root.right));
            return re;
        }
    }

    class Solution1 {
        List<Integer> result = new ArrayList<>();

        public List<Integer> inorderTraversal(TreeNode root) {
            // 迭代方法
            // List<Integer> result = new LinkedList<>();
            // Stack<TreeNode> stack = new Stack<>();
            // TreeNode currentNode = root;
            // while(currentNode != null || !stack.isEmpty()){
            //     while(currentNode != null){
            //         stack.push(currentNode);
            //         currentNode = currentNode.left;
            //     }
            //     currentNode = stack.pop();
            //     result.add(currentNode.val);
            //     currentNode = currentNode.right;
            // }
            // return result;
            inorder(root);
            return result;
        }

        private void inorder(TreeNode node) {
            if (node == null) {
                return;
            }
            inorder(node.left);
            result.add(node.val);
            inorder(node.right);
        }

    }

    class Solution_iteratively {
        public List<Integer> inorderTraversal(TreeNode root) {
//             迭代方法
            List<Integer> result = new LinkedList<>();
            Stack<TreeNode> stack = new Stack<>();
            TreeNode currentNode = root;
            while (currentNode != null || !stack.isEmpty()) {
                while (currentNode != null) {
                    stack.push(currentNode);
                    currentNode = currentNode.left;
                }
                currentNode = stack.pop();
                result.add(currentNode.val);
                currentNode = currentNode.right;
            }
            return result;
        }
    }
}
