package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author: L'Nan
 * Date: 2019/6/16 13:41
 * Description:
 */
public class MaximumSumof3NonOverlappingSubarrays689 {
    class Solution {
        public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
            int n = nums.length, mx = Integer.MIN_VALUE;
            int[] sums = new int[n + 1];
            int[] left = new int[n];//left[i]表示在区间[0, i]范围内长度为k且和最大的子数组的起始位置
            int[] right = new int[n];//right[i]表示在区间[i, n - 1]范围内长度为k且和最大的子数组的起始位置
            right[n - k] = n - k;
            int[] res = new int[3];
            for (int i = 1; i < n + 1; i++) {
                sums[i] = sums[i - 1] + nums[i - 1];
            }
            for (int i = k, total = sums[k] - sums[0]; i < n; ++i) {
                if (sums[i + 1] - sums[i + 1 - k] > total) {
                    left[i] = i + 1 - k;
                    total = sums[i + 1] - sums[i + 1 - k];
                } else {
                    left[i] = left[i - 1];
                }
            }
            for (int i = n - 1 - k, total = sums[n] - sums[n - k]; i >= 0; --i) {
                if (sums[i + k] - sums[i] >= total) {
                    right[i] = i;
                    total = sums[i + k] - sums[i];
                } else {
                    right[i] = right[i + 1];
                }
            }
            for (int i = k; i <= n - 2 * k; ++i) {
                int l = left[i - 1], r = right[i + k];
                int total = (sums[i + k] - sums[i]) + (sums[l + k] - sums[l]) + (sums[r + k] - sums[r]);
                if (mx < total) {
                    mx = total;
                    res[0] = l;
                    res[1] = i;
                    res[2] = r;
                }
            }
            return res;
        }
    }

    class SolutionOnLeetcode1 {
        public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
            int n = nums.length, maxsum = 0;
            int[] sum = new int[n + 1], posLeft = new int[n], posRight = new int[n], ans = new int[3];
            for (int i = 0; i < n; i++) sum[i + 1] = sum[i] + nums[i];
            // DP for starting index of the left max sum interval
            for (int i = k, tot = sum[k] - sum[0]; i < n; i++) {
                if (sum[i + 1] - sum[i + 1 - k] > tot) {
                    posLeft[i] = i + 1 - k;
                    tot = sum[i + 1] - sum[i + 1 - k];
                } else
                    posLeft[i] = posLeft[i - 1];
            }
            // DP for starting index of the right max sum interval
            // caution: the condition is ">= tot" for right interval, and "> tot" for left interval
            posRight[n - k] = n - k;
            for (int i = n - k - 1, tot = sum[n] - sum[n - k]; i >= 0; i--) {
                if (sum[i + k] - sum[i] >= tot) {
                    posRight[i] = i;
                    tot = sum[i + k] - sum[i];
                } else
                    posRight[i] = posRight[i + 1];
            }
            // test all possible middle interval
            for (int i = k; i <= n - 2 * k; i++) {
                int l = posLeft[i - 1], r = posRight[i + k];
                int tot = (sum[i + k] - sum[i]) + (sum[l + k] - sum[l]) + (sum[r + k] - sum[r]);
                if (tot > maxsum) {
                    maxsum = tot;
                    ans[0] = l;
                    ans[1] = i;
                    ans[2] = r;
                }
            }
            return ans;
        }
    }

    @Test
    public void test() {
        int[] nums = new int[]{9, 8, 7, 6, 2, 2, 2, 2};
        int k = 2;
        Solution s = new Solution();
        int[] re = s.maxSumOfThreeSubarrays(nums, k);
        System.out.println(Arrays.toString(re));
    }
}
