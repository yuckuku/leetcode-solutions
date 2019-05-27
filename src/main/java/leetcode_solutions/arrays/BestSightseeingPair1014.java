package leetcode_solutions.arrays;

import org.junit.Test;

/**
 * @author: L'Nan
 * Date: 2019/5/23 21:00
 * Description:
 */
public class BestSightseeingPair1014 {
    class Solution {
        public int maxScoreSightseeingPair(int[] A) {
            int len = A.length;
            int i = 0, j = 1;
            int re = -1;
            while (i < len - 1) {
                int next = j;
                int max = 0;
                for (; j < len; j++) {
                    int sum = A[i] + A[j] + i - j;
                    if (sum > max) {
                        max = sum;
                        next = j;
                    }
                }
                for (int k = i + 1; k < next; k++) {
                    int sum = A[k] + A[next] + k - next;
                    re = Math.max(re, sum);
                }
                re = Math.max(re, max);
                i = next;
                j = next + 1;
            }
            return re;
        }
    }

    class SolutionOnLeetcode1 {
        public int maxScoreSightseeingPair(int[] A) {
            int rst = A[0];
            int max = A[0];
            int tmp = 0;
            if (A.length == 0) return 0;
            for (int i = 1; i < A.length; i++) {
                max--;
                tmp = A[i] + max;
                if (tmp > rst) rst = tmp;
                if (A[i] > max) max = A[i];
            }
            return rst;
        }
    }

    @Test
    public void test() {
        int[] A = new int[]{8,1,5,2,6};
        SolutionOnLeetcode1 s = new SolutionOnLeetcode1();
        int re = s.maxScoreSightseeingPair(A);
        System.out.println(re);
    }
}
