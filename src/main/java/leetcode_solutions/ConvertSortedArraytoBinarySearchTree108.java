package leetcode_solutions;

import common.TreeNode;
import org.junit.Test;

/**
 * Created by Administrator on 2017/12/13.
 */
public class ConvertSortedArraytoBinarySearchTree108 {

  //try to avoid create new object
  public TreeNode sortedArrayToBST(int[] nums) {

    if (nums == null || nums.length == 0) {
      return null;
    }
    if (nums.length == 1) {
      return new TreeNode(nums[0]);
    }
    TreeNode root = new TreeNode(nums[nums.length / 2]);
    int[] left = getLeft(nums, nums.length / 2);
    int[] right = getRight(nums, nums.length / 2);
    root.left = sortedArrayToBST(left);
    root.right = sortedArrayToBST(right);
    return root;
  }

  public int[] getLeft(int[] nums, int i) {
    if (i <= 0) {
      return null;
    }
    int[] left = new int[i];
    for (int j = 0; j < i; j++) {
      left[j] = nums[j];
    }
    return left;
  }

  public int[] getRight(int[] nums, int i) {
    if (i >= nums.length - 1) {
      return null;
    }
    int[] right = new int[nums.length - i - 1];
    for (int j = i + 1; j < nums.length; j++) {
      System.out.println("j and num[j] " + j + " " + nums[j]);
      right[j - i - 1] = nums[j];
    }
    return right;
  }

  @Test
  public void test() {
    int[] nums = new int[]{-10, -3, 0, 5, 9};
    sortedArrayToBST(nums);
  }

  public TreeNode sortedArrayToBST1(int[] nums) {
    if (nums == null || nums.length == 0) {
      return null;
    }
    return helper(nums, 0, nums.length - 1);
  }

  public TreeNode helper(int[] nums, int l, int r) {
    if (l > r) {
      return null;
    }
    int mid = l + (r - l) / 2;
    TreeNode root = new TreeNode(nums[mid]);
    root.left = helper(nums, l, mid - 1);
    root.right = helper(nums, mid + 1, r);
    return root;
  }
}
