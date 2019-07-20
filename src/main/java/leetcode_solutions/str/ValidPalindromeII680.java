package leetcode_solutions.str;

import org.junit.Test;

public class ValidPalindromeII680 {
    class Solution {
        public boolean validPalindrome(String s) {
            if (s.equals(new StringBuilder(s).reverse().toString())) return true;
            int l = 0, r = s.length() - 1;
            while (l <= r) {
                if (s.charAt(l) != s.charAt(r)) break;
                else {
                    l++;
                    r--;
                }
            }
            //remove l
            String tmp = s.substring(0, l) + s.substring(l + 1, s.length());
            if (tmp.equals(new StringBuilder(tmp).reverse().toString())) return true;
            tmp = s.substring(0, r) + s.substring(r + 1, s.length());
            if (tmp.equals(new StringBuilder(tmp).reverse().toString())) return true;
            return false;
        }
    }

    class SolutionOnLeetcode1 {
        public boolean validPalindrome(String s) {
            char[] sChars = s.toCharArray();
            int count = getDifferentNum(sChars, 0, 0, sChars.length - 1);
            return count <= 1;
        }

        private int getDifferentNum(char[] sChars, int count, int head, int last) {
            if (count > 1) {
                return count;
            }
            while (head < last) {
                if (sChars[head] != sChars[last]) {
                    return Math.min(getDifferentNum(sChars, count + 1, head + 1, last), getDifferentNum(sChars, count + 1, head, last - 1));
                }
                head++;
                last--;
            }
            return 0;
        }
    }

    @Test
    public void test() {
        String s = "abca";
        Solution solution = new Solution();
        boolean re = solution.validPalindrome(s);
        System.out.println(re);
    }

}
