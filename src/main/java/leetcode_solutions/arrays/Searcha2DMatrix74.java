package leetcode_solutions.arrays;

import org.junit.Test;

/**
 * @author: L'Nan
 * Date: 2019/5/22 13:47
 * Description:
 */
public class Searcha2DMatrix74 {
    class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {
            if (null == matrix || matrix.length == 0) return false;
            int m = matrix.length;
            int n = matrix[0].length;
            if (m == 1) return searchArray(matrix[0], target);
            int l = 0, r = m - 1;
            int mid = (l + r) >> 1;
            while (l + 1 < r) {
                if (matrix[mid][n - 1] < target && matrix[mid + 1][n - 1] >= target) {
                    break;
                }
                if (matrix[mid + 1][n - 1] < target) {
                    l = mid;
                    mid = (l + r) >> 1;
                    continue;
                }
                if (matrix[mid][n - 1] >= target) {
                    r = mid;
                    mid = (l + r) >> 1;
                    continue;
                }
            }
            if (matrix[l][n - 1] >= target && matrix[l][0] <= target) return searchArray(matrix[l], target);
            if (matrix[r][n - 1] >= target && matrix[r][0] <= target) return searchArray(matrix[r], target);
            if (matrix[mid + 1][n - 1] >= target && matrix[mid + 1][0] <= target)
                return searchArray(matrix[mid + 1], target);
            return false;
        }

        private boolean searchArray(int[] arr, int target) {
            if (arr == null || arr.length == 0) return false;
            int len = arr.length;
            int l = 0, r = len - 1;
            int mid = (l + r) >> 1;
            while (l + 1 < r) {
                if (arr[mid] == target) return true;
                if (arr[mid] > target) {
                    r = mid;
                    mid = (l + r) >> 1;
                    continue;
                }
                if (arr[mid] < target) {
                    l = mid;
                    mid = (l + r) >> 1;
                    continue;
                }
            }
            if (target == arr[l] || target == arr[r]) return true;
            return false;
        }
    }

    class SolutionOnLeetcode1 {
        public boolean searchMatrix(int[][] matrix, int target) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return false;
            }
            int i = 0;
            while (i < matrix.length && matrix[i][0] <= target) {
                i++;
            }
            if (i == 0) {
                return false;
            }
            int result = search(matrix[i - 1], 0, matrix[i - 1].length - 1, target);
            return result != -1;
        }

        private int search(int[] nums, int lo, int hi, int target) {
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                if (target > nums[mid]) {
                    lo = mid + 1;
                } else if (target < nums[mid]) {
                    hi = mid - 1;
                } else {
                    return mid;
                }
            }
            return -1;
        }
    }

    @Test
    public void test() {
        int[][] matrix = new int[][]{
                {1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}
        };
        int target = 13;
        Solution s = new Solution();
        boolean re = s.searchMatrix(matrix, target);
        System.out.println(re);
    }
}
