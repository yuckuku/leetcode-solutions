package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author: L'Nan
 * Date: 2019/5/21 10:47
 * Description:
 */
public class MaximumSwap670 {
    class Solution {
        public int maximumSwap(int num) {

            String s = num + "";
            int len = s.length();
            int[] digits = new int[s.length()];
            for (int i = 0; i < len; i++) {
                digits[i] = s.charAt(i);
            }
            Arrays.sort(digits);
            int i = len - 1;
            for (; i > 0; i--) {
                if (digits[i] != s.charAt(len - i - 1)) {
                    break;
                }
            }
            if (i == 0) return num;
            int j = len - 1;
            for (; j > 0; j--) {
                if (s.charAt(j) == digits[i]) break;
            }
            System.out.println(s.substring(0, len - i - 1));
            System.out.println(String.valueOf((char) digits[i]));
            System.out.println(s.substring(len - i, j));
            System.out.println(String.valueOf(s.charAt(len - i - 1)));
            System.out.println(s.substring(j + 1, len));
            String str = s.substring(0, len - i - 1) +
                    String.valueOf((char) digits[i]) +
                    s.substring(len - i, j) +
                    String.valueOf(s.charAt(len - i - 1)) +
                    s.substring(j + 1, len);
            return Integer.valueOf(str);
        }
    }

    class SolutionOnLeetcode1 {

        public int maximumSwap(int num) {
            String n = "" + num;
            int iloc = -1;
            int l = -1, r = -1;
            //寻找第一个非增
            for (int i = 0; i < n.length() - 1; i++) {
                if (n.charAt(i + 1) > n.charAt(i)) {
                    iloc = i;
                    break;
                }
            }
            if (iloc == -1)
                return num;
            char max = '0';
            for (int i = n.length() - 1; i > iloc; i--) {
                if (n.charAt(i) > max) {
                    max = n.charAt(i);
                    r = i;
                }
                if (max == '9')
                    break;
            }
            for (int i = 0; i <= iloc; i++) {
                if (n.charAt(i) < max) {
                    l = i;
                    break;
                }
            }
            return Integer.parseInt(n.substring(0, l) + n.charAt(r) + n.substring(l + 1, r) + n.charAt(l) + n.substring(r + 1));


        }
    }

    @Test
    public void test() {
        int num = 98368;
        Solution s = new Solution();
        int re = s.maximumSwap(num);
        System.out.println(re);
    }
}
