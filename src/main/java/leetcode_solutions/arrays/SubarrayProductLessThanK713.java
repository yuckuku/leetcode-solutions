package leetcode_solutions.arrays;

import org.junit.Test;

/**
 * @author: L'Nan
 * Date: 2019/5/28 15:09
 * Description:
 */
public class SubarrayProductLessThanK713 {
    class Solution {
        public int numSubarrayProductLessThanK(int[] nums, int k) {
            if (k <= 1) return 0;
            int res = 0, prod = 1, left = 0;
            for (int i = 0; i < nums.length; ++i) {
                prod *= nums[i];
                while (prod >= k) prod /= nums[left++];
                res += i - left + 1;
            }
            return res;
        }
    }

    @Test
    public void test() {
        int[] nums = new int[]{10, 5, 2, 6};
        int k = 100;
        Solution s = new Solution();
        int re = s.numSubarrayProductLessThanK(nums, k);
        System.out.println(re);
    }
}
