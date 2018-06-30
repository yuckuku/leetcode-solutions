package leetcode_solutions.arrays;

import java.util.ArrayList;
import java.util.List;

public class CombinationSumIII216 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), k, n, 1);

        return list;
    }

    private boolean backtrack(List<List<Integer>> list, List<Integer> tempList, int k, int n, int start) {
        if (n < 0) { // if remain is 0 or less than 0, meaning the rest numbers are even greater
            return false; // therefore, no need to continue the loop, return false
        } else if (n == 0) {
            if (tempList.size() == k) {
                list.add(new ArrayList<>(tempList));
            }
            return false;
        } else {
            for (int i = start; i <= 9; i++) {
                boolean flag;
                tempList.add(i);
                flag = backtrack(list, tempList, k, n - i, i + 1); // not i + 1 because we can use same number.
                tempList.remove(tempList.size() - 1);

                if (!flag) // if find a sum or fail to find a sum, there is no need to continue
                    break;// because it is a sorted array with no duplicates, the rest numbers are even greater.
            }

            return true; // return true because previous tempList didn't find a sum or fail a sum
        }
    }

    List<List<Integer>> ans;

    public List<List<Integer>> combinationSum30(int k, int n) {
        // set = new HashSet<>();
        ans = new ArrayList<>();
        dfs(k, n, 0, 0L);
        return ans;
    }

    void dfs(int k, int n, int pre, long val) {
        if (k == 0 && n == 0) {
            List<Integer> list = new ArrayList<>();
            while (val > 0) {
                list.add((int) (val % 10));
                val /= 10;
            }
            ans.add(list);
            return;
        }
        if (k == 0 || n < 0) return;
        for (int i = pre + 1; i < 10; i++) {
            dfs(k - 1, n - i, i, val * 10 + i);
        }
    }

}
