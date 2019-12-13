package leetcode_solutions;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

public class SimplifyPath71 {
    class Solution {
        public String simplifyPath(String path) {
            Deque<String> deque = new ArrayDeque<>();
            String[] ss = path.split("/");
            for (int i = 0; i < ss.length; i++) {
                if (ss[i].equals(".") || ss[i].equals("")) continue;
                else if (ss[i].equals("..")) {
                    if (!deque.isEmpty()) {
                        deque.pop();
                    }
                } else deque.push(ss[i]);
            }

            if (deque.isEmpty()) return "/";

            StringBuilder sb = new StringBuilder();

            while (!deque.isEmpty()) {
                sb.append("/");
                sb.append(deque.pollLast());
            }
            return sb.toString();
        }
    }

    @Test
    public void test() {
        String path = "/../";
        Solution solution = new Solution();
        String re = solution.simplifyPath(path);
        System.out.println(re);
    }

    class Solution1ms {
        public String simplifyPath(String path) {
            char[] pathChars = path.toCharArray();
            int resI = pathChars.length;
            int mode = 0;
            int skips = 0;
            for (int i = pathChars.length - 1; i >= 0; --i) {
                char c = pathChars[i];
                if (c == '/') {
                    if (mode == 1) {
                        pathChars[--resI] = '/';
                    }
                    mode = 0;
                } else {
                    if (mode == 0) {
                        if (c == '.') {
                            if (pathChars[i - 1] == '.' && pathChars[i - 2] == '/') {
                                ++skips;
                                i -= 2;
                                continue;
                            }
                            if (pathChars[i - 1] == '/') {
                                --i;
                                continue;
                            }
                        }
                        if (skips > 0) {
                            --skips;
                            mode = 2;
                        } else {
                            pathChars[--resI] = c;
                            mode = 1;
                        }
                    } else if (mode == 1) {
                        pathChars[--resI] = c;
                    }
                }
            }
            if (resI == pathChars.length || mode == 1) {
                pathChars[--resI] = '/';
            }
            return new String(pathChars, resI, pathChars.length - resI);
        }
    }
}
