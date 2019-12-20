package leetcode_solutions;

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
    class SolutionDP {
        public int coinChange(int[] coins, int amount) {

        }
    }
}
