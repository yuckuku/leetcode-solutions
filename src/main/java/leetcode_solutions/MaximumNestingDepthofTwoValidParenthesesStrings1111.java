package leetcode_solutions;

import org.junit.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class MaximumNestingDepthofTwoValidParenthesesStrings1111 {

    class Solution {
        public int[] maxDepthAfterSplit(String seq) {
            int[] re = new int[seq.length()];

            Deque<Character> queue = new LinkedList<>();
            queue.add(seq.charAt(0));
            int i = 1;
            for (; i < seq.length(); i++) {
                if (seq.charAt(i) == ')') {
                    queue.pop();
                    if (queue.isEmpty()) {
                        break;
                    } else {
                        re[i] = 1;
                    }
                } else {
                    re[i] = 1;
                    queue.add(seq.charAt(i));
                }
            }

            i++;
            for (; i < re.length; i++) {
                re[i] = 1;
            }


            return re;
        }
    }

    class Solution1 {
        /**
         * 遍历seq，遇到(则压入栈
         * 遇到)则计算：
         * 使用stack.size()获取当前深度
         * 当前遍历stack.pop()和i 获取括号的左右下标
         * depth%2 判断负责为1 还是0
         */
        public int[] maxDepthAfterSplit(String seq) {
            if (seq == null || seq.isEmpty()) {
                return new int[0];
            }
            int[] result = new int[seq.length()];
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < seq.length(); i++) {
                if (seq.charAt(i) == '(') {
                    stack.push(i);
                } else {
                    int depth = stack.size();
                    int left = stack.pop();
                    if (depth % 2 == 0) {
                        result[left] = 1;
                        result[i] = 1;
                    }
                }
            }
            return result;
        }
    }

    class SolutionOnLeetcode1 {
        public int[] maxDepthAfterSplit(String seq) {
            int[] res = new int[seq.length()];
            for (int i = 0, cnt = 0; i < res.length; ++i) {
                if (seq.charAt(i) == '(') {
                    res[i] = cnt++ & 1;
                } else {
                    res[i] = --cnt & 1;
                }
            }
            return res;
        }
    }


    @Test
    public void test() {
        String s = "()(())()";//[0,0,0,1,1,0,1,1]
        Solution1 solution = new Solution1();
        int[] re = solution.maxDepthAfterSplit(s);
        System.out.println(Arrays.toString(re));
        System.out.println("------------");

        int cnt = 0;
        System.out.println(cnt++ & 1);
        System.out.println(cnt++ & 1);
        System.out.println(cnt++ & 1);


        System.out.println("-----------------------");
        String s1 = "(((())))";
        SolutionOnLeetcode1 solutionOnLeetcode1 = new SolutionOnLeetcode1();
        int[] re1 = solutionOnLeetcode1.maxDepthAfterSplit(s1);

        System.out.println(Arrays.toString(re1));
        System.out.println(Arrays.toString(solution.maxDepthAfterSplit(s1)));
    }

}
