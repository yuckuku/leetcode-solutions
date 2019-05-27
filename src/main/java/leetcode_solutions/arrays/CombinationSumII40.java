package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII40 {
    class Solution {

        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        int path[] = new int[1000];
        int len = 0;

        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            Arrays.sort(candidates);
            DFS(0, candidates, target);
            return ans;
        }

        private void DFS(int index, int[] c, int target) {
            if (target == 0) {
                //ans记录
                List<Integer> tmp = new ArrayList<Integer>();
                for (int i = 0; i < len; i++) {
                    tmp.add(path[i]);
                }
                ans.add(tmp);
                return;
            }

            if (target < 0) {
                len--;
                return;
            }

            if (index >= c.length) {
                return;
            }

            //use c[index]
            path[len] = c[index];
            len++;
            DFS(index + 1, c, target - c[index]);
            //not use c[index]
            len--;
            while (index < c.length - 1 && c[index] == c[index + 1]) {
                index++;
            }
            DFS(index + 1, c, target);
        }
    }

    static class SolutionOnLeetCode1 {
        public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
            Arrays.sort(candidates);
            List<List<Integer>> result = new ArrayList<>();
            combination(result, 0, candidates, target, 0, new ArrayList<>());
            return result;
        }

        private static void combination(List<List<Integer>> result, int pos,
                                        int[] candidates, int target, int sum, List<Integer> saved) {
            for (int i = pos; i < candidates.length; i++) {
                int candidate = candidates[i];
                if (sum + candidate < target) {
                    if (i > pos && candidate == candidates[i - 1]) continue;

                    sum += candidate;
                    saved.add(candidate);
                    combination(result, i + 1, candidates, target, sum, saved);
                    sum -= candidate;
                    saved.remove(saved.size() - 1);
                } else if (sum + candidates[i] == target) {
                    List<Integer> l = new ArrayList<>(saved);
                    l.add(candidates[i]);
                    result.add(l);
                    break;
                } else {
                    break;
                }
            }
        }
    }


    @Test
    public void test() {
        int[] candidates = new int[]{10, 1, 2, 7, 6, 1, 5};
        new Solution().combinationSum2(candidates, 8);
    }

}
