package leetcode_solutions;

import common.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeZigzagLevelOrderTraversal103 {

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
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> re = new ArrayList<>();
            levelOrder(root, 0, false, re);
            return re;
        }

        private void levelOrder(TreeNode node, int level, boolean reverseFlag, List<List<Integer>> re) {
            if (null == node) return;
            if (level > re.size() - 1) {
                re.add(new ArrayList<>());
            }
            if (!reverseFlag) {
                re.get(level).add(node.val);
            } else {
                re.get(level).add(0, node.val);
            }

            levelOrder(node.left, level + 1, !reverseFlag, re);
            levelOrder(node.right, level + 1, !reverseFlag, re);
        }
    }

    @Test
    public void test() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        System.out.println(list);
        System.out.println("-------");
        list.add(0, 0);
        System.out.println(list);
    }

    class Solution0ms {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            traverse(root, result, 0);
            return result;
        }

        public void traverse(TreeNode root, List<List<Integer>> result, int level) {
            if (root != null) {
                LinkedList<Integer> list;
                if (result.size() <= level) {
                    list = new LinkedList<Integer>();
                    result.add(list);
                } else {
                    list = (LinkedList<Integer>) result.get(level);
                }

                if (level % 2 == 0) {
                    list.addLast(root.val);
                } else {
                    list.addFirst(root.val);
                }
                traverse(root.left, result, level + 1);
                traverse(root.right, result, level + 1);
            }
        }
    }
}
