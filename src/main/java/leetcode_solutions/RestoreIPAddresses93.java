package leetcode_solutions;


import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddresses93 {
    class Solution {
        public List<String> restoreIpAddresses(String s) {
            List<String> res = new ArrayList<>();
            int n = s.length();
            for (int i = 0; i < 3; i++) {
                for (int j = i + 1; j < i + 4; j++) {
                    for (int k = j + 1; k < j + 4; k++) {
                        if (i < n && j < n && k < n) {
                            String tmp1 = s.substring(0, i + 1);
                            String tmp2 = s.substring(i + 1, j + 1);
                            String tmp3 = s.substring(j + 1, k + 1);
                            String tmp4 = s.substring(k + 1);
                            if (helper(tmp1) && helper(tmp2) && helper(tmp3) && helper(tmp4))
                                res.add(tmp1 + "." + tmp2 + "." + tmp3 + "." + tmp4);
                        }
                    }
                }
            }
            return res;
        }

        private boolean helper(String tmp) {
            if (tmp == null || tmp.length() == 0 || tmp.length() > 3 || (tmp.charAt(0) == '0' && tmp.length() > 1) || Integer.parseInt(tmp) > 255)
                return false;
            return true;
        }
    }

    //执行用时:3ms,在所有java提交中击败了77.97%的用户内存消耗:36.5MB,在所有java提交中击败了45.40%的用户
    class Solution0 {
        public List<String> restoreIpAddresses(String s) {
            List<String> ret = new ArrayList<>();

            StringBuilder ip = new StringBuilder();

            for (int a = 1; a < 4; ++a)
                for (int b = 1; b < 4; ++b)
                    for (int c = 1; c < 4; ++c)
                        for (int d = 1; d < 4; ++d) {
                            if (a + b + c + d == s.length()) {
                                int n1 = Integer.parseInt(s.substring(0, a));
                                int n2 = Integer.parseInt(s.substring(a, a + b));
                                int n3 = Integer.parseInt(s.substring(a + b, a + b + c));
                                int n4 = Integer.parseInt(s.substring(a + b + c));
                                if (n1 <= 255 && n2 <= 255 && n3 <= 255 && n4 <= 255) {
                                    ip.append(n1).append('.').append(n2)
                                            .append('.').append(n3).append('.').append(n4);
                                    if (ip.length() == s.length() + 3) ret.add(ip.toString());
                                    ip.delete(0, ip.length());
                                }
                            }
                        }
            return ret;
        }
    }

    //执行用时:3ms,在所有java提交中击败了77.97%的用户内存消耗:40.2MB,在所有java提交中击败了5.31%的用户
    //trace back
    class Solution1 {
        public List<String> restoreIpAddresses(String s) {
            List<String> res = new ArrayList<>();
            int n = s.length();
            backtrack(0, "", 4, s, res, n);
            return res;
        }

        private void backtrack(int i, String tmp, int flag, String s, List<String> res, int n) {
            if (i == n && flag == 0) {
                res.add(tmp.substring(0, tmp.length() - 1));
                return;
            }
            if (flag < 0) return;
            for (int j = i; j < i + 3; j++) {
                if (j < n) {
                    if (i == j && s.charAt(j) == '0') {
                        backtrack(j + 1, tmp + s.charAt(j) + ".", flag - 1, s, res, n);
                        break;
                    }
                    if (Integer.parseInt(s.substring(i, j + 1)) <= 255)
                        backtrack(j + 1, tmp + s.substring(i, j + 1) + ".", flag - 1, s, res, n);
                }
            }
        }
    }


    class Solution1ms {
        List<String> ans = new ArrayList<>();

        public List<String> restoreIpAddresses(String s) {
            char[] chars = s.toCharArray();
            StringBuilder stringBuilder = new StringBuilder();
            addIp(stringBuilder, 0, 0, chars);
            return ans;
        }

        public void addIp(StringBuilder stringBuilder, int level, int start, char[] chars) {
            if (level > 0 && level < 4) {
                stringBuilder.append('.');
            }
            if (level == 4 && start == chars.length) {
                ans.add(stringBuilder.toString());
            }
            if (level == 4 || start == chars.length || chars.length - start < 4 - level || chars.length - start > 3 * (4 - level)) {
                return;
            }
            int limit;
            if (chars[start] > '2') {
                limit = Math.min(chars.length, start + 2);
            } else if (chars[start] == '0') {
                limit = Math.min(chars.length, start + 1);
            } else {
                limit = Math.min(chars.length, start + 3);
            }
            for (int i = start; i < limit; i++) {
                if (chars[start] == '2') {
                    if ((i - start == 2) && (chars[i - 1] > '5' || (chars[i - 1] == '5' && chars[i] > '5'))) {
                        return;
                    }
                }
                stringBuilder.append(chars[i]);
                addIp(new StringBuilder(stringBuilder), level + 1, i + 1, chars);
            }
        }
    }
}
