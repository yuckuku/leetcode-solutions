package leetcode_solutions;

import org.junit.Test;

import java.util.LinkedList;

public class NumberofIslands200 {
    /**
     * 方法一：深度优先遍历
     */
    public class Solution {

        //           x-1,y
        //  x,y-1    x,y      x,y+1
        //           x+1,y
        // 方向数组，它表示了相对于当前位置的 4 个方向的横、纵坐标的偏移量，这是一个常见的技巧
        private final int[][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        // 标记数组，标记了 grid 的坐标对应的格子是否被访问过
        private boolean[][] marked;
        // grid 的行数
        private int rows;
        // grid 的列数
        private int cols;
        private char[][] grid;

        public int numIslands(char[][] grid) {
            rows = grid.length;
            if (rows == 0) {
                return 0;
            }
            cols = grid[0].length;
            this.grid = grid;
            marked = new boolean[rows][cols];
            int count = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    // 如果是岛屿中的一个点，并且没有被访问过
                    // 就进行深度优先遍历
                    if (!marked[i][j] && grid[i][j] == '1') {
                        count++;
                        dfs(i, j);
                    }
                }
            }
            return count;
        }

        // 从坐标为 (i,j) 的点开始进行深度优先遍历
        private void dfs(int i, int j) {
            marked[i][j] = true;
            // 得到 4 个方向的坐标
            for (int k = 0; k < 4; k++) {
                int newX = i + directions[k][0];
                int newY = j + directions[k][1];
                // 如果不越界、没有被访问过、并且还要是陆地
                if (inArea(newX, newY) && grid[newX][newY] == '1' && !marked[newX][newY]) {
                    dfs(newX, newY);
                }
            }
        }

