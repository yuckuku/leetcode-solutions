package leetcode_solutions;

import org.junit.Test;

import java.math.BigDecimal;

public class StudentAttendanceRecordII552 {
    class Solution {

        //执行用时:937ms,在所有Java提交中击败了5.22%的用户
        //内存消耗:51.4MB,在所有Java提交中击败了14.29%的用户
        public int checkRecord(int n) {

            if (n == 1) return 3;
            if (n == 2) return 8;

            int mod = (int) 1e9 + 7;
            int[] A = new int[n + 1];
            A[0] = 1;
            A[1] = 2;
            A[2] = 4;
            for (int i = 3; i <= n; i++) {
                int tmp = (A[i - 1] + A[i - 2]) % mod;
                tmp = (tmp + A[i - 3]) % mod;
                A[i] = tmp;
            }

            //DP[n]=A[n]+(A[0]*A[n-1]+A[1]*A[n-2]+A[2]*A[n-3]+...+A[n-1]*A[0])
            int re = A[n];


            System.out.println(re);
            for (int i = 0; i <= n - 1; i++) {
                BigDecimal tmp = new BigDecimal(A[i]).multiply(new BigDecimal(A[n - 1 - i]));
                System.out.println(tmp);
                BigDecimal[] c = tmp.divideAndRemainder(new BigDecimal(mod));

                re += c[1].intValue();
                re %= mod;
            }

            return re % mod;
        }
    }

    @Test
    public void test() {
        int n = 100;//re=985598218
        Solution solution = new Solution();
        int re = solution.checkRecord(n);
        System.out.println(re);
    }


    //暴力法
    public class Solution1 {
        int count, M = 1000000007;

        public int checkRecord(int n) {
            count = 0;
            gen("", n);
            return count;
        }

        public void gen(String s, int n) {
            if (n == 0 && checkRecord(s))
                count = (count + 1) % M;
            else if (n > 0) {
                gen(s + "A", n - 1);
                gen(s + "P", n - 1);
                gen(s + "L", n - 1);
            }
        }

        public boolean checkRecord(String s) {
            int count = 0;
            for (int i = 0; i < s.length() && count < 2; i++)
                if (s.charAt(i) == 'A')
                    count++;
            return s.length() > 0 && count < 2 && s.indexOf("LLL") < 0;
        }
    }

    //思路同我
    public class Solution2 {
        int M = 1000000007;

        public int checkRecord(int n) {
            int[] f = new int[n + 1];
            f[0] = 1;
            for (int i = 1; i <= n; i++)
                f[i] = func(i);
            int sum = func(n);
            for (int i = 1; i <= n; i++) {
                sum += (f[i - 1] * f[n - i]) % M;
            }
            return sum % M;
        }

        public int func(int n) {
            if (n == 0)
                return 1;
            if (n == 1)
                return 2;
            if (n == 2)
                return 4;
            if (n == 3)
                return 7;
            return (2 * func(n - 1) - func(n - 4)) % M;
        }
    }

    //DP
    public class Solution3 {
        long M = 1000000007;

        public int checkRecord(int n) {
            long[] f = new long[n <= 5 ? 6 : n + 1];
            f[0] = 1;
            f[1] = 2;
            f[2] = 4;
            f[3] = 7;
            for (int i = 4; i <= n; i++)
                f[i] = ((2 * f[i - 1]) % M + (M - f[i - 4])) % M;
            long sum = f[n];
            for (int i = 1; i <= n; i++) {
                sum += (f[i - 1] * f[n - i]) % M;
            }
            return (int) (sum % M);
        }
    }

    //常数空间的动态规划
    //状态转移
    public class Solution4 {
        long M = 1000000007;

        public int checkRecord(int n) {
            long a0l0 = 1;
            long a0l1 = 0, a0l2 = 0, a1l0 = 0, a1l1 = 0, a1l2 = 0;
            for (int i = 0; i < n; i++) {
                long new_a0l0 = (a0l0 + a0l1 + a0l2) % M;
                long new_a0l1 = a0l0;
                long new_a0l2 = a0l1;
                long new_a1l0 = (a0l0 + a0l1 + a0l2 + a1l0 + a1l1 + a1l2) % M;
                long new_a1l1 = a1l0;
                long new_a1l2 = a1l1;
                a0l0 = new_a0l0;
                a0l1 = new_a0l1;
                a0l2 = new_a0l2;
                a1l0 = new_a1l0;
                a1l1 = new_a1l1;
                a1l2 = new_a1l2;
            }
            return (int) ((a0l0 + a0l1 + a0l2 + a1l0 + a1l1 + a1l2) % M);
        }
    }

    //使用更少的变量
    public class Solution5 {
        long M = 1000000007;

        public int checkRecord(int n) {
            long a0l0 = 1, a0l1 = 0, a0l2 = 0, a1l0 = 0, a1l1 = 0, a1l2 = 0;
            for (int i = 0; i <= n; i++) {
                long a0l0_ = (a0l0 + a0l1 + a0l2) % M;
                a0l2 = a0l1;
                a0l1 = a0l0;
                a0l0 = a0l0_;
                long a1l0_ = (a0l0 + a1l0 + a1l1 + a1l2) % M;
                a1l2 = a1l1;
                a1l1 = a1l0;
                a1l0 = a1l0_;
            }
            return (int) a1l0;
        }
    }

    //状态转移
    //矩阵快速幂
    class Solution3ms {
        public int checkRecord(int n) {
            long[][] a = new long[][]{{1}, {1}, {0}, {1}, {0}, {0}};
            long[][] aMatrix = new long[][]{{1, 1, 1, 0, 0, 0}, {1, 0, 0, 0, 0, 0}, {0, 1, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1}, {0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 1, 0}};
            while (n > 0) {
                int m = n & 1;
                if (m == 1) {
                    a = this.multipleMatrix(aMatrix, a);
                }
                aMatrix = this.multipleMatrix(aMatrix, aMatrix);
                n = n >> 1;
            }
            /**
             * 0 A0L0
             * 1 A0L1
             * 2 A0L2
             * 3 A1L0
             * 4 A1L1
             * 5 A1L2
             */
            return (int) a[3][0];

        }

        public long[][] multipleMatrix(long[][] a, long[][] b) {
            long mod = (long) 1e9 + 7;
            long c[][] = new long[a.length][b[0].length];
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < b[i].length; j++) {
                    for (int k = 0; k < a[i].length; k++) {
                        c[i][j] = (c[i][j] + a[i][k] * b[k][j]) % mod;
                    }
                }
            }
            return c;
        }
    }
}
