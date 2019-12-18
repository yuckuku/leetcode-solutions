package leetcode_solutions;

import org.junit.Test;

public class PerfectSquares279 {
    /**
     * 标签：动态规划
     * 首先初始化长度为n+1的数组dp，每个位置都为0
     * 如果n为0，则结果为0
     * 对数组进行遍历，下标为i，每次都将当前数字先更新为最大的结果，即dp[i]=i，比如i=4，最坏结果为4=1+1+1+1即为4个数字
     * 动态转移方程为：dp[i] = MIN(dp[i], dp[i - j * j] + 1)，i表示当前数字，j*j表示平方数
     * 时间复杂度：O(n*sqrt(n))，sqrt为平方根
     */
    //假设最小公式值m = ƒ(n)
    //那么n的值满足下列公式 ∑(A[i] * A[i]) = n
    //令 k 为满足最小值 m 的时候，最大的平方数  。 令  d + k * k = n ;  d >= 0;
    //注意：一定要是满足m最小的时候的k值,一味的取最大平方数,就是贪心算法了
    //得出 f(d) + f(k*k) = f(n);
    //显然 f(k*k) = 1; 则  f(d) + 1 = f(n); 因为 d = n - k*k;
    //则可以推出ƒ(n - k * k) + 1 = ƒ(n) ;  且 k * k <= n;

    //执行用时:39ms,在所有java提交中击败了63.44%的用户内存消耗:35.7MB,在所有java提交中击败了40.56%的用户
    class Solution {
        public int numSquares(int n) {
            int[] dp = new int[n + 1]; // 默认初始化值都为0
            for (int i = 1; i <= n; i++) {
                dp[i] = i; // 最坏的情况就是每次+1
                for (int j = 1; i - j * j >= 0; j++) {
                    dp[i] = Math.min(dp[i], dp[i - j * j] + 1); // 动态转移方程
                }
            }
            return dp[n];
        }

    }

    @Test
    public void test() {
        System.out.println(Math.sqrt(Integer.MAX_VALUE));
    }

    class Solution1ms {
        public int numSquares(int n) {
            while (n % 4 == 0) {
                n /= 4;
            }
            if (n % 8 == 7) {
                return 4;
            }
            for (int a = 0; a * a <= n; a++) {
                if (n == a * a) {
                    return 1;
                }
            }
            for (int a = 0; a * a <= n; a++) {
                int b = (int) (Math.sqrt(n - a * a));
                if (a * a + b * b == n && a >= 0 && b >= 0) {
                    return 2;
                }
            }
            return 3;
        }
    }

    class Solution3ms {
        public int numSquares(int n) {
            if (square1(n)) {
                return 1;
            } else if (square2(n)) {
                return 2;
            } else if (square3(n)) {
                return 3;
            } else {
                return 4;
            }


        }

        private boolean square3(int n) {
            for (int i = 1; i * i < n; i++) {
                if (square2(n - i * i)) {
                    return true;
                }
            }
            return false;
        }

        private boolean square2(int n) {
            for (int i = 1; i * i < n; i++) {
                if (square1(n - i * i)) {
                    return true;
                }
            }
            return false;
        }

        private boolean square1(int n) {
            int x = (int) Math.sqrt(n);
            return x * x == n;
        }
    }
}
