package leetcode_solutions;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

public class CheckIfWordIsValidAfterSubstitutions1003 {
    class Solution {
        private final Deque<String> stack = new ArrayDeque<>();

        public boolean isValid(String S) {
            if (S.equals("abc") || S.equals("")) return true;
            if (S.startsWith("abc")) {
                return isValid(S.substring(3, S.length()));
            } else if (S.startsWith("ab")) {
                stack.push("ab");
                return isValid(S.substring(2, S.length()));
            } else if (S.startsWith("a")) {
                stack.push("a");
                return isValid(S.substring(1, S.length()));
            } else if (S.startsWith("c")) {
                if (!stack.isEmpty() && (stack.pop() + "c").equals("abc"))
                    return isValid(S.substring(1, S.length()));
                else return false;
            } else if (S.startsWith("bc")) {
                if (!stack.isEmpty() && (stack.pop() + "bc").equals("abc"))
                    return isValid(S.substring(2, S.length()));
                else return false;
            }
            return false;
        }

        public boolean isValid1(String S) {
            if (S.equals("")) return true;
            int i = S.indexOf("abc");
            if (i != -1) {
                return isValid(S.substring(0, i) + S.substring(i + 3, S.length()));
            } else {
                return false;
            }
            /*char[] chs = S.toCharArray();
            int a = 0, b = 0, c = 0;
            for (int i = 0; i < chs.length; i++) {
                if (chs[i] == 'a')
                    a++;
                else if (chs[i] == 'b')
                    b++;
                else
                    c++;
                if (a - b < 0 || b - c < 0) return false;
            }
            if (a != b || b != c) return false;
            return true;*/
        }
    }

    class Solution1 {
        public boolean isValid(String S) {
            if (S == null || S.length() < 3 || !S.startsWith("a")) return false;
            char pre = '0';
            int flag = 0;
            for (char s : S.toCharArray()) {
                switch (s) {
                    case 'a':
                        flag++;
                        break;
                    case 'b':
                        if (pre == 'b') {
                            return false;
                        }
                        break;
                    case 'c':
                        if (pre == 'a') {
                            return false;
                        }
                        flag--;
                        if (flag < 0) {
                            return false;
                        }
                        break;
                }
                pre = s;
            }
            return flag == 0;
        }
    }

    @Test
    public void test() {
//        String s = "abcabcababcc";
//        String s = "abccba";
        String s = "aabcbabcc";//true
//        String s = "aabbcc";//false
        boolean re = new Solution().isValid1(s);
        System.out.println(re);
    }
}
