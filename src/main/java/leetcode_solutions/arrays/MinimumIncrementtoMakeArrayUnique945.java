package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author: L'Nan
 * Date: 2019/5/8 15:59
 * Description:
 */
public class MinimumIncrementtoMakeArrayUnique945 {
    /**
     * TLE
     */
    class Solution {
        public int minIncrementForUnique(int[] A) {
            int re = 0;
            Arrays.sort(A);
            for (int i = 1; i < A.length; i++) {
                if (A[i] == A[i - 1]) {
                    re += unique(A, i);
                    Arrays.sort(A);
                    i--;
                }
            }
            return re;
        }

        private int unique(int[] A, int index) {
            int nextValue = -1;
            for (int i = index + 1; i < A.length; i++) {
                if (A[i] - A[i - 1] > 1) {
                    nextValue = A[i - 1] + 1;
                    break;
                }
            }
            if (nextValue == -1) {
                nextValue = A[A.length - 1] + 1;
            }
            int re = nextValue - A[index];
            A[index] = nextValue;
            return re;
        }
    }

    /**
     * wrong answer
     */
    class Solution1 {
        public int minIncrementForUnique(int[] A) {
            if (null == A || A.length == 0)
                return 0;
            int re = 0;
            Arrays.sort(A);
            int nextValue = -1;
            int temp = A[A.length - 1] + 1;
            boolean flag = true;
            int j = -1;
            boolean moveflag = false;
            int last = -1;
            for (int i = 1; i < A.length; i++) {
                if (moveflag && A[i] == last) {
                    if (j == -1) {
                        j = i + 1;
                        for (; j < A.length; j++) {
                            if (A[j] - A[j - 1] > 1) {
                                nextValue = A[j - 1] + 1;
                                flag = false;
                                break;
                            }
                        }
                    } else if (nextValue < A[A.length - 1]) {
                        for (; j < A.length; j++) {
                            if (A[j] - nextValue > 1) {
                                nextValue = nextValue + 1;
                                flag = false;
                                break;
                            }
                        }
                    }
                    if (flag) {
                        nextValue = temp++;
                    }
                    re += nextValue - A[i];
                    moveflag = true;
                    last = A[i];
                    A[i] = nextValue;
                    flag = true;
                }

                if (!moveflag && A[i] == A[i - 1]) {
                    if (j == -1) {
                        j = i + 1;
                        for (; j < A.length; j++) {
                            if (A[j] - A[j - 1] > 1) {
                                nextValue = A[j - 1] + 1;
                                flag = false;
                                break;
                            }
                        }
                    } else if (nextValue < A[A.length - 1]) {
                        for (; j < A.length; j++) {
                            if (A[j] - nextValue > 1) {
                                nextValue = nextValue + 1;
                                flag = false;
                                break;
                            }
                        }
                    }
                    if (flag) {
                        nextValue = temp++;
                    }
                    re += nextValue - A[i];
                    moveflag = true;
                    last = A[i];
                    A[i] = nextValue;
                    flag = true;
                }
                moveflag = false;
            }

            return re;
        }
    }

    /**
     * 不用真正变动数组的值
     */
    class Solution2 {
        public int minIncrementForUnique(int[] A) {
            if (null == A || A.length == 0)
                return 0;
            int re = 0;
            Arrays.sort(A);
            int prev = A[0];
            for (int i = 1; i < A.length; i++) {
                if (A[i] <= prev) {
                    prev += 1;
                    re += prev - A[i];
                } else {
                    prev = A[i];
                }
            }
            return re;
        }
    }

    class SolutionOnLeetCode1 {
        public int minIncrementForUnique(int[] A) {
            int[] a = new int[41000];
            for (int i = 0; i < A.length; i++)
                a[A[i]]++;
            int count = 0;
            for (int i = 0; i < 41000; i++) {
                if (a[i] > 0) {
                    int cha = a[i] - 1;
                    count += cha;
                    a[i + 1] += cha;
                }
            }
            return count;
        }
    }

    @Test
    public void test() {
        int[] a = new int[]{3, 2, 1, 2, 1, 7};
        Solution1 s = new Solution1();
        int re = s.minIncrementForUnique(a);
        System.out.println(re);
    }
}
