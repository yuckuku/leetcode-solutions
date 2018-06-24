package leetcode_solutions;

import common.TreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/11.
 */
public class DiameterofBinaryTree543 {

  //find all paths between leaf nodes---abandoned
  //max left+max right
  public int diameterOfBinaryTree(TreeNode root) {
    if (root == null) {
      return 0;
    }
    List<Integer> list = f(root);
    return list.get(2);
  }

  public List<Integer> f(TreeNode root) {
    List<Integer> re = new ArrayList<>();
    re.add(0);
    re.add(0);
    re.add(0);
    if (root == null) {
      return re;
    }
    List<Integer> left = f(root.left);
    List<Integer> right = f(root.right);
    if (root.left != null) {
      re.set(0, Math.max(left.get(0), left.get(1)) + 1);
    }
    if (root.right != null) {
      re.set(1, Math.max(right.get(0), right.get(1)) + 1);
    }
    int path = Math.max(re.get(0) + re.get(1), left.get(2));
    path = Math.max(path, right.get(2));
    re.set(2, path);
    return re;
  }

  // ues depth
  int diameter;
  public int diameterOfBinaryTree1(TreeNode root) {
    diameter = 0;
    depth(root);
    return diameter;
  }

  public int depth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int leftD = depth(root.left);
    int rightD = depth(root.right);
    diameter = Math.max(diameter, leftD + rightD);
    return Math.max(leftD, rightD) + 1;
  }
}
