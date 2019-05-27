package leetcode_solutions.arrays;

/**
 * @author: L'Nan
 * Date: 2019/4/28 17:48
 * Description:
 */
public class MaximumSumofTwoNonOverlappingSubarrays1031 {
    class Solution {
        public int maxSumTwoNoOverlap(int[] A, int L, int M) {
            int re = 0;
            int len = A.length;

            //L left, M right
            int i = 0, j = L - 1;
            while (j < len - M) {
                int temp = 0;
                for (int k = i; k < j + 1; k++) {
                    temp += A[k];
                }

                int m = j + 1, n = M + j;
                while (n < len) {
                    int temp2 = 0;
                    for (int k = m; k < n + 1; k++) {
                        temp2 += A[k];
                    }
                    re = Math.max(re, temp + temp2);
                    m++;
                    n++;
                }
                i++;
                j++;
            }

            //M left, L right
            i = 0;
            j = M - 1;
            while (j < len - L) {
                int temp = 0;
                for (int k = i; k < j + 1; k++) {
                    temp += A[k];
                }

                int m = j + 1, n = L + j;
                while (n < len) {
                    int temp2 = 0;
                    for (int k = m; k < n + 1; k++) {
                        temp2 += A[k];
                    }
                    re = Math.max(re, temp + temp2);
                    m++;
                    n++;
                }
                i++;
                j++;
            }

            return re;
        }

        class SolutionOnLeetCode1 {
            public int maxSumTwoNoOverlap(int[] a, int L, int M) {

                int res = Integer.MIN_VALUE;
                int len = a.length;
                int[] nums = new int[len + 1];

                for (int i = 0; i < len; i++) {
                    nums[i + 1] = nums[i] + a[i];
                }

                int max = Integer.MIN_VALUE;
                for (int i = 0; i <= len; i++) {
                    if (i >= L + M) {
                        max = Math.max(max, nums[i - M] - nums[i - M - L]);
                        res = Math.max(res, max + nums[i] - nums[i - M]);
                    }
                }

                max = Integer.MIN_VALUE;
                for (int i = 0; i <= len; i++) {
                    if (i >= L + M) {
                        max = Math.max(max, nums[i - L] - nums[i - M - L]);
                        res = Math.max(res, max + nums[i] - nums[i - L]);
                    }
                }

                return res;
            }
        }
    }
}
