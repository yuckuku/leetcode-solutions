package leetcode_solutions;

public class HouseRobber198 {
    class Solution {
        public int rob(int[] nums) {
            int pre = 0, cur = 0, tmp;
            for (int num : nums) {
                tmp = cur;
                cur = Math.max(pre + num, cur);
                pre = tmp;
            }
            return cur;
        }
    }

    //TLE
    class Solution1 {
        public int rob(int[] nums) {
            return helper(nums, 0, nums.length - 1);
        }

        private int helper(int[] nums, int l, int r) {
            if (l == r) return nums[l];
            else if (l > r) return 0;
            else {
                return Math.max(nums[l] + helper(nums, l + 2, r), helper(nums, l + 1, r));
            }
        }
    }

    //DP
    class Solution2 {
        public int rob(int[] nums) {
            int len = nums.length;
            if (len == 0)
                return 0;
            int[] dp = new int[len + 1];
            dp[0] = 0;
            dp[1] = nums[0];
            for (int i = 2; i <= len; i++) {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
            }
            return dp[len];
        }
    }

}
