package leetcode_solutions.str;

import org.junit.Test;

import java.util.Arrays;

public class NumberofSegmentsinaString434 {
    class Solution {
        public int countSegments(String s) {
            int re = 0;
            boolean tmp = true;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ' ') {
                    tmp = true;
                    continue;
                } else if (tmp) {
                    re++;
                    tmp = false;
                }
            }
            return re;
        }
    }

    class SolutionOnLeetcode1 {
        public int countSegments(String s) {
            int count = 0;
            boolean isNew = true;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != ' ' && isNew) {
                    count++;
                    isNew = false;
                }
                if (s.charAt(i) == ' ' && !isNew) {
                    isNew = true;
                }
            }
            return count;
        }
    }

    @Test
    public void test() {
        String s = "    foo    bar";
        Solution solution = new Solution();
        int re = solution.countSegments(s);
        System.out.println(re);

    }


}
