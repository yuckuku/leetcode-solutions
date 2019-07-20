package leetcode_solutions.str;

import org.junit.Test;

import java.util.Arrays;

public class StringCompression443 {
    class Solution {
        public int compress(char[] chars) {
            int re = 0;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                re++;
                int count = 1;
                chars[index++] = chars[i];
                for (int j = i + 1; j < chars.length && chars[j] == chars[i]; j++) {
                    count++;
                    i = j;
                }
                if (count > 1) {
                    String tmp = String.valueOf(count);
                    re += tmp.length();
                    System.arraycopy(tmp.toCharArray(), 0, chars, index, tmp.length());
                    index += tmp.length();
                }
            }
            char[] t = new char[index];
            System.arraycopy(chars, 0, t, 0, index);
            chars = t;
            return re;
        }
    }

    class SolutionOnLeetcode1 {
        public int compress(char[] chars) {
            int len = chars.length;
            int tmp = 0;
            for (int i = 0, j = 0; i < len; i = j) {
                while (j < len && chars[i] == chars[j]) {
                    j++;
                }
                chars[tmp] = chars[i];
                tmp = tmp + 1;
                if (j - i == 1) {
                    continue;
                }
                for (char a : String.valueOf(j - i).toCharArray()) {

                    chars[tmp] = a;
                    tmp = tmp + 1;
                }
            }
            return tmp;
        }
    }

    @Test
    public void test() {
        char[] chars = new char[]{'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        SolutionOnLeetcode1 s = new SolutionOnLeetcode1();
        int re = s.compress(chars);
        System.out.println(re);
        System.out.println(Arrays.toString(chars));
    }

}
