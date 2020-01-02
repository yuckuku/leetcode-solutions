package leetcode_solutions;

import org.junit.Test;

import java.util.LinkedList;

public class DecodeString394 {
    //执行用时:1ms,在所有java提交中击败了94.01%的用户内存消耗:34.4MB,在所有java提交中击败了90.64%的用户
    class Solution {
        public String decodeString(String s) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.toCharArray().length; ) {
                if (Character.isDigit(s.charAt(i))) {
                    //number
                    int k = i;
                    while (Character.isDigit(s.charAt(k))) {
                        k++;
                    }
                    int repeat = Integer.valueOf(s.substring(i, k));
                    int j = k + 1;
                    for (int braceNum = 1; braceNum > 0; j++) {
                        if (s.charAt(j) == '[') {
                            braceNum++;
                        }
                        if (s.charAt(j) == ']') {
                            braceNum--;
                        }
                    }
                    String tmp = decodeString(s.substring(k + 1, j - 1));
                    for (int l = 0; l < repeat; l++) {
                        sb.append(tmp);
                    }
                    i = j;
                } else {
                    sb.append(s.charAt(i));
                    i++;
                }
            }

            return sb.toString();

        }


    }

    @Test
    public void test() {
//        System.out.println(Character.isDigit('0'));
        String s = "100[leetcode]";
        Solution solution = new Solution();
        String re = solution.decodeString(s);
        System.out.println(re);
    }

    class Solution0ms {
        int idx = 0;

        public String decodeString(String s) {
            StringBuilder b = new StringBuilder();
            int num = 0;
            while (idx < s.length()) {
                char ch = s.charAt(idx++);
                if (ch >= '0' && ch <= '9') num = num * 10 + ch - '0';
                else if (ch == '[') {
                    String str = decodeString(s);
                    for (int i = 0; i < num; i++) b.append(str);
                    num = 0;
                } else if (ch == ']') {
                    break;
                } else {
                    b.append(ch);
                }
            }
            return b.toString();

        }
    }

    //stack
    class Solution1 {
        public String decodeString(String s) {
            StringBuilder res = new StringBuilder();
            int multi = 0;
            LinkedList<Integer> stack_multi = new LinkedList<>();
            LinkedList<String> stack_res = new LinkedList<>();
            for (Character c : s.toCharArray()) {
                if (c == '[') {
                    stack_multi.addLast(multi);
                    stack_res.addLast(res.toString());
                    multi = 0;
                    res = new StringBuilder();
                } else if (c == ']') {
                    StringBuilder tmp = new StringBuilder();
                    int cur_multi = stack_multi.removeLast();
                    for (int i = 0; i < cur_multi; i++) tmp.append(res);
                    res = new StringBuilder(stack_res.removeLast() + tmp);
                } else if (c >= '0' && c <= '9') multi = multi * 10 + Integer.parseInt(c + "");
                else res.append(c);
            }
            return res.toString();
        }
    }

}
