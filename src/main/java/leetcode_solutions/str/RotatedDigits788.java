package leetcode_solutions.str;

import com.google.common.base.CaseFormat;
import com.google.common.primitives.Chars;
import org.junit.Test;

public class RotatedDigits788 {
    class Solution {
        public int rotatedDigits(int N) {
// 0, 1, and 8 rotate to themselves;
// 2 and 5 rotate to each other;
// 6 and 9 rotate to each other,
// and the rest of the numbers do not rotate to any other number and become invalid.
            int re = 0;
            for (int i = 1; i <= N; i++) {
                if (validAfterRotated(i)) re++;
            }
            return re;
        }

        private boolean validAfterRotated(int n) {

            String s = String.valueOf(n);
            if (!s.matches("^((?!3|4|7).)*$")) return false;
            char[] chs = s.toCharArray();
            for (int i = 0; i < chs.length; i++) {
                switch (chs[i]) {
                    case '2':
                        chs[i] = '5';
                        break;
                    case '5':
                        chs[i] = '2';
                        break;
                    case '6':
                        chs[i] = '9';
                        break;
                    case '9':
                        chs[i] = '6';
                        break;
                    default:
                        break;
                }
            }

            String tmp = String.valueOf(chs);
//            System.out.println(tmp);
            if (!tmp.equals(s))
                return true;
            else return false;
        }

    }

    class SolutionOnLeetcode1 {
        public int rotatedDigits(int N) {
            /*按照1000为单位进行划分*/
            int[] a = {0, 316, 632, 975, 975, 975, 1318, 1661, 1661, 1977, 2320};
            int[] b = {0, 40, 80, 129, 129, 129, 178, 227, 227, 267, 316};
            int[] c = {0, 49, 98, 147, 147, 147, 196, 245, 245, 294, 343};

            int[] d = {0, 4, 8, 15, 15, 15, 22, 29, 29, 33, 40};
            int[] e = {0, 7, 14, 21, 21, 21, 28, 35, 35, 42, 49};

            int[] f = {0, 0, 0, 1, 1, 1, 2, 3, 3, 3, 4};
            int[] g = {0, 1, 2, 3, 3, 3, 4, 5, 5, 6, 7};
            if (N == 10000) return a[10];
            int high = N / 1000;
            boolean is2 = false;
            int result = a[high];
            if (high > 0) {
                if (high == 3 || high == 4 || high == 7) return result;
                if (high == 2 || high == 5 || high == 6 || high == 9) {
                    is2 = true;
                }
            }
            int last3 = N % 1000;//取余数
            int second = last3 / 100;
            result = result + (is2 ? c[second] : b[second]);
            if (second > 0) {
                if (second == 3 || second == 4 || second == 7) {
                    return result;
                }
                if (second == 2 || second == 5 || second == 6 || second == 9) {
                    is2 = true;
                }
            }
            int last2 = last3 % 100;//取十位和个位
            int third = last2 / 10;//取十位
            result = result + (is2 ? e[third] : d[third]);
            if (third > 0) {
                if (third == 3 || third == 4 || third == 7) {
                    return result;
                }
                if (third == 2 || third == 5 || third == 6 || third == 9) {
                    is2 = true;
                }
            }

            int last1 = last2 % 10;//取个位
            result = result + (is2 ? g[last1] : f[last1]);
            if (last1 == 2 || last1 == 5 || last1 == 6 || last1 == 9) {
                return result + 1;
            } else if (last1 == 3 || last1 == 4 || last1 == 7) {
                return result;
            } else {
                return result + (is2 ? 1 : 0);
            }
        }
    }

    class SolutionOnLeetcode3 {
        public int rotatedDigits(int N) {
            byte[] d = new byte[N + 1];
            byte[] a = new byte[]{0, 0, 1, -1, -1, 1, 1, -1, 0, 1};
            int ans = 0;
            for (int i = 1; i <= N; i++) {
                d[i] = -1;
                int i1 = d[i / 10] | a[i % 10];
                if (i1 == 0) {
                    d[i] = 0;
                } else if (i1 == 1) {
                    d[i] = 1;
                    ans++;
                }
            }
            return ans;
        }
    }

    @Test
    public void test() {
        int N = 3984;
        SolutionOnLeetcode1 s = new SolutionOnLeetcode1();
        int re = s.rotatedDigits(N);
        System.out.println(re);
    }
}
