package leetcode_solutions;

import org.junit.Test;

public class AddStrings415 {

    //to complete:get char and change char to digit
    public String addStrings(String num1, String num2) {
         char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        StringBuilder sb = new StringBuilder();
        String re = "";
        int len = Math.min(chars1.length, chars2.length);
        boolean flag = false;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            if (flag) {
                sum = Character.digit(chars1[i], 10) + Character.digit(chars2[i], 10) + 1;
            } else {
                sum = Character.digit(chars1[i], 10) + Character.digit(chars2[i], 10);
            }
            if (sum > 10) {
                flag = true;
                sum = sum % 10;
            } else {
                flag = false;
            }
            sb.insert(0, sum);
        }
        if (chars1.length == chars2.length) {
            if (flag) {
                sb.insert(0, 1);
            }
        } else if (chars1.length > chars2.length) {
            for (int i = len; i < chars1.length; i++) {
                if (flag) {
                    sum = Character.digit(chars1[i], 10) + 1;
                } else {
                    sum = Character.digit(chars1[i], 10);
                }
                if (sum > 10) {
                    flag = true;
                    sum = sum % 10;
                } else {
                    flag = false;
                }
                sb.insert(0, sum);
            }
        } else if (chars1.length < chars2.length) {
            for (int i = len; i < chars2.length; i++) {
                if (flag) {
                    sum = Character.digit(chars2[i], 10) + 1;
                } else {
                    sum = Character.digit(chars2[i], 10);
                }
                if (sum > 10) {
                    flag = true;
                    sum = sum % 10;
                } else {
                    flag = false;
                }
                sb.insert(0, sum);
            }
        }
        return sb.toString();
    }

    @Test
    public void test(){
        addStrings("98","9");
    }

    public String addStrings0(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
        String res = "";
        while (i >= 0 || j >= 0) {
            if (i >= 0)
                carry += num1.charAt(i--) - '0';
            if (j >= 0)
                carry += num2.charAt(j--) - '0';
            res = Integer.toString(carry % 10) + res;
            carry /= 10;
        }
        return carry != 0 ? "1" + res : res;
    }
}
