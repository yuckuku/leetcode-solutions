package leetcode_solutions;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePaths257 {

    private List<String> list = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        if (null == root) {
            return list;
        } else {
            triverseTree0(root, null);
        }
        return list;
    }

    private void triverseTree0(TreeNode root, String preStr) {
        if (null != preStr) {
            preStr = preStr + "->" + root.val;
        } else {
            preStr =new String(root.val+"") ;
        }
        if (root.left == null && root.right == null) {
            list.add(preStr);
        } else if (root.left != null && root.right == null) {
            triverseTree0(root.left, preStr);
        } else if (root.left == null && root.right != null) {
            triverseTree0(root.right, preStr);
        } else {
            triverseTree0(root.left, preStr);
            triverseTree0(root.right, preStr);
        }
    }

    //why sb not right???
    private void triverseTree(TreeNode root, StringBuilder preStrSb) {
        if (0 != preStrSb.length()) {
            preStrSb.append("->").append(root.val);
        } else {
            preStrSb.append(root.val);
        }
        if (root.left == null && root.right == null) {
            list.add(preStrSb.toString());
        } else if (root.left != null && root.right == null) {
            triverseTree(root.left, preStrSb);
        } else if (root.left == null && root.right != null) {
            triverseTree(root.right, preStrSb);
        } else {
            triverseTree(root.left, preStrSb);
            triverseTree(root.right, preStrSb);
        }
    }
}
