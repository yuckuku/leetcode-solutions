package leetcode_solutions.arrays;

/**
 * @author: L'Nan
 * Date: 2019/4/29 14:04
 * Description:
 */
public class GlobalandLocalInversions775 {

    class Solution {
        /**
         * TLE
         */
        public boolean isIdealPermutation(int[] A) {
            for (int i = 0; i < A.length - 2; i++) {
                for (int j = i + 2; j < A.length; j++) {
                    if (A[i] > A[j])
                        return false;
                }
            }
            return true;
        }

        public boolean isIdealPermutation1(int[] A) {
            for (int i = 0; i < A.length - 1; i++) {
                if (A[i] != i) {
                    if (A[i] == i + 1 && A[i + 1] == i) {
                        i++;
                        continue;
                    } else {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    class SolutionOnLeetCode1 {
        public boolean isIdealPermutation(int[] A) {

            int max = Integer.MIN_VALUE;
            for (int i = 0; i < A.length - 2; i++) {
                max = Math.max(max, A[i]);
                if (max > A[i + 2])
                    return false;
            }

            return true;
        }
    }
}
