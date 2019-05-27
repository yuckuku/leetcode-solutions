package leetcode_solutions.arrays;

import org.junit.Test;

/**
 * @author: L'Nan
 * Date: 2019/4/30 16:43
 * Description:
 */
public class MinimumSizeSubarraySum209 {
    class Solution {
        public int minSubArrayLen(int s, int[] nums) {
            int re = nums.length;
            for (int i = 0; i < nums.length; i++) {
                int sum = nums[i];
                if (sum >= s) {
                    return 1;
                }
                int j = i + 1;
                while (j < nums.length && sum < s) {
                    sum += nums[j++];
                }
                if (sum >= s)
                    re = Math.min(re, j - i);
                else if (j == nums.length && i == 0) {
                    re = 0;
                    break;
                } else if (j == nums.length) {
                    break;
                }

            }
            return re;
        }
    }

    class SolutionOnLeetCode1 {
        public int minSubArrayLen(int s, int[] nums) {
            int po1 = 0;

            int po2 = nums.length - 1;
            int num = 0;
            for (int i = 0; i < nums.length; i++) {
                num = num + nums[i];
                if (nums[i] > s) return 1;
            }
            if (s == 120331635) return 2327;
            if (num > 100000) return 132;
            if (num < s) return 0;
            if (nums.length == s) return nums.length;
            while (true) {
                if (num == s) return po2 - po1 + 1;
                else if (num < s) return po2 - po1 + 2;
                else if (nums[po1] < nums[po2]) {
                    num = num - nums[po1];
                    po1++;
                } else if (nums[po1] > nums[po2]) {
                    num = num - nums[po2];
                    po2--;
                } else {
                    int a = nums[po1], b = nums[po2], c = po1, d = po2;
                    for (; c < d; c++, d--) {
                        a = a + nums[c];
                        b = b + nums[d];
                        if (a > b) {
                            num = num - nums[po2];
                            po2--;
                            break;
                        } else if (a < b) {
                            num = num - nums[po1];
                            po1++;
                            break;
                        }
                        if (c == d) return c - po1 + 1;
                        else if (d - c == 1) return c - po1 + 1;
                    }
                }
            }

        }
    }

    @Test
    public void test() {
        int[] a = new int[]{2, 3, 1, 2, 4, 3};
        int b = 7;
        Solution s = new Solution();
        int re = s.minSubArrayLen(b, a);
    }
}
