package leetcode_solutions.arrays;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @author: L'Nan
 * Date: 2019/4/11 20:34
 * Description:
 */
public class ImageOverlap835 {
    /**
     * 向量法
     */
    class Solution {
        public int largestOverlap(int[][] A, int[][] B) {
            int N = A.length;
            List<Point> A2 = new ArrayList(), B2 = new ArrayList();
            for (int i = 0; i < N * N; ++i) {
                if (A[i / N][i % N] == 1) A2.add(new Point(i / N, i % N));
                if (B[i / N][i % N] == 1) B2.add(new Point(i / N, i % N));
            }

            Set<Point> Bset = new HashSet(B2);

            int ans = 0;
            Set<Point> seen = new HashSet();
            for (Point a : A2)
                for (Point b : B2) {
                    Point delta = new Point(b.x - a.x, b.y - a.y);
                    if (!seen.contains(delta)) {
                        seen.add(delta);
                        int cand = 0;
                        for (Point p : A2)
                            if (Bset.contains(new Point(p.x + delta.x, p.y + delta.y)))
                                cand++;
                        ans = Math.max(ans, cand);
                    }
                }

            return ans;
        }
    }

    class SolutionOnLeetCode1 {
        public int largestOverlap(int[][] A, int[][] B) {
            int res = 0;
            int[][] nums = new int[A.length * 2 - 1][A.length * 2 - 1];
            int len = A.length;
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A.length; j++) {
                    if (A[i][j] == 1) {
                        addRes(nums, i, j, B);
                    }
                }
            }
            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; j < nums.length; j++) {
                    if (nums[i][j] > res) {
                        res = nums[i][j];
                    }
                }
            }
            return res;
        }

        public void addRes(int[][] nums, int a, int b, int[][] B) {
            for (int i = 0; i < B.length; i++) {
                for (int j = 0; j < B.length; j++) {
                    if (B[i][j] == 1) {
                        nums[B.length - 1 + (i - a)][B.length - 1 + (j - b)]++;
                    }
                }
            }
        }
    }
}
