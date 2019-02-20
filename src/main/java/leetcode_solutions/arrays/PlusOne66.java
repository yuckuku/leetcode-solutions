package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

/**
 * @Auther: L'Nan
 * @Date: 2018/11/12 20:35
 * @Description:
 */
public class PlusOne66 {
    public int[] plusOne(int[] digits) {
        boolean carry = true;
        for (int i = digits.length - 1; i >= 0; i--) {
            if (carry) {
                if (digits[i] != 9) {
                    digits[i]++;
                    carry = false;
                    break;
                } else {
                    digits[i] = 0;
                    carry = true;
                    continue;
                }
            } else {
                break;
            }
        }

        if (carry) {
            int[] newDigits = new int[digits.length + 1];
            newDigits[0] = 1;
            for (int i = 1; i < newDigits.length; i++) {
                newDigits[i] = digits[i - 1];
            }
            return newDigits;
        } else {
            return digits;
        }
    }

    @Test
    public void test() {
        int[] digits = new int[]{4,3,2,1};
        int[] re = plusOne(digits);
        System.out.println(Arrays.toString(re));
    }
}
