package leetcode_solutions.arrays;

import org.junit.Test;

/**
 * @author: L'Nan
 * Date: 2019/6/21 16:25
 * Description:
 */
public class JumpGameII45 {
    class Solution {
        public int jump(int[] nums) {
            int len = nums.length;
            if (len <= 1) return 0;
            int[] jump = new int[len];
            int index = 1;
            for (int i = 0; i < len - 1; i++) {
                int toIndex = Math.min(i + nums[i], len - 1);
                if (index > toIndex) continue;
                else {
                    for (int j = index; j <= toIndex; j++) {
                        jump[j] = i;
                    }
                    index = toIndex + 1;
                }
                if (index >= len) break;
            }
            int re = 1;
            index = jump[len - 1];
            while (index != 0) {
                index = jump[index];
                re++;
            }
            return re;
        }
    }

    class SolutionOnLeetcode1 {
        public int jump(int[] nums) {
            if (nums == null || nums.length == 0 || nums.length == 1) {
                return 0;
            }
            int n = nums.length;

            int step = 0, curIndex = 0, i = 0, nextIndex = 0;

            while (curIndex - i + 1 > 0) {
                step++;
                for (; i <= curIndex; i++) {
                    nextIndex = Math.max(nextIndex, nums[i] + i);
                    if (nextIndex >= n - 1) return step;
                }
                curIndex = nextIndex;
            }
            return 0;
        }
    }

    @Test
    public void test() {
        int[] nums = new int[]{2, 3, 1, 1, 4};
        Solution s = new Solution();
        int re = s.jump(nums);
        System.out.println(re);
    }

}
