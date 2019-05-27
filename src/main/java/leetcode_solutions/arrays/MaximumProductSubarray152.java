package leetcode_solutions.arrays;

import org.junit.Test;

/**
 * @author: L'Nan
 * Date: 2019/5/22 15:52
 * Description:
 */
public class MaximumProductSubarray152 {
    class Solution {
        public int maxProduct(int[] nums) {
            if (nums.length == 1) return nums[0];
            int re = Integer.MIN_VALUE;
            int negtive = 0, positive = 0;
            int product = 1;
            int sum = 0;
            int leftP = 1, rightP = 1;
            boolean first = true;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 0) {
                    if (positive + negtive <= 1) {
                        re = Math.max(re, Math.max(sum, 0));
                    } else {
                        if (negtive % 2 == 0) re = Math.max(re, sum);
                        else {
                            int temp = Math.max(sum / leftP, sum / rightP);
                            re = Math.max(re, temp);
                        }
                    }
                    negtive = 0;
                    positive = 0;
                    product = 1;
                    sum = 0;
                    leftP = 1;
                    rightP = 1;
                    first = true;
                    continue;
                } else if (nums[i] > 0) {
                    product *= nums[i];
                    sum = product;
                    positive++;
                    if (first) leftP *= nums[i];
                    rightP *= nums[i];
                } else {
                    product *= nums[i];
                    sum = product;
                    negtive++;
                    if (first) {
                        leftP *= nums[i];
                        first = false;
                    }
                    rightP = nums[i];
                }
            }
            if (positive + negtive == 1) re = Math.max(re, sum);
            else if (positive + negtive >= 1 && negtive % 2 == 0) re = Math.max(re, sum);
            else if (positive + negtive >= 1) {
                int temp = Math.max(sum / leftP, sum / rightP);
                re = Math.max(re, temp);
            }
            return re;
        }
    }

    class SolutionOnLeetcode1 {
        public int maxProduct(int[] nums) {
            int maxNow; // 以当前位置结尾的最大值
            int minNow; // 以当前位置结尾的最小值

            int result = nums[0];

            maxNow = result;
            minNow = result;
            for (int i = 1; i < nums.length; i++) {

                if (nums[i] < 0) {
                    minNow = minNow ^ maxNow;
                    maxNow = minNow ^ maxNow;
                    minNow = minNow ^ maxNow;
                }
                maxNow = Math.max(maxNow * nums[i], nums[i]);
                minNow = Math.min(minNow * nums[i], nums[i]);

                result = Math.max(maxNow, result);
            }

            return result;
        }
    }

    @Test
    public void test() {
        int[] nums = new int[]{-3, -1, 3, 5, -6, -6, -1, 6, -3, -5, 1, 0, -6, -5, 0, -2, 6, 1, 0, 5};
        SolutionOnLeetcode1 s = new SolutionOnLeetcode1();
        int re = s.maxProduct(nums);
        System.out.println(re);
    }
}
