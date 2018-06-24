package leetcode_solutions;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/12/11.
 */
public class MissingNumber268 {

  public int missingNumber(int[] nums) {
    Arrays.sort(nums);
    int point = 0;
    for (int num : nums) {
      if (num != point) {
        break;
      } else {
        point++;
      }
    }
    return point;
  }


  //division , not good
  public int missingNumber1(int[] nums) {
    Arrays.sort(nums);
    if (nums[0] != 0) {
      return 0;
    }
    if (nums.length == 1 && nums[0] == 0) {
      return 1;
    }
    if (nums.length == 2 && nums[1] == 1) {
      return 2;
    }
    if (nums.length == 2 && nums[1] == 2 && nums[0] == 0) {
      return 1;
    }
    if (nums.length == 2 && nums[1] == 2 && nums[0] == 1) {
      return 0;
    }
    int i = 0;
    int j = nums.length - 1;
    while (i != j - 1) {
      int mid = i + (j - i) / 2;
      if (nums[mid] <= nums[i] + (nums[j] - nums[i]) / 2) {
        i = mid;
      } else if (nums[mid] > nums[i] + (nums[j] - nums[i]) / 2) {
        j = mid;
      } else {
        System.out.println("break");
        break;
      }
    }
    if (nums[j] - nums[i] == 1) {
      System.out.println(i);
      System.out.println(j);
      System.out.println(nums[i]);
      System.out.println(nums[j]);
      System.out.println("'yeah");
      return nums[nums.length - 1]+1;
    } else {
      System.out.println(i);
      System.out.println(j);
      return nums[j] - 1;
    }
  }

  //use ^
  public int missingNumber2(int[] nums) {
    if(nums == null || nums.length == 0) {
      return 0;
    }
    int res = nums.length;    // nums.length = n
    for(int i = 0; i < nums.length; i++) {
      res ^= i;
      res ^= nums[i];
    }
    return res;
  }
}
