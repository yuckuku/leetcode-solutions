package leetcode_solutions.arrays;

/**
 * @author: L'Nan
 * Date: 2019/4/11 17:04
 * Description:
 */
public class ContainerWithMostWater11 {
    class Solution {
        public int maxArea(int[] height) {
            int re = 0;
            for (int i = 0; i < height.length; i++) {
                int j = i;
                while (++j < height.length) {
                    re = Math.max(re, (j - i) * Math.min(height[i], height[j]));
                }
            }
            return re;
        }
    }

    class SolutionOnLeetCode1 {
        public int maxArea(int[] height) {
            int ans = 0;
            int keep;
            int i = 0, j = height.length - 1;
            while (i < j) {
                int tmp = Math.min(height[i], height[j]) * (j - i);
                ans = ans > tmp ? ans : tmp;
                if (height[i] <= height[j]) {
                    keep = height[i];
                    i++;
                    while (height[i] <= keep && i < j)
                        i++;
                } else {
                    keep = height[j];                        //不同的地方
                    j--;
                    while (height[j] <= keep && i < j)
                        j--;
                }

            }
            return ans;
        }
    }
}
