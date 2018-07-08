package leetcode_solutions.arrays;

import com.google.common.primitives.Ints;
import common.TreeNode;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ConstructBinaryTreefromInorderandPostorderTraversal106 {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (null == inorder || inorder.length < 1) {
            return null;
        } else if (inorder.length == 1) {
            return new TreeNode(inorder[0]);
        } else if (inorder.length == 2) {
            TreeNode root = new TreeNode(postorder[1]);
            if (inorder[0] == postorder[0]) {
                TreeNode left = new TreeNode(inorder[0]);
                root.left = left;
            } else {
                TreeNode right = new TreeNode(inorder[1]);
                root.right = right;
            }
            return root;
        } else if (inorder.length == 3) {
            TreeNode root = new TreeNode(postorder[2]);
            TreeNode left = new TreeNode(postorder[0]);
            TreeNode right = new TreeNode(postorder[1]);
            root.left = left;
            root.right = right;
            return root;
        } else {
            TreeNode root = new TreeNode(postorder[postorder.length - 1]);
            int index = 0;
            for (; index < inorder.length; index++) {
                if (inorder[index] == postorder[postorder.length - 1]) {
                    break;
                }
            }
            int[] leftInorder = Arrays.copyOfRange(inorder, 0, index);
            int[] rightInorder = Arrays.copyOfRange(inorder, index + 1, inorder.length);
            int[] leftPostorder = Arrays.copyOfRange(postorder, 0, leftInorder.length);
            int[] rightPostorder = Arrays.copyOfRange(postorder, leftInorder.length, postorder.length - 1);
            root.left = buildTree(leftInorder, leftPostorder);
            root.right = buildTree(rightInorder, rightPostorder);
            return root;
        }
    }

    @Test
    public void test() {
        int[] inorder = new int[]{1, 2};
        int[] postorder = new int[]{2, 1};
        buildTree(inorder, postorder);
    }

    public TreeNode buildTree0(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length <= 0 || postorder == null || postorder.length <= 0) return null;
        return buildTree(inorder, postorder, 0, inorder.length - 1, postorder.length - 1);
    }

    private TreeNode buildTree(int[] inorder, int[] postorder, int start, int iend, int pend) {
        int value = postorder[pend];
        TreeNode treeNode = new TreeNode(value);
        int temp = iend;
        while (temp > start) {
            if (inorder[temp] == value) break;
            temp--;
        }
        if (start != temp) {
            treeNode.left = buildTree(inorder, postorder, start, temp - 1, pend - iend + temp - 1);
        }
        if (temp != iend) {
            treeNode.right = buildTree(inorder, postorder, temp + 1, iend, pend - 1);
        }
        return treeNode;
    }
}
