package leetcode_solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Combinations77 {
    class Solution {
        public List<List<Integer>> combine(int n, int k) {
            LinkedList<Integer> path = new LinkedList<>();
            List<List<Integer>> result = new LinkedList<>();
            backtrace(1, n, k, path, result);
            return result;
        }

        private void backtrace(int t, int n, int k, LinkedList<Integer> path, List<List<Integer>> result) {
            if (path.size() == k) {
                List<Integer> successPath = new LinkedList<>(path);
                result.add(successPath);
                return;
            }
            for (int i = t; i <= n; i++) {
                path.add(i);
                backtrace(i + 1, n, k, path, result);
                path.removeLast();
            }
        }
    }

    public class Solution77_1 {
        private LinkedList<List<Integer>> res;

        public List<List<Integer>> combine(int n, int k) {
            res = new LinkedList<>();
            if (n <= 0 || k <= 0 || k > n) {
                return res;
            }
            LinkedList<Integer> c = new LinkedList<>();
            findCombination(n, k, 1, c);
            return res;
        }

        private void findCombination(int n, int k, int start, LinkedList<Integer> c) {
            if (c.size() == k) {
                res.addLast((List<Integer>) c.clone());
                return;
            }
            for (int i = start; i <= n; i++) {
                c.addLast(i);
                findCombination(n, k, i + 1, c);
                c.removeLast();
            }
            return;
        }
    }

    class Solution1 {
        public void combine(List<List<Integer>> res, List<Integer> list, int n, int k, int index) {
            if (list.size() == k) {
                res.add(new ArrayList<>(list));
                return;
            }
            for (int i = (list.size() == 0 ? 1 : list.get(list.size() - 1) + 1); i <= n - k + index + 1; i++) {
                list.add(i);
                combine(res, list, n, k, index + 1);
                list.remove(list.size() - 1);
            }
        }

        public List<List<Integer>> combine(int n, int k) {
            List<List<Integer>> res = new ArrayList<>();
            combine(res, new ArrayList<>(), n, k, 0);
            return res;
        }
    }


    @Test
    public void test() {
        int n = 4;
        int k = 2;
        Solution solution = new Solution();
        List<List<Integer>> re = solution.combine(n, k);
        System.out.println(re);
    }
}
