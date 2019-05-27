package leetcode_solutions.arrays;

import org.junit.Test;

/**
 * @author: L'Nan
 * Date: 2019/5/21 14:55
 * Description:
 */
public class JumpGame55 {
    class Solution {
        public boolean canJump(int[] nums) {

            int len = nums.length;
            int[] reach = new int[len];
            reach[0] = 1;
            int reached = 0;
            for (int i = 0; i < len; i++) {
                if (reach[i] == 0) break;
                int right = nums[i] + i;
                if (right >= len - 1) {
                    return true;
                }
                for (int j = reached + 1; j <= right; j++) {
                    if (reach[j] == 0) reach[j] = 1;
                }
                reached = Math.max(reached, right);
            }
            return reach[len - 1] == 1 ? true : false;
        }
    }

    class SolutionOnLeetcode1 {
        public boolean canJump(int[] nums) {
            int step = 1;
            for (int i = nums.length - 2; i >= 0; i--) {
                step = nums[i] >= step ? 1 : step + 1;
            }
            return step == 1;
        }
    }

    @Test
    public void test() {
        int[] nums = new int[]{3, 2, 1, 0, 4};
        Solution s = new Solution();
        boolean re = s.canJump(nums);
        System.out.println(re);
    }
}
