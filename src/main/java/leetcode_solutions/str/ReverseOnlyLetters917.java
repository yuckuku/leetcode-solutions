package leetcode_solutions.str;

import org.junit.Test;

import java.util.Arrays;

public class ReverseOnlyLetters917 {
    //S.length <= 100
    //33 <= S[i].ASCIIcode <= 122
    //S doesn't contain \ or "
    class Solution {
        public String reverseOnlyLetters(String S) {
            char[] chs = S.toCharArray();
            int l = 0, r = chs.length - 1;
            while (l < r) {
                l = locateL(chs, l);
                r = locateR(chs, r);
                if (l < r) {
                    char tmp = chs[l];
                    chs[l] = chs[r];
                    chs[r] = tmp;
//                    System.out.println(String.valueOf(chs));
                    l++;
                    r--;
                }
            }
            return String.valueOf(chs);
        }

        private int locateL(char[] chs, int l) {
            if (l > chs.length - 1) return chs.length - 1;
            if (chs[l] >= 'a' && chs[l] <= 'z' || chs[l] >= 'A' && chs[l] <= 'Z')
                return l;
            else return locateL(chs, l + 1);
        }

        private int locateR(char[] chs, int l) {
            if (l < 0) return 0;
            if (chs[l] >= 'a' && chs[l] <= 'z' || chs[l] >= 'A' && chs[l] <= 'Z')
                return l;
            else return locateR(chs, l - 1);
        }
    }

    @Test
    public void test() {
        String s = "Test1ng-Leet=code-Q!";
        Solution solution = new Solution();
        String re = solution.reverseOnlyLetters(s);
        System.out.println(re);
    }
}
