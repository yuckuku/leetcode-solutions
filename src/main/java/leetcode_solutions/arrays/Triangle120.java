package leetcode_solutions.arrays;

import java.util.Arrays;
import java.util.List;

/**
 * @author: L'Nan
 * Date: 2019/4/8 10:11
 * Description:
 */
public class Triangle120 {

    class Solution {
        public int minimumTotal(List<List<Integer>> triangle) {
            for (int i = 1; i < triangle.size(); i++) {
                List<Integer> list = triangle.get(i);
                List<Integer> preList = triangle.get(i - 1);
                int size = list.size();
                int preSize = preList.size();

                //first element
                list.set(0, list.get(0) + preList.get(0));
                //last element
                list.set(size - 1, list.get(size - 1) + preList.get(preSize - 1));
                //mid elements
                for (int j = 1; j < size - 1; j++) {
                    int sum = list.get(j) + Math.min(preList.get(j - 1), preList.get(j));
                    list.set(j, sum);
                }
            }
            int max = Integer.MAX_VALUE;
            for (Integer a :
                    triangle.get(triangle.size() - 1)) {
                max = Math.min(max, a);
            }
            return max;
        }
    }

    /**
     * alse dynamic planning
     */
    class SolutionOnLeetCode1 {
        public int minimumTotal(List<List<Integer>> triangle) {
            int h = triangle.size();
            int[][] memo = new int[h][h];
            if (h == 1) {
                return triangle.get(0).get(0);
            }
            return helper(1, 0, memo, triangle);
        }

        private int helper(int n, int i, int[][] memo, List<List<Integer>> tr) {
            if (n == tr.size()) {
                memo[n - 1][i] = tr.get(n - 1).get(i);
                return memo[n - 1][i];
            }
            if (memo[n][i] != 0 && memo[n][i + 1] != 0) {
                memo[n - 1][i] = Math.min(memo[n][i], memo[n][i + 1]) + tr.get(n - 1).get(i);
            }
            if (memo[n][i] != 0 && memo[n][i + 1] == 0) {
                memo[n - 1][i] = Math.min(memo[n][i], helper(n + 1, i + 1, memo, tr)) + tr.get(n - 1).get(i);
            }
            if (memo[n][i] == 0 && memo[n][i + 1] != 0) {
                memo[n - 1][i] = Math.min(helper(n + 1, i, memo, tr), memo[n][i + 1]) + tr.get(n - 1).get(i);
            }
            if (memo[n][i] == 0 && memo[n][i + 1] == 0) {
                memo[n - 1][i] = Math.min(helper(n + 1, i, memo, tr), helper(n + 1, i + 1, memo, tr)) + tr.get(n - 1).get(i);
            }
            return memo[n - 1][i];
        }
    }
}
