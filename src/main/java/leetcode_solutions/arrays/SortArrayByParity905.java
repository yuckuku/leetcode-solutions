package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SortArrayByParity905 {

    public int[] sortArrayByParity(int[] A) {
        int left = 0;
        int right = A.length - 1;

        while (right != left) {
            if (A[left] % 2 == 1 && A[right] % 2 == 0) {
                //switch
                int temp = A[left];
                A[left] = A[right];
                A[right] = temp;
//                A[st] = A[st] ^ A[ed];
//                A[ed] = A[st] ^ A[ed];
//                A[st] = A[st] ^ A[ed];

            } else if (A[left] % 2 != 1) {
                left++;
            } else if (A[right] % 2 != 0) {
                right--;
            }
        }
        return A;
    }

    @Test
    public void test() {
        int[] A = new int[]{3, 1, 2, 4};

    }
}
