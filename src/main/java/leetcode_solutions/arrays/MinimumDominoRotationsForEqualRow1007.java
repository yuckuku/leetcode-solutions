package leetcode_solutions.arrays;

/**
 * @author: L'Nan
 * Date: 2019/5/15 15:10
 * Description:
 */
public class MinimumDominoRotationsForEqualRow1007 {
    class Solution {
        public int minDominoRotations(int[] A, int[] B) {

            int a = A[0], b = B[0];
            int a_top = 0, a_bottom = 0, b_top = 0, b_bottom = 0;
            boolean aflag = true, bflag = true;
            for (int i = 0; i < A.length; i++) {
                //not a
                if (A[i] != a && B[i] != a) {
                    aflag = false;
                }

                //not b
                if (A[i] != b && B[i] != b) {
                    bflag = false;
                }

                if (!aflag && !bflag) return -1;

                //a
                if (aflag) {
                    if (A[i] != a) a_top++;
                    if (B[i] != a) a_bottom++;
                }

                //b
                if (bflag) {
                    if (A[i] != b) b_top++;
                    if (B[i] != b) b_bottom++;
                }
            }

            if (aflag) return Math.min(a_top, a_bottom);
            if (bflag) return Math.min(b_top, b_bottom);
            return -1;
        }
    }

    class SolutionOnLeetcode1 {
        public int minDominoRotations(int[] A, int[] B) {
            int times = Integer.MAX_VALUE;
            //首行不交换
            //A行相同 or B行相同 需要的次数
            int ca = count(A, B), cb = count(B, A);
            if (ca == Integer.MAX_VALUE && cb == Integer.MAX_VALUE)
                return -1;
            times = Math.min(ca, cb);

            //首行交换
            int temp = A[0];
            A[0] = B[0];
            B[0] = temp;
            ca = count(A, B);
            cb = count(B, A);
            times = Math.min(times, Math.min(ca, cb) + 1);
            return times;
        }

        int count(int[] A, int[] B) {
            int tar = A[0], count = 0;
            for (int i = 1; i < A.length; i++) {
                if (A[i] != tar) {
                    if (B[i] != tar)
                        return Integer.MAX_VALUE;
                    else count++;
                }
            }

            return count;
        }
    }
}
