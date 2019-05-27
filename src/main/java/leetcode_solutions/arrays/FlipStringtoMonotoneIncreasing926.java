package leetcode_solutions.arrays;

import org.junit.Test;

/**
 * @author: L'Nan
 * Date: 2019/5/8 22:38
 * Description:
 */
public class FlipStringtoMonotoneIncreasing926 {
    class Solution {
        public int minFlipsMonoIncr(String S) {
            //all zeros
            int zeros = 0;
            int ones = 0;
            for (int i = 0; i < S.length(); i++) {
                if (S.charAt(i) == '0')
                    zeros++;
                else ones++;
            }

            int re = Math.min(zeros, ones);

            int zeroNum = 0;
            int oneNum = 0;
            if (S.charAt(0) == '1') oneNum++;
            else zeroNum++;
            for (int i = 1; i < S.length(); i++) {
                if (S.charAt(i) == '1' && S.charAt(i - 1) == '0') {
                    re = Math.min(re, zeros - zeroNum + oneNum);
                }
                if (S.charAt(i) == '1') oneNum++;
                else zeroNum++;
            }

            return re;
        }
    }

    class SolutionOnLeetCode1 {
        public int minFlipsMonoIncr(String S) {
            char[] str = S.toCharArray();
            int length = S.length();
            int before = 0;
            int after = 0;
            int sum = 20000;
            int[] afters = new int[length + 1];
            for (int i = 0; i < length + 1; i++) {
                if (i != 0 && str[length - i] == '0') {
                    after++;
                }
                afters[length - i] = after;
            }
            for (int i = 0; i < length + 1; i++) {
                if (i != 0 && str[i - 1] == '1') {
                    before++;
                }
                if (sum > before + afters[i]) {
                    sum = before + afters[i];
                }
            }
            return sum;
        }
    }

    @Test
    public void test() {
        String S = "010110";
        Solution s = new Solution();
        int re = s.minFlipsMonoIncr(S);
        System.out.println(re);
    }
}
