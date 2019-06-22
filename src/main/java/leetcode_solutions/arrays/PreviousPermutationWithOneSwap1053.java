package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author: L'Nan
 * Date: 2019/6/10 15:59
 * Description:
 */
public class PreviousPermutationWithOneSwap1053 {
    class Solution {
        public int[] prevPermOpt1(int[] A) {
            int len = A.length;
            int left = len - 2;
            for (; left >= 0; left--) {
                if (A[left] > A[left + 1]) break;
            }
            if (left < 0) return A;
            int right = len - 1;
            for (; right > left; right--) {
                if (A[right] < A[left]) {
                    int temp = A[right];
                    for (; right >= left; right--) {
                        if (A[right] != temp) {
                            right++;
                            break;
                        }
                    }
                    break;
                }
            }
            //swap
            int temp = A[right];
            A[right] = A[left];
            A[left] = temp;
            return A;
        }
    }

    class SolutionOnLeetcode1 {
        public int[] prevPermOpt1(int[] A) {
            int j = A.length - 1;
            for (int i = j; i >= 1; i--) {
                if (A[i] < A[i - 1]) {
                    while (A[j] >= A[i - 1] || A[j] == A[j - 1])
                        j--;
                    int temp = A[j];
                    A[j] = A[i - 1];
                    A[i - 1] = temp;
                    break;
                }
            }
            return A;
        }
    }

    @Test
    public void test() {
        int[] A = new int[]{3, 2, 1};
        Solution s = new Solution();
        int[] re = s.prevPermOpt1(A);
        System.out.println(Arrays.toString(re));
    }
}
