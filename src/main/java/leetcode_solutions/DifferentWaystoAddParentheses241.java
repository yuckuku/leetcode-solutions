package leetcode_solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DifferentWaystoAddParentheses241 {
    class Solution {
        public List<Integer> diffWaysToCompute(String input) {
            List<Integer> re = f(input);
            return re;
        }

        private List<Integer> f(String input) {
            List<Integer> re = new ArrayList<>();
            if (input.matches("^[0-9]*$")) {
                int result = Integer.valueOf(input);
                re.add(result);
                return re;
            } else if (input.matches("^[0-9]*[\\-\\+\\*][0-9]*$")) {
                int result = compute(input);
                re.add(result);
                return re;
            } else {
                char[] chs = input.toCharArray();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < chs.length; i++) {
                    if (chs[i] > '9' || chs[i] < '0') {
                        List<Integer> l1 = f(input.substring(0, i));
                        List<Integer> l2 = f(input.substring(i + 1, input.length()));
                        for (int i1 = 0; i1 < l1.size(); i1++) {
                            for (int i2 = 0; i2 < l2.size(); i2++) {
                                if (input.charAt(i) == '+') {
                                    re.add(l1.get(i1) + l2.get(i2));
                                } else if (input.charAt(i) == '-') {
                                    re.add(l1.get(i1) - l2.get(i2));
                                } else if (input.charAt(i) == '*') {
                                    re.add(l1.get(i1) * l2.get(i2));
                                }
                            }
                        }
                    }
                }
            }
            return re;
        }

        private int compute(String one) {
            int i = 0;
            char[] chs = one.toCharArray();
            for (; i < chs.length; i++) {
                if (chs[i] > '9' || chs[i] < '0') break;
            }
            int a = Integer.valueOf(new String(chs, 0, i));
            int b = Integer.valueOf(new String(chs, i + 1, chs.length - 1 - i));
            char op = chs[i];
            int re = 0;
            switch (op) {
                case '+':
                    re = a + b;
                    break;
                case '-':
                    re = a - b;
                    break;
                case '*':
                    re = a * b;
                    break;
                default:
                    break;
            }
            return re;
        }
    }

    class Solutionn {

        public List<Integer> diffWaysToCompute(String input) {
            return partition(input);
        }

        public List<Integer> partition(String input) {
            List<Integer> result = new ArrayList<>();
            if (!input.contains("+") && !input.contains("-") && !input.contains("*")) {
                result.add(Integer.valueOf(input));
                return result;
            }
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*') {
                    for (Integer left : partition(input.substring(0, i))) {
                        for (Integer right : partition(input.substring(i + 1))) {
                            if (input.charAt(i) == '+') {
                                result.add(left + right);
                            } else if (input.charAt(i) == '-') {
                                result.add(left - right);
                            } else if (input.charAt(i) == '*') {
                                result.add(left * right);
                            }
                        }
                    }
                }
            }
            return result;
        }
    }

    class Solution1 {
        HashMap<String, List<Integer>> memo = new HashMap<String, List<Integer>>();

        public List<Integer> diffWaysToCompute(String input) {
            if (memo.containsKey(input))
                return memo.get(input);

            List<Integer> res = new ArrayList<Integer>();

            for (int i = 0; i < input.length(); ++i) {
                if (input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*') {
                    List<Integer> left = diffWaysToCompute(input.substring(0, i));
                    List<Integer> right = diffWaysToCompute(input.substring(i + 1));
                    for (int j = 0; j < left.size(); ++j) {
                        for (int k = 0; k < right.size(); ++k) {
                            if (input.charAt(i) == '+')
                                res.add(left.get(j) + right.get(k));
                            else if (input.charAt(i) == '-')
                                res.add(left.get(j) - right.get(k));
                            else
                                res.add(left.get(j) * right.get(k));
                        }
                    }
                }
            }
            if (res.size() == 0)
                res.add(Integer.parseInt(input));
            memo.put(input, res);
            return res;
        }
    }


    @Test
    public void test() {
//        String s = "1+3";
//        boolean bool = s.matches("^[0-9]*[\\-\\+\\*][0-9]*$");
//        System.out.println(bool);
//        char[] chs = s.toCharArray();
//        int a = Integer.valueOf(new String(chs, 0, 1));
//        int b = Integer.valueOf(new String(chs, 1 + 1, chs.length - 1 - 1));
//        System.out.println(a);
//        System.out.println(b);
//        String s = "2-1-1";
        String s = "2*3-4*5";
        Solution solution = new Solution();
        List<Integer> re = solution.diffWaysToCompute(s);
        System.out.println(re);
    }
}
