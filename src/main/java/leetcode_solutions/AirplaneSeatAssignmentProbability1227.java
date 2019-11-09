package leetcode_solutions;

import org.junit.Test;

public class AirplaneSeatAssignmentProbability1227 {

    class Solution {
        public double nthPersonGetsNthSeat(int n) {
            if (n == 1)
                return 1;
            else {
                return 0.5;
            }
        }
    }

    @Test
    public void test() {
        int n = 4;
        Solution solution = new Solution();
        double re = solution.nthPersonGetsNthSeat(n);
        System.out.println(re);
        System.out.println(3.0 / 4.0);
    }
}
