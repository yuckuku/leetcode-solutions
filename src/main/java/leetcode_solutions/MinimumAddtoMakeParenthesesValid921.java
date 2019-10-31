package leetcode_solutions;

import java.util.LinkedList;

public class MinimumAddtoMakeParenthesesValid921 {
    class Solution {
        public int minAddToMakeValid(String S) {
            LinkedList<Character> queue = new LinkedList<>();
            char[] chs = S.toCharArray();
            int re = 0;
            for (char ch : chs) {
                if (ch == '(') {
                    queue.offer(ch);
                } else if (queue.isEmpty()) {
                    re++;
                } else {
                    queue.poll();
                }
            }
            return re + queue.size();
        }
    }

    class Solution1 {
        public int minAddToMakeValid(String S) {

            // 方式一 采用stack,效果不太好
            // if(S == null || S.length() == 0) return 0;
            // Stack<Character> stack = new Stack<>();
            // int res = 0;
            // for(char c: S.toCharArray()){
            //     if(c == '('){
            //         stack.push(c);
            //     }
            //     if(c== ')'){
            //         if(!stack.isEmpty()){
            //             stack.pop();
            //         }
            //         else{
            //             res++;
            //         }
            //     }
            // }
            // return res+stack.size();

            //方式二
            if (S == null || S.length() == 0) return 0;
            int left = 0, right = 0;
            for (char c : S.toCharArray()) {
                if (c == '(') {
                    left++;
                }
                if (c == ')') {
                    if (left > 0) {
                        left--;
                    } else {
                        right++;
                    }
                }
            }
            return left + right;
        }
    }

}
