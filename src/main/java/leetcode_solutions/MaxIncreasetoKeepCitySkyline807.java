package leetcode_solutions;

import org.junit.Test;

public class MaxIncreasetoKeepCitySkyline807 {
    class Solution {
        public int maxIncreaseKeepingSkyline(int[][] grid) {
            int len = grid.length;
            int[] l = new int[len];
            int[] t = new int[len];
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    l[i] = grid[i][j] > l[i] ? grid[i][j] : l[i];
                    t[j] = grid[i][j] > t[j] ? grid[i][j] : t[j];
                }
            }
//            System.out.println(Arrays.toString(l));
//            System.out.println(Arrays.toString(t));

            int re = 0;
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    int min = l[i] > t[j] ? t[j] : l[i];
                    re += (min - grid[i][j]);
                }
            }
            return re;
        }
    }

    @Test
    public void test() {
        int[][] g = new int[][]{{3, 0, 8, 4}, {2, 4, 5, 7}, {9, 2, 6, 3}, {0, 3, 1, 0}};
        System.out.println(new Solution().maxIncreaseKeepingSkyline(g));
    }
}
