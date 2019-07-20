package leetcode_solutions.str;

import org.apache.commons.lang3.text.StrBuilder;
import org.junit.Test;

public class LongestCommonPrefix14 {
    class Solution {
        public String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length < 1) return "";
            StringBuilder sb = new StringBuilder();
            int i = 0;
            loopout:
            for (; i < strs[0].length(); i++) {
                char c = strs[0].charAt(i);
                for (int j = 1; j < strs.length; j++) {
                    if (i > strs[j].length() - 1) {
                        break loopout;
                    }
                    if (strs[j].charAt(i) != c) break loopout;
                }
                sb.append(c);
            }
            return sb.toString();
        }
    }

    class SolutionOnLeetcode1 {
        public String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0) {
                return "";
            }
            String prefix = strs[0];
            for (int i = 1; i < strs.length; i++) {
                while (!strs[i].startsWith(prefix)) {
                    prefix = prefix.substring(0, prefix.length() - 1);
                    if (prefix.length() == 0) {
                        return "";
                    }
                }
            }
            return prefix;
        }
    }

    @Test
    public void test() {
        System.out.println(System.nanoTime());
        String[] strs = new String[]{"ab", "ac"};
        Solution solution = new Solution();
        String re = solution.longestCommonPrefix(strs);
        System.out.println(re);
        System.out.println(System.nanoTime());
//        System.out.println('a' & 'b');
    }
}
