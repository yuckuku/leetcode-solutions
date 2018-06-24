package leetcode_solutions;

import java.util.Arrays;

public class MinCostClimbingStairs746 {
    //wrong answer
    public int minCostClimbingStairs(int[] cost) {

        if (null == cost) {
            return 0;
        }
        int len = cost.length;
        if (0 == len)
            return 0;
        if (1 == len)
            return cost[0];
        if (2 == len)
            return Math.min(cost[0], cost[1]);
        int cost1 = Math.min(cost[len - 2], cost[len - 1]);
        return minCostClimbingStairs(Arrays.copyOf(cost, len - 2)) + cost1;
    }

    //right answer
    public int minCostClimbingStairs1(int[] cost) {
        int length = cost.length + 1;
        int[] dp = new int[length];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i < length; i++) {
            dp[i] = Math.min(dp[i - 2] + cost[i - 2], dp[i - 1] + cost[i - 1]);
        }
        return dp[length - 1];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5};
        int[] temp = Arrays.copyOf(nums, nums.length - 3);
        for (int i :
                temp) {
            System.out.println(i);
        }
    }
}
