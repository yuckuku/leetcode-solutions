package leetcode_solutions.arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: L'Nan
 * Date: 2019/6/21 10:32
 * Description:
 */
public class NumberofSubmatricesThatSumtoTarget1074 {
    class Solution {
        public int numSubmatrixSumTarget(int[][] matrix, int target) {
            int m = matrix.length, n = matrix[0].length, res = 0;
            for (int l = 0; l < n; l++) {
                int[] rowsum = new int[m];
                for (int r = l; r < n; r++) {
                    int sum = 0;
                    Map<Integer, Integer> hash = new HashMap<>();
                    hash.put(0, 1);
                    for (int i = 0; i < m; i++) {
                        rowsum[i] += matrix[i][r];
                        sum += rowsum[i];
                        if (hash.containsKey(sum - target)) {
                            res += hash.get(sum - target);
                        }
                        hash.put(sum, hash.getOrDefault(sum, 0) + 1);
                    }
                }
            }
            return res;
        }
    }

    class SolutionOnLeetcode1 {
        public int numSubmatrixSumTarget(int[][] matrix, int target) {
            int m = matrix.length;
            int n = matrix[0].length;
            int ans = 0;
            for (int i = 0; i < n; i++) {
                int[] nums = new int[m];
                for (int j = i; j < n; j++) {
                    for (int k = 0; k < m; k++)
                        nums[k] += matrix[k][j];
                    ans += twoSum(nums, target);
                }
            }
            return ans;
        }

        private int twoSum(int[] nums, int target) {
            Map<Integer, Integer> count = new HashMap<>();
            int sum = 0;
            int n = nums.length;
            int ans = 0;
            count.put(0, 1);
            for (int i = 0; i < n; i++) {
                sum += nums[i];
                ans += count.getOrDefault(sum - target, 0);
                count.put(sum, count.getOrDefault(sum, 0) + 1);
            }
            return ans;
        }
    }
}
