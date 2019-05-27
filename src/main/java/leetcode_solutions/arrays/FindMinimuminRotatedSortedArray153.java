package leetcode_solutions.arrays;

import org.junit.Test;

/**
 * @author: L'Nan
 * Date: 2019/4/28 15:20
 * Description:
 */
public class FindMinimuminRotatedSortedArray153 {
    class Solution {
        public int findMin(int[] nums) {
            int i = 0, j = nums.length - 1;
            if (nums[i] < nums[j]) {
                return nums[i];
            }
            while (j - i > 1) {
                int mid = (j + i) / 2;
                if (nums[mid] > nums[j]) {
                    i = mid;
                } else {
                    j = mid;
                }
            }
            return nums[j];
        }
    }

    class SolutionOnLeetCode1 {
        public int findMin(int[] nums) {
            int l = 0, r = nums.length - 1;
            while (l < r) {
                int mid = l + r >> 1;
                if (nums[mid] <= nums[r]) r = mid;
                else l = mid + 1;
            }
            return nums[l];
        }
    }

    @Test
    public void test() {
        Solution solution = new Solution();
        int[] a = new int[]{3, 4, 5, 1, 2};
        int re = solution.findMin(a);
    }
}
