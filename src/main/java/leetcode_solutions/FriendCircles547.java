package leetcode_solutions;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class FriendCircles547 {
    class Solution {

        //执行用时:9ms,在所有java提交中击败了24.13%的用户内存消耗:44.3MB,在所有java提交中击败了90.54%的用户
        //上浮
        public int findCircleNum(int[][] M) {
            int N = M.length;

            //遍历M
            for (int i = N - 1; i >= 0; i--) {
                for (int j = N - 1; j >= i + 1; j--) {
                    if (M[i][j] == 1) {
                        //向下遍历，找需要上浮元素
                        for (int k = i + 1; k < N; k++) {
                            if (M[k][k] == 0) continue;
                            if (M[k][j] == 1) {
                                //k行上浮,k行置零
                                for (int l = k; l < N; l++) {
                                    if (M[k][l] == 1) {
                                        M[i][l] = 1;
                                        M[k][l] = 0;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            int re = 0;
            for (int i = 0; i < N; i++) {
                if (M[i][i] == 1) re++;
            }
            return re;
        }
    }

    //dfs
    class Solution1ms {
        public int findCircleNum(int[][] M) {
            if (M.length <= 1) return M.length;

            boolean[] visited = new boolean[M.length];
            int result = 0;

            for (int i = 0; i < visited.length; i++) {
                if (visited[i] == false) {
                    visited[i] = true;
                    dfs(M, visited, i);
                    result++;
                }
            }

            return result;
        }

        private void dfs(int[][] m, boolean[] visited, int i) {
            for (int j = 0; j < m.length; j++) {
                if (visited[j] == false && m[i][j] == 1) {
                    visited[j] = true;
                    dfs(m, visited, j);
                }
            }
        }
    }

    //bfs
    public class Solution1 {
        public int findCircleNum(int[][] M) {
            int[] visited = new int[M.length];
            int count = 0;
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < M.length; i++) {
                if (visited[i] == 0) {
                    queue.add(i);
                    while (!queue.isEmpty()) {
                        int s = queue.remove();
                        visited[s] = 1;
                        for (int j = 0; j < M.length; j++) {
                            if (M[s][j] == 1 && visited[j] == 0)
                                queue.add(j);
                        }
                    }
                    count++;
                }
            }
            return count;
        }
    }

    //并查集
    public class Solution2 {
        int find(int parent[], int i) {
            if (parent[i] == -1)
                return i;
            return find(parent, parent[i]);
        }

        void union(int parent[], int x, int y) {
            int xset = find(parent, x);
            int yset = find(parent, y);
            if (xset != yset)
                parent[xset] = yset;
        }

        public int findCircleNum(int[][] M) {
            int[] parent = new int[M.length];
            Arrays.fill(parent, -1);
            for (int i = 0; i < M.length; i++) {
                for (int j = 0; j < M.length; j++) {
                    if (M[i][j] == 1 && i != j) {
                        union(parent, i, j);
                    }
                }
            }
            int count = 0;
            for (int i = 0; i < parent.length; i++) {
                if (parent[i] == -1)
                    count++;
            }
            return count;
        }
    }

}
