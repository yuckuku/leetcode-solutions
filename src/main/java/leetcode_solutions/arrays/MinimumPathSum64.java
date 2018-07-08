package leetcode_solutions.arrays;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class MinimumPathSum64 {


    public int minPathSum(int[][] grid) {
        if (grid.length == 1) {
            int re = 0;
            for (int temp :
                    grid[0]) {
                re += temp;
            }
            return re;
        } else if (grid.length == 2 && grid[0].length == 1 && grid[1].length == 1) {
            return grid[0][0] + grid[1][0];
        } else {
            int re1 = grid[grid.length - 1][grid[0].length - 1];
            int re2 = grid[grid.length - 1][grid[0].length - 1];
            int[][] grid1 = new int[grid.length - 1][grid[0].length];
            for (int i = 0; i < grid.length - 1; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    grid1[i][j] = new Integer(grid[i][j]);
                }
            }

            int temp = minPathSum(grid1);
            re1 += temp;

            int[][] grid2 = new int[grid.length][grid[0].length - 1];
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length - 1; j++) {
                    grid2[i][j] = new Integer(grid[i][j]);
                }
            }

            re2 += minPathSum(grid2);

            return Math.min(re1, re2);
        }
    }


    public int minPathSum0(int[][] grid) {
        for (int i = 1; i < grid.length; i++) grid[i][0] += grid[i - 1][0];
        for (int j = 1; j < grid[0].length; j++) grid[0][j] += grid[0][j - 1];
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                grid[i][j] = Math.min(grid[i][j - 1], grid[i - 1][j]) + grid[i][j];
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }


        @Test
    public void test() {
        int[][] grid = new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        int path = minPathSum(grid);
        System.out.println("mint path is:" + path);
//      int i=  grid[grid.length - 1][grid.length - 1];
//        int[][] temp =   Arrays.copyOfRange(grid, 0, grid.length - 1);

    }

    public int minPathSum1(int[][] grid) {
        return solution1(grid);
    }


    //思路1：从上到下递归方式、

    //1、定义 dp 记录每一个点的 最短路径之和、 可使用 二维 方便记录、

    //2、计算 0,0 点的最短路径之和= 0,0点值 + Min(0,0右邻点最短路径之和,0,0下邻点最短路径之和 可能只有一个邻点 即一条路径！)

    //3、保证右邻、下邻节点 最优[最短] 递归到最内层 不再有 右邻、下邻 节点 返回该点的 值 已是 最优解、、上一层利用它 求解可得上层最优解、


    //4、总的来说：从上到下、递归到最内层 最小的子问题【最优解】得以解决、将结果返回上一层 上一层 经 min 最优判断 也得到了最优解、

    //直到最顶层的子问题 也是最优解、、从下到上 是解决 n 时 前面的所有子问题 都已经是最优解、


    public int solution1(int[][] grid) {
        if(grid.length>0 && grid[0].length>0)
        {
            //取下标记得判断
            int[][] dp=new int[grid.length][grid[0].length];
            return fromTop(0,0,grid,dp);
            //return fromBottom(grid,dp);
        }else{
            return -1;
        }
    }

    public int fromTop(int i,int j,int[][] grid,int[][] dp) {


        //书写递归逻辑:先写 dp[i][j]

        //没有 右邻(i,j+1)、下邻(i+1,j)的 则只有一条路径

        //只有右邻

        //加上记忆化搜索

        //递归终止条件、不重复调用 自己 、、即 递归结束、即 最短路径 本身即是 自己、

        if(i==grid.length-1 && j==grid[0].length-1)
        {
            return grid[i][j];
        }

        //i==grid.length-1 j!=grid[0].length-1  或者  i!=grid.length-1 j==grid[0].length-1 或者 i!=grid.length-1 j!=grid[0].length-1

        //才会走下面 所以 只需要 判断 一个即可、i==grid.length-1 那么 一定  j!=grid[0].length-1！！

        if(dp[i][j]==0)
        {
            //只有一个相邻节点的 情况、
            if(i+1==grid.length)
            {
                dp[i][j]=grid[i][j]+fromTop(i,j+1,grid,dp); //fromTop(i,j+1,grid,dp)表示 i,j+1 点处的 最短路径总和、
                //return dp[i][j];
            }else if(j+1==grid[0].length)
            {
                dp[i][j]=grid[i][j]+fromTop(i+1,j,grid,dp);
                //return dp[i][j];
                //均存在
            }else
            {
                dp[i][j]=grid[i][j]+Min(fromTop(i,j+1,grid,dp),fromTop(i+1,j,grid,dp));
                //return dp[i][j];
            }
        }


        return dp[i][j];


    }

    //动态规划：使用 从下到上 的方式、初始化 + 循环、[填表dp每个点处的最优值] 填每个节点的最优值、

    public int fromBottom(int[][] grid,int[][] dp) {

        dp[0][0]=grid[0][0];

        //0,1--0,j   1,0  1,j

        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[0].length;j++)
            {
                if(i==0 && j==0) continue;
                //对于 i、j 而言 i-1,j上节点、 i,j-1左节点、
                //上节点不存在 也就是第一行、

                //左节点不存在 也就是 第一列、

                //如果 第一行单独处理 第一列单独处理(包括第一行、第一列交叉点0,0) 使用 if if 结构 然后再使用 if 处理 >第1行 >第一列

                //如果 第一行单独处理 第一列单独处理(但不包括第一行) 使用 if else if   最后使用 else 处理 既不是第一行 也不是第一列的

                if(i==0)
                {
                    dp[i][j]=grid[i][j]+dp[i][j-1];
                }else if(j==0)
                {
                    //第一列 且不包括第一行、即不包括 0,0
                    dp[i][j]=grid[i][j]+dp[i-1][j];
                }else{
                    //既非第一行 也非 第一列、
                    dp[i][j]=grid[i][j]+Min(dp[i-1][j],dp[i][j-1]);
                }

            }
        }

        return dp[grid.length-1][grid[0].length-1];
    }

    public int Min(int i,int j) {
        return i<j?i:j;
    }
}
