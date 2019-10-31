package leetcode_solutions;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

public class ScoreofParentheses856 {
    class Solution {
        public int scoreOfParentheses(String S) {
            if (S.length() == 2) return 1;
            if (S.startsWith("((")) {
                int count = 0;
                int l = 0;
                for (; l < S.length(); l++) {
                    if (S.charAt(l) == '(') count++;
                    else {
                        count--;
                    }
                    if (count == 0) break;
                }
                return 2 * scoreOfParentheses(S.substring(1, l)) + scoreOfParentheses(S.substring(l + 1, S.length()));
            } else if (S.startsWith("()")) {
                return 1 + scoreOfParentheses(S.substring(2, S.length()));
            }
            return 0;
        }
    }

    class Solution1 {
        public int scoreOfParentheses(String S) {
            Deque<Integer> stack = new ArrayDeque<>();
            int cur = 0;
            for (char c : S.toCharArray()) {
                if (c == '(') {
                    stack.push(cur);
                    cur = 0;
                } else {
                    cur = stack.pop() + Math.max(cur * 2, 1);
                }
            }
            return cur;
        }
    }

    class Solution2 {
        /**
         * 下标
         */
        private int index;

        public int scoreOfParentheses(String S) {
            int number = 0;
            for (; index < S.length(); ) {
                if (S.charAt(index) == '(') {
                    index++;
                    number = number + this.scoreOfParentheses(S);
                } else {
                    index++;
                    return number > 0 ? 2 * number : 1;
                }
            }
            return number;
        }
    }


    @Test
    public void test() {
//        String s = "(())()";//"(()(()))"
        String s = "((())())";
        int re = new Solution().scoreOfParentheses(s);
        System.out.println(re);
    }
}
