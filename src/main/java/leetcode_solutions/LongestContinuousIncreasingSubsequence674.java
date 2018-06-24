package leetcode_solutions;

import org.junit.Test;

/**
 * Created by Administrator on 2017/12/20.
 */
public class LongestContinuousIncreasingSubsequence674 {

  public int findLengthOfLCIS(int[] nums) {

    if (nums.length < 1) {
      return 0;
    }
    if (nums.length == 1) {
      return 1;
    }
    int max = 1;
    int temp = 1;
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] > nums[i - 1]) {
        temp++;
      } else {
        temp = 1;
      }
      max = temp > max ? temp : max;
      System.out.println(max);
    }
    return max;
  }

  @Test
  public void test() {
    int[] nums = new int[]{1, 3, 5, 4, 7};
    findLengthOfLCIS(nums);
  }
}
