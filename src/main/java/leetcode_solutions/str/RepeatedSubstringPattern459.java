package leetcode_solutions.str;

import org.junit.Test;

import java.util.Arrays;

public class RepeatedSubstringPattern459 {
    class Solution {
        //the given string consists of lowercase English letters only and its length will not exceed 10000.
        public boolean repeatedSubstringPattern(String s) {
            //TLE
            /*for (int i = 0; i < s.length(); i++) {
                String[] a = s.split(s.substring(0, i));
                if (a.length == 0) return true;
            }
            return false;*/

            int len = s.length();
            for (int i = 1; i <= len / 2; i++) {
                if (len % i == 0) {
                    String str = s.substring(0, i);
                    String[] a = s.split(str);
                    if (a.length == 0) return true;
                }
            }
            return false;
        }
    }

    class SolutionOnLeetcode1 {
        public boolean repeatedSubstringPattern(String s) {
            int len = s.length();
            if (len == 0 || len == 1)
                return false;
            char lastc = s.charAt(len - 1);
            int l = s.lastIndexOf(lastc, len / 2 - 1) + 1;
            for (; l > 0; l = s.lastIndexOf(lastc, l - 2) + 1) {
                if (len % l == 0) {
                    String p = s.substring(0, l);
                    boolean res = true;
                    for (int i = l; i < len; i += l) {
                        if (!s.substring(i, i + l).equals(p)) {
                            res = false;
                            break;
                        }
                    }
                    if (res)
                        return true;
                }
            }
            return false;
        }
    }

    @Test
    public void test() {
        String s = "abababababab";
        SolutionOnLeetcode1 solution = new SolutionOnLeetcode1();
        boolean re = solution.repeatedSubstringPattern(s);
        System.out.println(re);
//        String[] a = "aba".split("ab");
//        System.out.println(a.length);
//        System.out.println(Arrays.toString(a));
    }
}
