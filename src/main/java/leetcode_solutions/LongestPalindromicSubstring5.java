package leetcode_solutions;

import org.junit.Test;

public class LongestPalindromicSubstring5 {
    class Solution {
        public String longestPalindrome(String s) {
            if (s.length() <= 1) return s;
            String re = s.substring(0, 1);
            int max = 1;
            for (int i = 0; i < s.length(); i++) {
                //以i为中心
                int j = i - 1, k = i + 1;
                while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
                    j--;
                    k++;
                }
                j++;
                k--;
                if (k - j + 1 > max) {
                    re = s.substring(j, k + 1);
                    max = k - j + 1;
                }
                //以i和i+1为中心
                j = i;
                k = i + 1;
                while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
                    j--;
                    k++;
                }
                j++;
                k--;
                if (k - j + 1 > max) {
                    re = s.substring(j, k + 1);
                    max = k - j + 1;
                }
            }
            return re;
        }
    }

    @Test
    public void test() {
        String s = "bbb";
        Solution solution = new Solution();
        String re = solution.longestPalindrome(s);
        System.out.println(re);
    }
}
