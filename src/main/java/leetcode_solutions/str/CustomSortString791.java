package leetcode_solutions.str;

import org.junit.Test;

public class CustomSortString791 {
    class Solution {
        public String customSortString(String S, String T) {

            char[] chs = T.toCharArray();
            int l = 0;
            for (int i = 0; i < S.length(); i++) {
                char c = S.charAt(i);
                while (l < chs.length && chs[l] == c) l++;
                int r = find(l + 1, chs, c);
                while (r != -1) {
                    swap(l, r, chs);
                    r = find(r + 1, chs, c);
                    l++;
                }
                if (l >= chs.length) break;
            }

            return new String(chs);

        }

        private int find(int from, char[] chs, char c) {
            for (int i = from; i < chs.length; i++) {
                if (chs[i] == c) return i;
            }
            return -1;
        }

        private void swap(int l, int r, char[] chs) {
            chs[l] = (char) (chs[l] ^ chs[r]);
            chs[r] = (char) (chs[l] ^ chs[r]);
            chs[l] = (char) (chs[l] ^ chs[r]);
        }
    }

    class Solution1 {
        public String customSortString(String S, String T) {
            StringBuilder sb = new StringBuilder();
            int[] map = new int[S.length()];
            for (char c : T.toCharArray()) {
                int i = S.indexOf(c);
                if (i < 0) sb.append(c);
                else map[i]++;
            }
            for (int i = 0; i < S.length(); i++) {
                char c = S.charAt(i);
                while (map[i] != 0) {
                    sb.append(c);
                    map[i]--;
                }
            }
            return sb.toString();
        }
    }

    @Test
    public void test() {
//        String S = "cba";
//        String T = "abcd";
        String S = "kqep";
        String T = "pekeq";
        String re = new Solution().customSortString(S, T);
        System.out.println(re);

        System.out.println(T.indexOf('e', 3));

        char a = 'b';
        char b = 'b';
        a = (char) (a ^ b);
        b = (char) (a ^ b);
        a = (char) (a ^ b);
        System.out.println(a);
        System.out.println(b);
        System.out.println((int) '0');
        System.out.println((int) '6');
        System.out.println((int) '6' + (int) '2');
        System.out.println((int) 'a');
    }
}
