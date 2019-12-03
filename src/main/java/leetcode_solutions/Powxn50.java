package leetcode_solutions;

public class Powxn50 {
    class Solution {
        public double myPow(double x, int n) {
            return Math.pow(x, n);
        }
    }

    /**
     * 时间复杂度：O(logn). 每一次我们使用公式 (x ^ n) ^ 2 = x ^ (2 * n)
     * n都变为原来的一半。因此我们需要至多 O(logn) 次操作来得到结果。
     * <p>
     * 空间复杂度：O(logn). 每一次计算，我们需要存储 x ^ (n / 2)
     * 的结果。 我们需要计算 O(logn) 次，所以空间复杂度为 O(logn) 。
     */
    class Solution1 {
        private double fastPow(double x, long n) {
            if (n == 0) {
                return 1.0;
            }
            double half = fastPow(x, n / 2);
            if (n % 2 == 0) {
                return half * half;
            } else {
                return half * half * x;
            }
        }

        public double myPow(double x, int n) {
            long N = n;
            if (N < 0) {
                x = 1 / x;
                N = -N;
            }

            return fastPow(x, N);
        }
    }

    class Solution2 {
        public double myPow(double x, int n) {
            long N = n;
            if (N < 0) {
                x = 1 / x;
                N = -N;
            }
            double ans = 1;
            double current_product = x;
            for (long i = N; i > 0; i /= 2) {
                if ((i % 2) == 1) {
                    ans = ans * current_product;
                }
                current_product = current_product * current_product;
            }
            return ans;
        }
    }

    ;

}
