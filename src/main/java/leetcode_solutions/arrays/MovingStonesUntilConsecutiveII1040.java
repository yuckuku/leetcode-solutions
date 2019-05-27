package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author: L'Nan
 * Date: 2019/5/9 16:14
 * Description:
 */
public class MovingStonesUntilConsecutiveII1040 {
    class Solution {
        public int[] numMovesStonesII(int[] stones) {
            Arrays.sort(stones);
            int n = stones.length;
            int least = (int) (Math.pow(10, 9) + 5);
            int most = -(int) (Math.pow(10, 9) + 5);
            for (int i = 0, j = 0; i < n; i++) {
                while (j + 1 < n && stones[j + 1] - stones[i] < n)
                    j++;

                int now = n - (j - i + 1);

                if (j - i == n - 2 && stones[j] - stones[i] == j - i)
                    now++;

                least = Math.min(least, now);
            }

            most = Math.max(stones[n - 1] - stones[1], stones[n - 2] - stones[0]) - (n - 2);
            int[] re = new int[2];
            re[0] = least;
            re[1] = most;
            return re;
        }
    }

    @Test
    public void test() {
        int[] stones = new int[]{6, 5, 4, 3, 10};
        Solution s = new Solution();
        int[] re = s.numMovesStonesII(stones);
        System.out.println(Arrays.toString(re));
    }
}
