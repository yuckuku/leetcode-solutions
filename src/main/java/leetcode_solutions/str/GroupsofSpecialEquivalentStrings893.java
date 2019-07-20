package leetcode_solutions.str;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

public class GroupsofSpecialEquivalentStrings893 {
    class Solution {
        public int numSpecialEquivGroups(String[] A) {
            boolean[] tt = new boolean[A.length];
            int re = 0;
            for (int i = 0; i < A.length; i++) {
                if (!tt[i]) {
                    re++;
                    tt[i] = true;
                } else {
                    continue;
                }
                for (int j = i + 1; j < A.length; j++) {
                    if (special(A[i], A[j])) {
                        tt[j] = true;
                    }
                }
            }

            return re;
        }

        private boolean special(String a, String b) {
            int len1 = a.length();
            int len2 = b.length();
            if (len1 != len2) return false;
            char[] aChs = a.toCharArray();
            char[] bChs = b.toCharArray();
            int evenLen = len1 >> 1;//偶数位
            int oddLen = len1 - evenLen;//奇数位
            char[] aEven = new char[evenLen];
            char[] bEven = new char[evenLen];
            char[] aOdd = new char[oddLen];
            char[] bOdd = new char[oddLen];
            for (int i = 0; i < len1; i++) {
                int index = i >> 1;
                if (i % 2 == 0) {
                    aOdd[index] = aChs[i];
                    bOdd[index] = bChs[i];
                } else {
                    aEven[index] = aChs[i];
                    bEven[index] = bChs[i];
                }
            }

            Arrays.sort(aEven);
            Arrays.sort(bEven);

            if (!Arrays.equals(aEven, bEven)) return false;

            Arrays.sort(aOdd);
            Arrays.sort(bOdd);
            if (!Arrays.equals(aOdd, bOdd)) return false;

            return true;
        }

        private boolean specialEquivalent(String a, String b) {
            int len1 = a.length();
            int len2 = b.length();
            if (len1 != len2) return false;
            int evenLen = len1 >> 1;//偶数位
            int oddLen = len1 - evenLen;//奇数位
            char[] aChs = a.toCharArray();
            char[] bChs = b.toCharArray();
            if (evenLen == 2 && oddLen == 2) {
                if (aChs[0] != aChs[2] && aChs[1] != aChs[3]) return false;
                Arrays.sort(aChs);
                Arrays.sort(bChs);
                if (!Arrays.equals(aChs, bChs)) {
                    return false;
                }
                return true;
            } else {

                if (evenLen >= 3) {
                    char ach = aChs[1];
                    char bch = bChs[1];
                    if (ach != bch) return false;
                    for (int i = 1; i < len1; i += 2) {
                        if (aChs[i] != bChs[i] || aChs[i] != ach) return false;
                    }
                }
                if (oddLen >= 3) {
                    char ach = aChs[0];
                    char bch = bChs[0];
                    if (ach != bch) return false;
                    for (int i = 0; i < len1; i += 2) {
                        if (aChs[i] != bChs[i] || aChs[i] != ach) return false;
                    }
                }
            }
            return true;
        }
    }

    class SolutionOnLeetcode1 {
        private final  int[] RECORD = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};

        public int numSpecialEquivGroups(String[] A) {
            HashSet<Integer> res = new HashSet<>();
            for (String s : A) {
                res.add(hash(s));
            }
            return res.size();
        }

        private  int hash(String str) {
            int res = 1;
            for (int i = 0; i < str.length(); i++) {
                int idx = str.charAt(i++) - 97;
                res *= RECORD[idx];
            }
            res += 5000;
            for (int i = 1; i < str.length(); i++) {
                int idx = str.charAt(i++) - 97;
                res *= RECORD[idx];
            }
            return res;
        }
    }

    @Test
    public void test() {
        String[] A = {"abcd", "cdab", "cbad", "xyzz", "zzxy", "zzyx"};
        Solution s = new Solution();
        int re = s.numSpecialEquivGroups(A);
        System.out.println(re);
    }
}
