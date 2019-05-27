package leetcode_solutions.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: L'Nan
 * Date: 2019/4/22 13:53
 * Description:
 */
public class BeautifulArrangementII667 {
    class Solution {
        public int[] constructArray(int n, int k) {
            int[] re = new int[n];
            int index = 0;
            for (int i = 1, j = n; i <= j; ) {
                if (k > 1) {
                    if (k % 2 == 0)
                        re[index++] = i++;
                    else
                        re[index++] = j--;
                    k--;
                } else {
                    if (k % 2 == 0)
                        re[index++] = i++;
                    else
                        re[index++] = j--;
                }
            }
            return re;
        }
    }

    class SolutionOnLeetCode1 {
        public int[] constructArray(int n, int k) {
            if (k >= n) return null;
            int[] res = new int[n];
            int pre = 1;
            int rear = n;
            int i = 0;
            while (i < k) {
                res[i++] = pre++;
                if (i < k) res[i++] = rear--;
            }
            if (k % 2 == 0) {
                while (i < n)
                    res[i++] = rear--;
            }
            if (i % 2 == 1) {
                while (i < n)
                    res[i++] = pre++;
            }
            return res;
        }
    }
}
