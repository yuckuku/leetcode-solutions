package leetcode_solutions.arrays;

import org.junit.Test;

import java.awt.event.HierarchyBoundsAdapter;

/**
 * @author: L'Nan
 * Date: 2019/5/11 22:15
 * Description:
 */
public class WordSearch79 {
    class Solution {
        public boolean exist(char[][] board, String word) {
            boolean[][] visited = new boolean[board.length][board[0].length];
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (dfs(board, word, 0, i, j, visited)) {
                        return true;
                    }

                }
            }
            return false;
        }

        private boolean dfs(char[][] board, String word, int index, int x, int y, boolean[][] visited) {
            //终止条件
            if (index == word.length()) {
                return true;
            }
            //越界
            if (x < 0 || y < 0 || x >= board.length || y >= board[0].length) {
                return false;
            }
            //已经访问
            if (visited[x][y]) {
                return false;
            }
            //不相等
            if (board[x][y] != word.charAt(index)) {
                return false;
            }
            visited[x][y] = true;
            boolean result = dfs(board, word, index + 1, x - 1, y, visited) || dfs(board, word, index + 1, x + 1, y, visited) || dfs(board, word, index + 1, x, y - 1, visited) || dfs(board, word, index + 1, x, y + 1, visited);//分别为上下左右地搜索.
            visited[x][y] = false; //准备回溯,设置回未访问状态.
            return result;
        }


    }

    @Test
    public void test() {
        boolean[][] visited = new boolean[1][1];
        System.out.println(visited[0][0]);
    }
}
