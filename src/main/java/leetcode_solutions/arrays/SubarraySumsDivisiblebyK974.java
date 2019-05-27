package leetcode_solutions.arrays;

import org.junit.Test;

/**
 * @author: L'Nan
 * Date: 2019/5/24 15:03
 * Description:
 */
public class SubarraySumsDivisiblebyK974 {
    class Solution {
        public int subarraysDivByK(int[] A, int K) {
            int re = 0;
            int len = A.length;
            int[] sums = new int[len + 1];
            int[] dp = new int[len + 1];
            for (int i = 0; i < len; i++) {
                sums[i + 1] = sums[i] + A[i];
            }
            for (int i = 1; i < len + 1; i++) {
                for (int j = i - 1; j >= 0; j--) {
                    if ((sums[i] - sums[j]) % K == 0) {
                        dp[i] = dp[j] + 1;
                        re += dp[i];
                        break;
                    }
                }
            }
            return re;
        }
    }

    class SolutionOnLeetcode1 {
        public int subarraysDivByK(int[] A, int K) {
            int N = A.length;
            int[] P = new int[N + 1];
            for (int i = 0; i < N; ++i)
                P[i + 1] = P[i] + A[i];

            int[] count = new int[K];
            for (int x : P)
                count[(x % K + K) % K]++; // as the sum can be negative, taking modulo twice

            int ans = 0;
            for (int v : count)
                ans += v * (v - 1) / 2;
            return ans;
        }
    }

    @Test
    public void test() {
        int[] A = new int[]{4, 5, 0, -2, -3, 1};
        int K = 5;
        Solution s = new Solution();
        int re = s.subarraysDivByK(A, K);
        System.out.println(re);
    }
}
