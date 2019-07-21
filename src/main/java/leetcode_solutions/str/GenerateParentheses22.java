package leetcode_solutions.str;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses22 {
    class Solution {
        public List<String> generateParenthesis(int n) {
            List<String> re = new ArrayList<>();
            char[] chs = new char[n * 2];
            f(re, chs, 0, 0, n);
            return re;
        }

        private void f(List<String> list, char[] chs, int left, int right, int n) {
            if (left + right == n * 2) {
                list.add(new String(chs));
            } else {
                int i = left - right;
                int index = left + right;
                if (i > 0 && left < n) {
                    chs[index] = '(';
                    f(list, chs, left + 1, right, n);
                    chs[index] = ')';
                    f(list, chs, left, right + 1, n);
                } else if (i > 0 && right < n) {
                    chs[index] = ')';
                    f(list, chs, left, right + 1, n);
                } else {
                    chs[index] = '(';
                    f(list, chs, left + 1, right, n);
                }
            }
        }
    }

    class Solution1 {
        public List<String> generateParenthesis(int n) {
            List<String> res = new ArrayList<>();
            backtrack(res, "", 0, 0, n);
            return res;
        }

        public void backtrack(List<String> res, String cur, int open, int close, int n) {
            if (cur.length() == 2 * n) {
                res.add(cur);
                return;
            }
            if (open < n) {
                backtrack(res, cur + "(", open + 1, close, n);
            }
            if (close < open) {
                backtrack(res, cur + ")", open, close + 1, n);
            }
        }
    }


    @Test
    public void test() {
        int n = 3;
        Solution solution = new Solution();
        List<String> re = solution.generateParenthesis(n);
        System.out.println(re);
    }
}
