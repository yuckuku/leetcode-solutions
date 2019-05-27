package leetcode_solutions.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: L'Nan
 * @Date: 2018/11/26 16:15
 * @Description:
 */
public class FourSumII454 {
    /**
     * no clue:wrong answer.
     */
    class Solution {
        public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
            int re = 0;
            Arrays.sort(A);
            Arrays.sort(B);
            Arrays.sort(C);
            Arrays.sort(D);

            int a = 0, b = 0, c = 0, d = 0;
            while (a < A.length && A[a] < 0 && b < B.length && B[b] < 0 && c < C.length && C[c] < 0 && d < D.length && D[d] < 0) {
                a++;
                b++;
                c++;
                d++;
            }

            while (a < A.length && b < B.length && c < C.length && d < D.length) {
                int sum = A[a] + B[b] + C[c] + D[d];
                if (sum == 0) {
                    re++;
                } else if (sum < 0) {

                } else {

                }


            }

            return re;
        }
    }

    /**
     * solution:use hash map to traverse all possible tuples
     */
    class Solution_hashmap {
        public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {

            Map<Integer, Integer> E = new HashMap<>();
            for (int a : A) {
                for (int b : B) {
                    int key = a + b;
                    if (!E.containsKey(key)) {
                        E.put(key, 1);
                    } else {
                        E.put(key, E.get(key) + 1);
                    }
                }
            }

            int ans = 0;
            for (int c : C) {
                for (int d : D) {
                    int key = -c - d;
                    if (E.containsKey(key)) {
                        ans += E.get(key);
                    }
                }
            }

            return ans;
        }
    }

    /**
     * @description: fastest solution on leetcode, also use hash map.
     */
    class Solution_leetcode {
        public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < C.length; i++) {
                for (int j = 0; j < D.length; j++) {
                    int sum = C[i] + D[j];
                    map.put(sum, map.getOrDefault(sum, 0) + 1);
                }
            }
            int res = 0;
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < B.length; j++) {
                    res += map.getOrDefault(-1 * (A[i] + B[j]), 0);
                }
            }
            return res;
        }
    }
}
