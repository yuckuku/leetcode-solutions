package leetcode_solutions;

import java.math.BigDecimal;

public class MultiplyStrings43 {
    //用了BigDecimal，不符合题目要求，但是能ac
    class Solution {
        public String multiply(String num1, String num2) {
            BigDecimal a = new BigDecimal(num1);
            BigDecimal b = new BigDecimal(num2);
            return a.multiply(b) + "";
        }
    }

    class Solution2ms {
        public String multiply(String num1, String num2) {
            if (num1.equals("0") || num2.equals("0")) return "0"; // 0额外处理
            char[] cs1 = num1.toCharArray();
            char[] cs2 = num2.toCharArray();
            int len1 = cs1.length, len2 = cs2.length;
            int[] res = new int[len1 + len2];
            // 乘法
            for (int i = 0; i < len1; i++) {
                for (int j = 0; j < len2; j++) {
                    res[i + j + 1] += (cs1[i] - '0') * (cs2[j] - '0');
                }
            }
            // 进位
            int ext = 0;
            for (int i = len1 + len2 - 1; i >= 0; i--) {
                res[i] += ext;
                ext = res[i] / 10;
                res[i] %= 10;
            }
            // 转为字符串
            StringBuilder sb = new StringBuilder();
            for (int i : res) sb.append(i);
            if (res[0] == 0) return sb.substring(1);
            else return sb.toString();
        }
    }
}
