package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: Administrator
 * @Date: 2018/11/5 20:41
 * @Description: A zero-indexed array A of length N contains all integers from 0 to N-1. Find and return the longest length of set S, where S[i] = {A[i], A[A[i]], A[A[A[i]]], ... } subjected to the rule below.
 * Suppose the first element in S starts with the selection of element A[i] of index = i, the next element in S should be A[A[i]], and then A[A[A[i]]]… By that analogy, we stop adding right before a duplicate element occurs in S.
 */
public class ArrayNesting565 {
    /**
     * @param nums
     * @return
     * @description:my solution: voilent traverse, time limit exceeded
     */
    public int arrayNesting(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            set.clear();
            set.add(nums[i]);
            int nextIndex = nums[i];
            while (nextIndex < nums.length && !set.contains(nums[nextIndex])) {
                set.add(nums[nextIndex]);
                nextIndex = nums[nextIndex];
            }
            max = Math.max(max, set.size());
        }
        return max;
    }


    /**
     * @param nums
     * @return
     * @description:The array contains all integers in the range [0, N - 1]，也就是说不存在一个点的入度为2。每个点的出度和入度都是1。(即不存在勺子环，只存在真正的环)
     */
    public int arrayNesting0(int[] nums) {
        int maxLength = -1;
        for (int firstIndex = 0; firstIndex <= nums.length - 1; firstIndex++) {
            int length = 0;
            while (nums[firstIndex] != -1) {
                length++;
                int firstTemp = nums[firstIndex];
                nums[firstIndex] = -1;
                firstIndex = firstTemp;
            }
            maxLength = maxLength > length ? maxLength : length;
        }
        return maxLength;
    }

    @Test
    public void test() {
        //max=4
        int[] nums = new int[]{5, 4, 0, 3, 1, 6, 2};

        int max = arrayNesting(nums);
        System.out.println(max);
    }

    /**
     * another thought
     * 并查集:解决动态连通性一类问题的一种算法
     */
    public class Solution {
        class UnionFind {
            private int count = 0;
            private int[] parent, rank;

            public UnionFind(int n) {
                count = n;
                parent = new int[n];
                rank = new int[n];
                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                }
            }

            public int find(int p) {
                int q = parent[p];
                while (q != parent[q]) {
                    q = parent[q];
                }
                parent[p] = q;
                return q;
            }

            public void union(int p, int q) {
                int rootP = find(p);
                int rootQ = find(q);
                if (rootP == rootQ) return;
                if (rank[rootQ] > rank[rootP]) {
                    parent[rootP] = rootQ;
                }
                else {
                    parent[rootQ] = rootP;
                    if (rank[rootP] == rank[rootQ]) {
                        rank[rootP]++;
                    }
                }
                count--;
            }

            public int count() {
                return count;
            }

            public int getMaxUnion() {
                Map<Integer, Integer> map = new HashMap<>();
                int max = 1;
                for (int i = 0; i < parent.length; i++) {
                    int p = find(i);
                    map.put(p, map.getOrDefault(p, 0) + 1);
                    max = Math.max(max, map.get(p));
                }
                return max;
            }
        }

        public int arrayNesting(int[] nums) {
            int n = nums.length;
            UnionFind uf = new UnionFind(n);
            for (int i = 0; i < n; i++) {
                uf.union(i, nums[i]);
            }
            return uf.getMaxUnion();
        }
    }
}
