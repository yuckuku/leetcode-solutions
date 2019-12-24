package leetcode_solutions;

import org.junit.Test;

import java.util.Arrays;

public class CoinChange322 {
    // greedy : can not cover all tests
    class Solution {
        private int[] coinsCopy;
        private int curIndex;
        private boolean flag;

        //greedy
        public int coinChange(int[] coins, int amount) {
            if (amount == 0) return 0;
            coinsCopy = coins;
            curIndex = coinsCopy.length - 1;
            flag = true;
            Arrays.sort(coinsCopy);
            int re = helper(amount);
            if (flag) {
                return re;
            }
            return -1;
        }

        private int helper(int amount) {
            while (curIndex >= 0 && amount < coinsCopy[curIndex])
                curIndex--;
            int i = curIndex;
            for (; i >= 0; i--) {
                if (amount > coinsCopy[i]) {
                    return 1 + helper(amount - coinsCopy[i]);
                } else if (amount == coinsCopy[i]) {
                    return 1;
                }
            }

            flag = false;
            return -1;
        }
    }

    //DP
    //执行用时:2228ms,在所有java提交中击败了5.03%的用户内存消耗:35.8MB,在所有java提交中击败了95.23%的用户
    class SolutionDP {
        public int coinChange(int[] coins, int amount) {
            if (amount == 0) return 0;
            int[] dp = new int[amount + 1];


            for (int i = 0; i < coins.length; i++) {
                if (coins[i] <= amount) dp[coins[i]] = 1;
//                try {
//                    dp[coins[i]] = 1;
//                } catch (ArrayIndexOutOfBoundsException e) {
//                }
            }

            for (int i = 1; i <= amount; i++) {
                if (dp[i] != 1) {
                    for (int r = i - 1, l = 1; r >= l; r--, l++) {
                        if (dp[r] != 0 && dp[l] != 0)
                            dp[i] = dp[i] == 0 ? dp[r] + dp[l] : Math.min(dp[i], dp[r] + dp[l]);
                    }
                }
            }
            return dp[amount] != 0 ? dp[amount] : -1;
        }
    }

    @Test
    public void test() {
        int[] coins = new int[]{1, 2, 5};
        int amount = 11;
        SolutionDP solutionDP = new SolutionDP();
        int re = solutionDP.coinChange(coins, amount);
        System.out.println(re);
    }

    //执行用时:12ms,在所有java提交中击败了85.19%的用户内存消耗:35.8MB,在所有java提交中击败了95.23%的用户
    class SolutionDP2 {
        public int coinChange(int[] coins, int amount) {

            int[] f = new int[amount + 1];
            f[0] = 0;

            for (int i = 1; i <= amount; i++) {

                int cost = Integer.MAX_VALUE;

                for (int j = 0; j < coins.length; j++) {
                    if (i - coins[j] >= 0) {
                        if (f[i - coins[j]] != Integer.MAX_VALUE)
                            cost = Math.min(cost, f[i - coins[j]] + 1);
                    }
                }

                f[i] = cost;
            }

            return f[amount] == Integer.MAX_VALUE ? -1 : f[amount];
        }
    }


    class Solution2ms {
        private int res = Integer.MAX_VALUE;

        public int coinChange(int[] coins, int amount) {
            Arrays.sort(coins);
            helper(coins, coins.length - 1, 0, amount);
            return res == Integer.MAX_VALUE ? -1 : res;
        }

        private void helper(int[] coins, int start, int cur, int amount) {
            if (start < 0) return;
            if (amount % coins[start] == 0) {
                res = Math.min(res, cur + amount / coins[start]);
                return;
            }
            for (int i = amount / coins[start]; i >= 0; i--) {
                if (cur + i >= res - 1) break;
                helper(coins, start - 1, cur + i, amount - i * coins[start]);
            }
        }
    }
}
