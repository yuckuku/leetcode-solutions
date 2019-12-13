package leetcode_solutions;

import org.junit.Test;

import java.util.Arrays;

public class HouseRobberII213 {
    class Solution {
        public int rob(int[] nums) {
            return -1;
        }
    }

    //动态规划
    class Solution1 {
        public int rob(int[] nums) {
            if (nums.length == 0) return 0;
            if (nums.length == 1) return nums[0];
            return Math.max(myRob(Arrays.copyOfRange(nums, 0, nums.length - 1)),
                    myRob(Arrays.copyOfRange(nums, 1, nums.length)));
        }

        private int myRob(int[] nums) {
            int pre = 0, cur = 0, tmp;
            for (int num : nums) {
                tmp = cur;
                cur = Math.max(pre + num, cur);
                pre = tmp;
            }
            return cur;
        }
    }


    @Test
    public void test() {
        int[] nums = new int[]{
                1, 3, 1, 3, 100};
        Solution solution = new Solution();
        int re = solution.rob(nums);
        System.out.println(re);
    }
}
