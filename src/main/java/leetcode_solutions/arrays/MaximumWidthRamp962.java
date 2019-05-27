package leetcode_solutions.arrays;

import org.junit.Test;

/**
 * @author: L'Nan
 * Date: 2019/5/26 21:26
 * Description:
 */
public class MaximumWidthRamp962 {
    class Solution {
        public int maxWidthRamp(int[] A) {
            int re = 0;
            int len = A.length;
            int i = 0, j = len - 1;
            for (; i <= j; i++) {
                for (int k = len - 1; k > i; k--) {
                    if (A[i] <= A[k]) {
                        re = Math.max(re, (k - i));
                        j = len - 1 - k + i;
                        break;
                    }
                }
            }
            return re;
        }
    }

    class SolutionOnLeetcode1 {
        public int maxWidthRamp(int[] A) {
            int n = A.length;
            int i, j, max = 0;
            int[] maxR = new int[n], minL = new int[n];
            minL[0] = A[0];
            for (i = 1; i < n; i++) {
                minL[i] = Math.min(A[i], minL[i - 1]);
            }
            maxR[n - 1] = A[n - 1];
            for (j = n - 2; j >= 0; j--) {
                maxR[j] = Math.max(A[j], maxR[j + 1]);
            }
            i = 0;
            j = 0;
            while (i < n && j < n) {
                if (minL[i] <= maxR[j]) {
                    max = Math.max(max, j - i);
                    j++;
                } else {
                    i++;
                }
            }
            return max;
        }
    }

    @Test
    public void test() {
        int[] A = new int[]{6, 0, 8, 2, 1, 5};
        SolutionOnLeetcode1 s = new SolutionOnLeetcode1();
        int re = s.maxWidthRamp(A);
        System.out.println(re);
    }
}
