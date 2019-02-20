package leetcode_solutions.arrays;

import org.junit.Test;
import org.terracotta.quartz.TerracottaToolkitBuilder;

import java.util.Arrays;

/**
 * @Auther: L'Nan
 * @Date: 2018/11/13 14:38
 * @Description:
 */
public class LengthofLongestFibonacciSubsequence873 {
    //my solution: using dividing search, turns to be right answer.
    class MySolution {
        public int lenLongestFibSubseq(int[] A) {
            int len = 0;

            for (int i = 0; i < A.length - 1; i++) {
                int j = i + 1;
                for (; j < A.length; j++) {
                    int tempLen = 0;
                    int previousIndex = i;
                    int currentIndex = j;
                    int nextIndex = findSumIndex(A, A[previousIndex] + A[currentIndex], Math.min(currentIndex + 1, A.length - 1), A.length - 1);
                    if (-1 != nextIndex) {
                        tempLen = 2;
                    }
                    previousIndex = currentIndex;
                    currentIndex = nextIndex;
                    while (-1 != nextIndex) {
                        tempLen++;
                        nextIndex = findSumIndex(A, A[previousIndex] + A[currentIndex], Math.min(currentIndex + 1, A.length - 1), A.length - 1);
                        previousIndex = currentIndex;
                        currentIndex = nextIndex;
                    }
                    len = Math.max(len, tempLen);
                }
            }
            return len;
        }

        private int findSumIndex(int[] A, int sum, int left, int right) {
            if (left == right) {
                return A[left] == sum ? left : -1;
            }
            int mid = (left + right) / 2;
            if (A[mid] == sum) {
                return mid;
            } else if (A[mid] > sum) {
                return findSumIndex(A, sum, left, Math.max(left, mid - 1));
            } else {
                return findSumIndex(A, sum, mid + 1, right);
            }
        }
    }

    //solution on leetcode: using dp.
    class Solution {
        public int lenLongestFibSubseq(int[] A) {
            if (A.length < 3)
                return 0;
            int len = A.length;
            int dp[][] = new int[len][len];
            int maxLen = 0;
            int sum = 0;
            for (int i = 0; i < len; i++) {
                Arrays.fill(dp[i], 2);
            }
            for (int i = 1; i < len; i++) {
                int l = 0;
                int r = i - 1;
                while (l < r) {
                    sum = A[l] + A[r];
                    if (sum > A[i])
                        r--;
                    else if (sum < A[i])
                        l++;
                    else {
                        dp[r][i] = dp[l][r] + 1;
                        if (maxLen < dp[r][i])
                            maxLen = dp[r][i];
                        l++;
                        r--;
                    }
                }
            }
            return maxLen == 0 ? 0 : maxLen;
        }
    }

    @Test
    public void test() {
        //ans=5
        int[] A = new int[]{1, 2, 3, 4, 5, 6, 7, 8};

        int len = new MySolution().lenLongestFibSubseq(A);
        System.out.println(len);
    }
}
