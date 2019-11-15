package classic.algorithms;

import org.junit.Test;

import java.util.Arrays;

public class KMP {

    public static int[] getKMPNext(String dest) {
        int[] next = new int[dest.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < dest.length(); i++) {
            //当不相等的时候
            if (dest.charAt(i) != dest.charAt(j)) {
                //j回退直到找到相同的，或者j=0
                while (dest.charAt(i) != dest.charAt(j) && j > 0) {
                    j = next[j - 1];
                }
                if (dest.charAt(i) == dest.charAt(j)) {
                    next[i] = j + 1;
                    j++;
                } else if (j == 0) {
                    next[i] = 0;
                }
            } else {
                //相等时
                next[i] = j + 1;
                j++;
            }
        }
        return next;
    }

    public static int kmp(String str, String dest, int[] next) {
        for (int i = 0, j = 0; i < str.length(); i++) {
            while (j > 0 && str.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            if (str.charAt(i) == dest.charAt(j)) {
                j++;
            }
            if (j == dest.length()) {
                return i - j + 1;
            }
        }
        return 0;
    }

    @Test
    public void test() {
        String str =  "abacaabacabacabaabb";
        String dest = "abacab";
        int[] next = KMP.getKMPNext(dest);//[0, 0, 1, 0, 1, 2]
        System.out.println(Arrays.toString(next));

        System.out.println("----------------");
        int re = KMP.kmp(str, dest, next);
        System.out.println(re);
    }
}
