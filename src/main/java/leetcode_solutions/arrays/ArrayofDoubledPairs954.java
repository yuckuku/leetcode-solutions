package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.*;

/**
 * @author: L'Nan
 * Date: 2019/6/10 17:17
 * Description:
 */
public class ArrayofDoubledPairs954 {
    class Solution {
        public boolean canReorderDoubled(int[] A) {
            int len = A.length;
            Map<Integer, Integer> map = new TreeMap<>();
            for (int i = 0; i < len; i++) {
                map.put(A[i], map.getOrDefault(A[i], 0) + 1);
            }
            int m, n;

            for (int key : map.keySet()) {
                if (map.get(key) == 0) {
                    continue;
                }
                m = map.get(key);
                if (key < 0) {
                    key = key / 2;//比如-4在treemap中排在-2前面，所以是key/2

                } else {
                    key = key * 2;//比如2在treemap中排在4前面，所以是key*2
                }
                n = map.getOrDefault(key, 0);
                if (m > n) {
                    return false;
                }
                map.put(key, n - m);
            }

            return true;
        }
    }

    class SolutionOnLeetcode1 {
        public boolean canReorderDoubled(int[] A) {

            short z[] = new short[100001];
            short f[] = new short[100001];

            int s = 0;
            int ma1 = 0;
            int ma2 = 0;
            for (int n : A) {
                if (n >= 0) {
                    ++z[n];
                    ++s;
                    if (n > ma1) {
                        ma1 = n;
                    }
                } else {
                    n = -n;
                    ++f[n];
                    if (n > ma2) {
                        ma2 = n;
                    }
                }
            }
            if ((s & 1) != 0 || (z[0] & 1) != 0) {
                return false;
            }


            int b = ma1 >> 1;
            for (int i = 1; i <= b; ++i) {
                if (z[i] > 0) {
                    z[i << 1] -= z[i];
                } else if (z[i] < 0) {
                    return false;
                }
            }
            for (int i = b + 1; i <= ma1; ++i) {
                if (z[i] != 0) {
                    return false;
                }
            }
            b = ma2 >> 1;
            for (int i = 1; i <= b; ++i) {
                if (f[i] > 0) {
                    f[i << 1] -= f[i];
                } else if (f[i] < 0) {
                    return false;
                }
            }
            for (int i = b + 1; i <= ma2; ++i) {
                if (f[i] != 0) {
                    return false;
                }
            }

            return true;
        }
    }

    @Test
    public void test() {
        int[] A = new int[]{4, -2, 2, -4};
        Solution s = new Solution();
        boolean re = s.canReorderDoubled(A);
        System.out.println(re);
    }
}
