package leetcode_solutions.str;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class BuddyStrings859 {
    class Solution {
        public boolean buddyStrings(String A, String B) {
            if (A.equals(B)) {
                int[] buckets = new int[122];
                for (int i = 0; i < A.length(); i++) {
                    int index = (int) A.charAt(i);
                    buckets[index] += 1;
                    if (buckets[index] > 1) return true;
                }
                return false;
            }
            if (A.length() != B.length()) return false;
            int l = -1, r = -1;
            for (int i = 0; i < A.length(); i++) {
                if (A.charAt(i) != B.charAt(i)) {
                    if (l == -1) l = i;
                    else {
                        r = i;
                        break;
                    }
                }
            }
            if (l == -1 || r == -1) return false;
            char[] chs = A.toCharArray();
            chs[l] = (char) (chs[l] ^ chs[r]);
            chs[r] = (char) (chs[l] ^ chs[r]);
            chs[l] = (char) (chs[l] ^ chs[r]);
            if (String.valueOf(chs).equals(B)) return true;
            return false;
        }
    }

    class Solution1 {
        public boolean buddyStrings(String A, String B) {
            if (A.length() != B.length() || A.length() < 2 || B.length() < 2) {
                return false;
            }
            int start = -1;  //前面指针
            int end = -1;    //后面指针
            char tmp = ' ';
            if (A.equals(B)) { //要么全是一个字符，要么有两个以上相同字符。这俩种true
                Set<Character> set = new HashSet<>();
                for (int i = 0; i < A.length(); i++) {
                    if (set.contains(A.charAt(i))) {
                        return true;
                    } else {
                        set.add(A.charAt(i));
                    }
                }
                return false;
            } else {
                for (int i = 0; i < A.length(); i++) {
                    if (A.charAt(i) != B.charAt(i)) {
                        if (start == -1) {
                            start = i;
                        } else if (end == -1) {
                            end = i;
                        }
                    }
                    if (A.charAt(A.length() - 1 - i) != B.charAt(A.length() - 1 - i)) {
                        if (end == -1) {
                            end = A.length() - 1 - i;
                        } else if (start == -1) {
                            start = A.length() - 1 - i;
                        }
                    }
                    if (start == end) {
                        return false;
                    }

                    if (start != -1 && end != -1) {
                        break;
                    }
                }
                //存在两个不相等的
                char[] chars = A.toCharArray();
                tmp = chars[start];
                chars[start] = chars[end];
                chars[end] = tmp;
                A = String.valueOf(chars);
                if (!A.equals(B)) {
                    return false;
                }
            }
            return true;
        }
    }

    @Test
    public void test() {
        String A = "ab", B = "ab";
        Solution solution = new Solution();
        boolean re = solution.buddyStrings(A, B);
        System.out.println(re);
        System.out.println((int) 'z');
    }
}
