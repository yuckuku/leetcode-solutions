package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.Arrays;

public class SumofSubsequenceWidths891 {
    //TLE
    class Solution {
        private double mod = (Math.pow(10, 9) + 7);

        public int sumSubseqWidths(int[] A) {
            Arrays.sort(A);
            int re = (int) f(A, 0, A.length - 1);
            return re;
        }

        private double f(int[] A, int l, int r) {
            int n = r - l - 1;
            if (n <= 0) {
                return (A[r] - A[l]) % mod;
            }

            double re = 0;
            double count = Math.pow(2, n);
            re += (count * (A[r] - A[l])) % mod;

            for (int i = n - 1; i >= 0; i--) {
                count = Math.pow(2, i);
                re += (count * (A[r + i - n] - A[l])) % mod;
            }
            for (int i = 1; i <= n; i++) {
                count = Math.pow(2, n - i);
                re += (count * (A[r] - A[l + i])) % mod;
            }

            re += f(A, l + 1, r - 1) % mod;
            re %= mod;
            return re;
        }
    }

    class Solution1 {
        public int sumSubseqWidths(int[] A) {
            Arrays.sort(A);
            int n = A.length;
            long[] p = new long[20001];
            int temp = 1;
            for (int i = 0; i < 20001; i++) {
                p[i] = temp;
                temp *= 2;
                temp %= 1000000007;
            }
            long res = 0;
            for (int i = 0; i < n; i++) {
                res += (p[i] - p[n - i - 1]) * A[i];
                res %= 1000000007;
            }
            return (int) res;
        }
    }

    class SolutionOnLeetcode1 {
        public int sumSubseqWidths(int[] A) {
            Arrays.sort(A);
            long c = 1, res = 0, mod = (long) 1e9 + 7;
            for (int i = 0; i < A.length; ++i, c = (c << 1) % mod)
                res = (res + A[i] * c - A[A.length - i - 1] * c) % mod;
            return (int) ((res + mod) % mod);
        }
    }

    @Test
    public void test() {
        int[] A = new int[]{1, 2, 3};
        Solution s = new Solution();
        System.out.println(s.mod);
        int re = s.sumSubseqWidths(A);
        System.out.println(re);
    }

}
