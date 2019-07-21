package leetcode_solutions.str;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class RepeatedStringMatch686 {
    class Solution {
        public int repeatedStringMatch(String A, String B) {
            if (A.contains(B)) return 1;
            if (B.length() == 0) return 1;
            Set<Integer> set = new HashSet<>();
            for (int j = B.length(); j > 0; j--) {
                int i = A.lastIndexOf(B.substring(0, j));
                if (i != -1) set.add(i);
            }
            System.out.println(set);
            if (set.isEmpty()) return -1;
            int re = -1;
            for (int i : set) {
                int tmp = f(A, B, i);
                if (tmp != -1) {
                    re = tmp;
                    break;
                }
            }

            return re;
        }

        private int f(String A, String B, int i) {
            int re = 1;
            for (int j = 1; j < B.length(); j++) {
                i++;
                if (i > A.length() - 1) {
                    re++;
                    i -= A.length();
                }
                if (B.charAt(j) != A.charAt(i)) return -1;
            }
            return re;
        }
    }


    class SolutionOnLeetcode1 {
        public int repeatedStringMatch(String A, String B) {
            StringBuilder sb = new StringBuilder(A);
            int max = B.length() / A.length() + 2;
            int i = 1;
            while (i <= max) {
                if (sb.lastIndexOf(B) > -1) {
                    return i;
                } else {
                    i++;
                    sb.append(A);
                }
            }

            return -1;
        }
    }

    @Test
    public void test() {
        String A = "a";
        String B = "aa";
//        int index = A.indexOf(B);
//        System.out.println("index:" + index);
        Solution solution = new Solution();
        int re = solution.repeatedStringMatch(A, B);
        System.out.println(re);
    }
}
