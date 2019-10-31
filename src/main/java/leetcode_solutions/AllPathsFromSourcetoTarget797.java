package leetcode_solutions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AllPathsFromSourcetoTarget797 {
    class Solution {
        public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
            return allPathsSourceTarget(graph, 0);
        }

        /**
         * 实际处理
         *
         * @param graph 图
         * @param n     当前是第几个节点
         * @return 路径
         */
        private List<List<Integer>> allPathsSourceTarget(int[][] graph, int n) {
            List<List<Integer>> lists = new ArrayList<>();
            // 如果当前是最后一个节点，则直接返回一条路径，路径只有当前节点
            if (n == graph.length - 1) {
                List<Integer> path = new ArrayList<>();
                path.add(graph.length - 1);
                lists.add(path);
                return lists;
            }
            // 遍历该节点可以通往其他节点的路，将当前节点添加在路径最前
            for (int i : graph[n]) {
                for (List<Integer> path : allPathsSourceTarget(graph, i)) {
                    path.add(0, n);
                    lists.add(path);
                }
            }
            return lists;
        }

    }

    class Solution1 {
        public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
            // 当做一个树来做（深度优先）遍历，找出所有到达终点的路径
            LinkedList<Integer> path = new LinkedList<>();
            List<List<Integer>> result = new LinkedList<>();

            // 从0开始遍历
            path.addLast(0);
            allPathSourceTarget(graph, 0, path, result);
            path.removeLast();

            return result;
        }

        private void allPathSourceTarget(int[][] graph, int offset, LinkedList<Integer> path, List<List<Integer>> result) {
            // 到达最后一个节点，说明这条路径是通的
            if (offset == (graph.length - 1)) {
                List<Integer> successPath = new LinkedList<>(path);
                result.add(successPath);
            } else {
                for (int i = 0; i < graph[offset].length; ++i) {
                    int next = graph[offset][i];
                    path.addLast(next);
                    allPathSourceTarget(graph, next, path, result);
                    path.removeLast();
                }
            }
        }
    }

}
