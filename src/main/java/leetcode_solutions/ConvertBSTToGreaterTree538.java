package leetcode_solutions;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/19.
 */
public class ConvertBSTToGreaterTree538 {
    public TreeNode convertBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        BSTToList(root, list);
        sum(root, list);
        return root;
    }

    public void sum(TreeNode root, List<Integer> list) {
        if (null == root) {
            return;
        }
        sum(root.left, list);
        int i = list.indexOf(root.val);
        if (i != -1) {
            for (i++; i < list.size(); i++) {
                root.val += list.get(i);
            }
        }
        sum(root.right, list);
    }

    public void BSTToList(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        BSTToList(root.left, list);
        list.add(root.val);
        BSTToList(root.right, list);
    }
}
