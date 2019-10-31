package leetcode_solutions.str;

import org.junit.Test;

public class PalindromicSubstrings647 {
    class Solution {

        public int countSubstrings(String s) {
            char[] chs = s.toCharArray();
            int re = 0;
            for (int i = 0; i < chs.length; i++) {
                re += count(chs, i);
            }
            return re;
        }

        private int count(char[] chs, int i) {
            int re = 0;
            for (int j = 0; i - j >= 0 && i + j < chs.length; j++) {
                if (chs[i - j] == chs[i + j]) re++;
                else break;
            }
            if (i + 1 < chs.length && chs[i] == chs[i + 1]) {
                for (int j = 0; i - j >= 0 && i + j + 1 < chs.length; j++) {
                    if (chs[i - j] == chs[i + j + 1]) re++;
                    else break;
                }
            }
            return re;
        }
    }

    class Solution1 {
        public int countSubstrings(String ss) {
            char[] s = ss.toCharArray();
            int n = s.length;
            int ans = 0;
            for(int i = 0; i < n; ++i)
                for(int j = 0; j <= i && i + j < n && s[i - j] == s[i + j]; ++j)
                    ++ans;
            for(int i = 1; i < n; ++i) {
                int l = i - 1;
                int r = i;
                for(; l >= 0 && r < n && s[l] == s[r]; --l, ++r)
                    ++ans;
            }
            return ans;
        }
    }

    @Test
    public void test() {
        String s = "leetcode";
        Solution solution = new Solution();
        int re = solution.countSubstrings(s);
        System.out.println(re);
    }
}
