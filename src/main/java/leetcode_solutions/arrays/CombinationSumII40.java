package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII40 {

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
        if (index < c.length - 1 && c[index] != c[index + 1]) {
            DFS(index + 1, c, target);
        }
    }

    @Test
    public void test() {
        int[] candidates = new int[]{10, 1, 2, 7, 6, 1, 5};
        combinationSum2(candidates, 8);
    }

}