        // 封装成 inArea 方法语义更清晰
        private boolean inArea(int x, int y) {
            // 等于号不要忘了
            return x >= 0 && x < rows && y >= 0 && y < cols;
        }

    }

    @Test
    public void test() {
        Solution solution = new Solution();
        char[][] grid1 = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}};
        int numIslands1 = solution.numIslands(grid1);
        System.out.println(numIslands1);

        System.out.println("--------------------");

        char[][] grid2 = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}};
        int numIslands2 = solution.numIslands(grid2);
        System.out.println(numIslands2);
    }


    /**
     * 方法二：广度优先遍历
     */
    public class Solution2 {


        private int rows;
        private int cols;

        public int numIslands(char[][] grid) {
            //           x-1,y
            //  x,y-1    x,y      x,y+1
            //           x+1,y
            int[][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

            rows = grid.length;
            if (rows == 0) {
                return 0;
            }
            cols = grid[0].length;
            boolean[][] marked = new boolean[rows][cols];
            int count = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    // 如果是岛屿中的一个点，并且没有被访问过
                    // 从坐标为 (i,j) 的点开始进行广度优先遍历
                    if (!marked[i][j] && grid[i][j] == '1') {
                        count++;
                        LinkedList<Integer> queue = new LinkedList<>();
                        // 小技巧：把坐标转换为一个数字
                        // 否则，得用一个数组存，在 Python 中，可以使用 tuple 存
                        queue.addLast(i * cols + j);
                        // 注意：这里要标记上已经访问过
                        marked[i][j] = true;
                        while (!queue.isEmpty()) {
                            int cur = queue.removeFirst();
                            int curX = cur / cols;
                            int curY = cur % cols;
                            // 得到 4 个方向的坐标
                            for (int k = 0; k < 4; k++) {
                                int newX = curX + directions[k][0];
                                int newY = curY + directions[k][1];
                                // 如果不越界、没有被访问过、并且还要是陆地，我就继续放入队列，放入队列的同时，要记得标记已经访问过
                                if (inArea(newX, newY) && grid[newX][newY] == '1' && !marked[newX][newY]) {
                                    queue.addLast(newX * cols + newY);
                                    // 【特别注意】在放入队列以后，要马上标记成已经访问过，语义也是十分清楚的：反正只要进入了队列，你迟早都会遍历到它
                                    // 而不是在出队列的时候再标记
                                    // 【特别注意】如果是出队列的时候再标记，会造成很多重复的结点进入队列，造成重复的操作，这句话如果你没有写对地方，代码会严重超时的
                                    marked[newX][newY] = true;
                                }
                            }
                        }
                    }
                }

            }
            return count;
        }

        private boolean inArea(int x, int y) {
            // 等于号这些细节不要忘了
            return x >= 0 && x < rows && y >= 0 && y < cols;
        }

    }

    @Test
    public void test2() {
        Solution2 solution2 = new Solution2();
        char[][] grid1 = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}};
        int numIslands1 = solution2.numIslands(grid1);
        System.out.println(numIslands1);

        char[][] grid2 = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}};
        int numIslands2 = solution2.numIslands(grid2);
        System.out.println(numIslands2);
    }

    //并查集
    class Solution3 {
        class UnionFind {
            int count; // # of connected components
            int[] parent;
            int[] rank;

            public UnionFind(char[][] grid) { // for problem 200
                count = 0;
                int m = grid.length;
                int n = grid[0].length;
                parent = new int[m * n];
                rank = new int[m * n];
                for (int i = 0; i < m; ++i) {
                    for (int j = 0; j < n; ++j) {
                        if (grid[i][j] == '1') {
                            parent[i * n + j] = i * n + j;
                            ++count;
                        }
                        rank[i * n + j] = 0;
                    }
                }
            }

            public int find(int i) { // path compression
                if (parent[i] != i) parent[i] = find(parent[i]);
                return parent[i];
            }

            public void union(int x, int y) { // union with rank
                int rootx = find(x);
                int rooty = find(y);
                if (rootx != rooty) {
                    if (rank[rootx] > rank[rooty]) {
                        parent[rooty] = rootx;
                    } else if (rank[rootx] < rank[rooty]) {
                        parent[rootx] = rooty;
                    } else {
                        parent[rooty] = rootx;
                        rank[rootx] += 1;
                    }
                    --count;
                }
            }

            public int getCount() {
                return count;
            }
        }

        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0) {
                return 0;
            }

            int nr = grid.length;
            int nc = grid[0].length;
            int num_islands = 0;
            UnionFind uf = new UnionFind(grid);
            for (int r = 0; r < nr; ++r) {
                for (int c = 0; c < nc; ++c) {
                    if (grid[r][c] == '1') {
                        grid[r][c] = '0';
                        if (r - 1 >= 0 && grid[r - 1][c] == '1') {
                            uf.union(r * nc + c, (r - 1) * nc + c);
                        }
                        if (r + 1 < nr && grid[r + 1][c] == '1') {
                            uf.union(r * nc + c, (r + 1) * nc + c);
                        }
                        if (c - 1 >= 0 && grid[r][c - 1] == '1') {
                            uf.union(r * nc + c, r * nc + c - 1);
                        }
                        if (c + 1 < nc && grid[r][c + 1] == '1') {
                            uf.union(r * nc + c, r * nc + c + 1);
                        }
                    }
                }
            }

            return uf.getCount();
        }
    }


    class Solution1ms {
        private char[][] grid;
        private int r;
        private int c;

        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0) return 0;
            this.grid = grid;
            this.r = grid.length;
            this.c = grid[0].length;

            int count = 0;
            for (int m = 0; m < this.r; m++) {
                for (int n = 0; n < this.c; n++) {
                    if (grid[m][n] == '1') {
                        count++;
                        dfs(m, n);
                    }
                }
            }
            return count;
        }

        private void dfs(int m, int n) {
            if (m < 0 || m >= this.r || n < 0 || n >= this.c || this.grid[m][n] == '0')
                return;
            this.grid[m][n] = '0';
            dfs(m - 1, n);
            dfs(m + 1, n);
            dfs(m, n - 1);
            dfs(m, n + 1);
        }
    }
}
