package lintcode_solutions;

import org.junit.Test;

public class DigitCounts3 {
    /*
     * @param : An integer
     * @param : An integer
     * @return: An integer denote the count of digit k in 1..n
     */
    public int digitCounts(int k, int n) {
        int re = 0;
        String str = String.valueOf(k);
        for (int i = 0; i <= n; i++) {
            String temp = String.valueOf(i);
            int lastIndex = temp.lastIndexOf(str);
            while (lastIndex != -1) {
                re++;
                temp = temp.substring(0, lastIndex);
                lastIndex = temp.lastIndexOf(str);
            }
        }
        return re;
    }

    public int digitCounts0(int k, int n) {
        if (k < 1 || k > 9) {
            return -1;
        }

        int count = 0;
        int factor = 1;
        int low = 0, cur = 0, high = 0;
        while (n / factor != 0) {
            low = n - (n / factor) * factor;//低位数字
            cur = (n / factor) % 10;
            high = n / (factor * 10);

            if (cur < k)
                count += high * factor;
            else if (cur == k)
                count += high * factor + low + 1;
            else
                count += (high + 1) * factor;

            factor *= 10;
        }

        return count;
    }


    public int digitCounts1(int k, int n) {
        int sum = 0;
        char kk = (char) ('0' + k);
        for (int i = 0; i <= n; i++) {
            String ii = Integer.toString(i);
            for (int j = 0; j < ii.length(); j++) {
                if (ii.charAt(j) == kk) sum++;
            }
        }
        return sum;
    }

    @Test
    public void test() {
        System.out.println(digitCounts0(1, 12));
//        System.out.println("aaabbbccc".lastIndexOf("cc"));
//        System.out.println("aaabbbccc".substring(0, "aaabbbccc".lastIndexOf("cc")));
    }
}
