package leetcode_solutions;

import org.junit.Test;

public class DecodeWays91 {
    class Solution {
        public int numDecodings(String s) {
            if (s == null || s.length() == 0) return 1;
            else if (s.length() == 1) {
                int i = Integer.valueOf(s);
                if (i >= 1 && i <= 9)
                    return 1;
                else return 0;
            } else if (s.length() > 1) {
                int i = Integer.valueOf(s.substring(0, 2));
                if (i >= 10 && i <= 26) return numDecodings(s.substring(1)) + numDecodings(s.substring(2));
                else return numDecodings(s.substring(1));
            }
            return 0;
        }
    }

    @Test
    public void test() {
        System.out.println((int) '1');
        System.out.println("--------------");
        String s = "12";
        Solution solution = new Solution();
        int re = solution.numDecodings(s);
        System.out.println(re);
        System.out.println("----------");
        System.out.println(s.substring(1));
        System.out.println(s.substring(2));
    }
}
