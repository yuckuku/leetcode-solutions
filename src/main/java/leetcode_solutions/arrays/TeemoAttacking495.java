package leetcode_solutions.arrays;

/**
 * @author: L'Nan
 * Date: 2019/4/27 21:46
 * Description:
 */
public class TeemoAttacking495 {
    class Solution {
        public int findPoisonedDuration(int[] timeSeries, int duration) {
            int re = 0;
            if (null == timeSeries || timeSeries.length == 0)
                return re;
            for (int i = 0; i < timeSeries.length - 1; i++) {
                re += Math.min(duration, timeSeries[i + 1] - timeSeries[i]);
            }
            re += duration;
            return re;
        }
    }

    class SolutionOnLeetCode1 {
        public int findPoisonedDuration(int[] timeSeries, int duration) {
            if (timeSeries.length == 0) {
                return 0;
            }
            int res = duration;
            for (int i = 1; i < timeSeries.length; i++) {
                res += timeSeries[i] - timeSeries[i - 1] > duration ? duration : timeSeries[i] - timeSeries[i - 1];

            }
            return res;
        }
    }
}
