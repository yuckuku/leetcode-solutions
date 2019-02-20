package leetcode_solutions.arrays;

import org.junit.Test;

/**
 * @Auther: Administrator
 * @Date: 2018/11/5 16:06
 * @Description: Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.
 */
public class MaximumLengthofRepeatedSubarray718 {

    //my solution:can not cover all test case, wrong answer
    public int findLength(int[] A, int[] B) {
        int max = findMax(A, B);
        int maxReverse = findMax(B, A);
        return Math.max(max, maxReverse);
    }

    private int findMax(int[] A, int[] B) {
        int max = 0;
        int temp = 0;
        for (int i = 0; i < A.length; i++) {
            int tempIndex = i;
            for (int j = 0; j < B.length; j++) {
                if (tempIndex < A.length && A[tempIndex] == B[j]) {
                    tempIndex++;
                    temp++;
                } else if (j > 0 && B[j] == B[j - 1]) {
                    j++;
                } else {
                    max = Math.max(max, temp);
                    temp = 0;
                    tempIndex = i;
                }
            }
            max = Math.max(max, temp);
            temp = 0;

        }
        return max;
    }

    @Test
    public void test() {
        //max=3
//        int[] A = new int[]{1, 2, 3, 2, 1};
//        int[] B = new int[]{3, 2, 1, 4, 7};

        //max=5
//        int[] A = new int[]{0, 0, 0, 0, 0};
//        int[] B = new int[]{0, 0, 0, 0, 0};

        //max=9
//        int[] A = new int[]{0, 0, 0, 0, 0, 0, 1, 0, 0, 0};
//        int[] B = new int[]{0, 0, 0, 0, 0, 0, 0, 1, 0, 0};

        //max=6
//        int[] A = new int[]{1, 0, 1, 0, 0, 0, 0, 0, 1, 1};
//        int[] B = new int[]{1, 1, 0, 1, 1, 0, 0, 0, 0, 0};

        //max=4,my solution's result is 5
        int[] A = new int[]{1, 0, 0, 1, 0, 0, 0, 1, 0, 1};
        int[] B = new int[]{1, 1, 0, 0, 1, 1, 1, 0, 0, 0};


        int max = findLength(A, B);
        System.out.println(max);
    }

    //DP two-dimensional array
    public int findLength0(int[] A, int[] B) {
        int[][] dp = new int[A.length + 1][B.length + 1];
        int res = 0;
        for (int i = 1; i < dp.length; ++i) {
            for (int j = 1; j < dp[i].length; ++j) {
                dp[i][j] = (A[i - 1] == B[j - 1]) ? dp[i - 1][j - 1] + 1 : 0;
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }

    /**
     * solution on leetcode
     */
    class Solution {
        // public int findLength(int[] A, int[] B) {
        //     int[][] dp = new int[A.length+1][B.length+1];
        //     int result = 0;
        //     for(int i =1; i<=A.length;i++){
        //         for(int j = 1; j<=B.length; j++){
        //             if(A[i-1] == B[j-1]){
        //                 dp[i][j] = dp[i-1][j-1] +1;
        //             }
        //             result = Math.max(dp[i][j], result);
        //         }
        //     }
        //     return result;
        // }

        public int findLength(int[] a, int[] b) {
            int len_a = a.length;
            int len_b = b.length;
            int dp[] = new int[len_b+1];
            int maxLen = 0;

            for (int i=1; i<=len_a; i++) {
                for (int j=len_b; j>0; j--) {
                    if (a[i-1] == b[j-1]) {
                        dp[j] = dp[j-1] + 1;
                        maxLen = Math.max(maxLen, dp[j]);
                    } else {
                        dp[j] = 0;
                    }
                }
            }
            return maxLen;
        }
    }
}
