package leetcode_solutions;

import common.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeLevelOrderTraversalII107 {

    List<List<Integer>> reList = new ArrayList<>();

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        getList(root, 1);
        return reverse();
    }

    private List<List<Integer>> reverse() {
        List<List<Integer>> list = new ArrayList<>();
        for (int i = reList.size() - 1; i >= 0; i--) {
            list.add(reList.get(i));
        }
        return list;
    }

    private void getList(TreeNode root, int level) {
        if (null == root) {
            return;
        }

        if (level > reList.size()) {
            List<Integer> list = new ArrayList<>();
            list.add(root.val);
            reList.add(list);
        } else if (level <= reList.size()) {
            List<Integer> list = reList.get(level - 1);
            list.add(root.val);
        }

        getList(root.left, level + 1);
        getList(root.right, level + 1);
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        TreeNode nood2 = new TreeNode(2);
        TreeNode nood3 = new TreeNode(3);
        TreeNode nood4 = new TreeNode(4);
        TreeNode nood5 = new TreeNode(5);
        root.left = nood2;
        root.right = nood3;
        nood2.left = nood4;
        nood2.right = nood5;

        List<List<Integer>> list = levelOrderBottom(root);

    }
}
