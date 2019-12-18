package leetcode_solutions;

import java.util.ArrayList;
import java.util.List;

public class FriendCircles547 {
    class Solution {
        private int[][] l;

        public int findCircleNum(int[][] M) {
            int N = M.length;

//            List<int[]> l=new ArrayList<>(N);
            //初始化
            for (int i = 0; i < N; i++) {
                int[] tmp = new int[N];
                tmp[i] = 1;
                l[i] = tmp;
            }

            int re = N;
            //遍历M
            for (int i = N - 1; i >= 0; i--) {
                for (int j = N - 1; j >= i + 1; j--) {
                    if (M[i][j] == 1) {
                        //移动好友关系

                    }
                }

            }

        }


    }


}
