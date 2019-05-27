package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Auther: L'Nan
 * @Date: 2019/2/28 22:15
 * @Description:
 */
public class LongestTurbulentSubarray978 {
    /**
     * wrong answer
     */
    class Solution {
        public int maxTurbulenceSize(int[] A) {
            int temp[] = new int[A.length - 1];
            for (int i = 0; i < A.length - 1; i++) {
                if (A[i] == A[i + 1]) {
                    temp[i] = 2;
                    continue;
                }
                temp[i] = A[i] > A[i + 1] ? 1 : 0;
            }


            System.out.println(Arrays.toString(temp));

            return function(temp);
        }

        private int function(int[] temp) {
            if (temp.length == 0) return 1;
            if (temp.length == 1 && temp[0] != 2)
                return 2;
            if (temp.length == 1 && temp[0] == 2)
                return 1;
            int re = 2;
            int i = 1;
            for (; i < temp.length; i++) {
                if (temp[i] == 2) {
                    i = i == temp.length - 1 ? i : i + 1;
                    re = 1;
                    break;
                }
                if (temp[i] != temp[i - 1]) {
                    re++;
                } else {
                    break;
                }
            }
            System.out.println(Arrays.toString(Arrays.copyOfRange(temp, i, temp.length)));
            System.out.println("re=" + re);
            re = Math.max(re, function(Arrays.copyOfRange(temp, i, temp.length)));
            return re;
        }

    }

    @Test
    public void test1() {
        int[] A = new int[]{9, 4, 2, 10, 7, 8, 8, 1, 9};
        int re = new Solution().maxTurbulenceSize(A);
        System.out.println("re=" + re);
    }

    @Test
    public void test2() {
        int[] A = new int[]{0, 1, 1, 0, 1, 0, 1, 1, 0, 0};
        int re = new Solution().maxTurbulenceSize(A);
        System.out.println("re=" + re);
    }

    @Test
    public void test3() {
        int[] A = new int[]{100, 100, 100};
        int re = new Solution().maxTurbulenceSize(A);
        System.out.println("re=" + re);
    }

    /**
     * 虫取法(个人理解还是遍历一遍数组，用了两个游标+规则)
     */
    class SolutionInsectMethod {
        public int maxTurbulenceSize(int[] A) {
            int N = A.length;
            if (N == 1) return 1;
            int res = 1;
            int left = 0, right = 1;
            boolean isde = false;
            while (right < N) {
                if (A[right] == A[right - 1]) {
                    left = right;
                    right++;
                } else if (right - left == 1 || (A[right] - A[right - 1] < 0 != isde)) {
                    isde = A[right] - A[right - 1] < 0;
                    res = Math.max(res, right - left + 1);
                    right++;
                } else {
                    left = right - 1;
                }
            }
            return res;
        }
    }

    /**
     * leetcode上提交：11ms
     * 省去了游标
     */
    class SolutionOnLeetCode11ms {
        public int maxTurbulenceSize(int[] A) {
            switch (A.length) {
                case 0:
                    return 0;
                case 1:
                    return 1;
            }
            int max = 0;
            int count = 2;
            for (int i = 1; i < A.length - 1; i++) {
                if (A[i] > A[i + 1] && A[i] > A[i - 1] || A[i] < A[i + 1] && A[i] < A[i - 1]) {
                    count += 1;
                } else if (A[i] == A[i + 1] && A[i] == A[i - 1]) {
                    count = 1;
                } else {
                    if (max < count) {
                        max = count;
                    }
                    count = 2;
                }
            }
            if (max < count) {
                max = count;
            }
            return max;
        }
    }


}
