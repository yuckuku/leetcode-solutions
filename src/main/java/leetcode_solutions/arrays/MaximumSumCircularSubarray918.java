package leetcode_solutions.arrays;

import org.junit.Test;

/**
 * @author: L'Nan
 * Date: 2019/6/2 22:00
 * Description:
 */
public class MaximumSumCircularSubarray918 {
    class Solution {
        public int maxSubarraySumCircular(int[] A) {
            int len = A.length;
            int[] sums = new int[len + 1];
            for (int i = 1; i < len + 1; i++) {
                sums[i] = sums[i - 1] + A[i - 1];
            }

            int re = Integer.MIN_VALUE;
            for (int i = 1; i < len; i++) {
                re = Math.max(re, sums[i] - sums[0]);
                re = Math.max(re, sums[len] + sums[0] - sums[i]);
            }
            re = Math.max(re, sums[len] - sums[0]);
            for (int i = 1; i < len + 1; i++) {
                for (int j = i + 1; j < len + 1; j++) {
                    re = Math.max(re, sums[j] - sums[i]);
                    re = Math.max(re, sums[len] + sums[i] - sums[j]);
                }
            }
            return re;
        }
    }

    class Solution1 {
        public int maxSubarraySumCircular(int[] A) {
            int cur_max, sum_max, cur_min, sum_min, total = 0;
            cur_max = sum_max = -30000;
            cur_min = sum_min = 30000;
            for (int v : A) {
                cur_max = Math.max(cur_max + v, v);
                sum_max = Math.max(sum_max, cur_max);
                cur_min = Math.min(cur_min + v, v);
                sum_min = Math.min(cur_min, sum_min);
                total += v;
            }
            if (total == sum_min) return sum_max;
            return Math.max(sum_max, total - sum_min);
        }
    }

    @Test
    public void test() {
        int[] A = new int[]{-2, -1, -3};
        Solution1 s = new Solution1();
        int re = s.maxSubarraySumCircular(A);
        System.out.println(re);
    }
}
