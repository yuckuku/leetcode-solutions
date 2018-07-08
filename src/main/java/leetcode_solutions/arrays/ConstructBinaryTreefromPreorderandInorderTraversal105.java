package leetcode_solutions.arrays;

import common.TreeNode;

import java.util.Arrays;

public class ConstructBinaryTreefromPreorderandInorderTraversal105 {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (null == inorder || inorder.length < 1) {
            return null;
        } else if (inorder.length == 1) {
            return new TreeNode(inorder[0]);
        } else if (inorder.length == 2) {
            TreeNode root = new TreeNode(preorder[0]);
            if (inorder[0] == preorder[0]) {
                TreeNode right = new TreeNode(preorder[1]);
                root.right = right;
            } else {
                TreeNode left = new TreeNode(preorder[1]);
                root.left = left;
            }
            return root;
        } else {
            TreeNode root = new TreeNode(preorder[0]);
            int index = 0;
            for (; index < inorder.length; index++) {
                if (inorder[index] == preorder[0]) {
                    break;
                }
            }
            int[] leftInorder = Arrays.copyOfRange(inorder, 0, index);
            int[] rightInorder = Arrays.copyOfRange(inorder, index + 1, inorder.length);
            int[] leftPreorder = Arrays.copyOfRange(preorder, 1, leftInorder.length + 1);
            int[] rightPretorder = Arrays.copyOfRange(preorder, leftInorder.length + 1, preorder.length);
            root.left = buildTree(leftPreorder, leftInorder);
            root.right = buildTree(rightPretorder, rightInorder);
            return root;
        }
    }

    public TreeNode buildTree0(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        return buildTree(preorder, inorder, 0, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int[] inorder, int ps, int is, int ie) {
        int val = preorder[ps];
        TreeNode node = new TreeNode(val);
        int iRoot = ie;
        while (iRoot > is) {
            if (val == inorder[iRoot]) {
                break;
            }
            iRoot--;
        }

        if (iRoot > is) {
            node.left = buildTree(preorder, inorder, ps + 1, is, iRoot - 1);
        }

        if (iRoot < ie) {
            node.right = buildTree(preorder, inorder, ps + 1 + (iRoot - is), iRoot + 1, ie);
        }
        return node;

    }
}
