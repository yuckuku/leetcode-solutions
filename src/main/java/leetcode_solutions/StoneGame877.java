package leetcode_solutions;

import junit.framework.TestCase;
import org.junit.Test;

public class StoneGame877 {
    class Solution {
        public boolean stoneGame(int[] piles) {
            return pick(piles, 0, piles.length - 1, 0, 0);
        }

        //超出时间限制
        private boolean pick(int[] piles, int l, int r, int aV, int lV) {
            //最后一次选取，比较
            if (l > r) {
                if (aV > lV) return true;
                else return false;
            }

            //alex取左边，lee取右边
            if (pick(piles, l + 1, r - 1, aV + piles[l], lV + piles[r])) {
                return true;
            } else if (pick(piles, l + 1, r - 1, aV + piles[r], lV + piles[l])) {
                return true;
            } else if (pick(piles, l + 2, r, aV + piles[l], lV + piles[l + 1])) {
                return true;
            } else if (pick(piles, l, r - 2, aV + piles[r], lV + piles[r - 1])) {
                return true;
            }

            return false;
        }
    }

    class Solution1 {
        public boolean stoneGame(int[] piles) {
            int tmp = sG(piles);
            if (tmp > 0) return true;
            else return false;
        }

        /* 返回游戏最后先手和后手的得分之差 */
        private int sG(int[] piles) {
            int n = piles.length;
            // 初始化 dp 数组
            Pair[][] dp = new Pair[n][n];
            for (int i = 0; i < n; i++)
                for (int j = i; j < n; j++)
                    dp[i][j] = new Pair(0, 0);
            // 填入 base case
            for (int i = 0; i < n; i++) {
                dp[i][i].fir = piles[i];
                dp[i][i].sec = 0;
            }
            // 斜着遍历数组
            for (int l = 2; l <= n; l++) {
                for (int i = 0; i <= n - l; i++) {
                    int j = l + i - 1;
                    // 先手选择最左边或最右边的分数
                    int left = piles[i] + dp[i + 1][j].sec;
                    int right = piles[j] + dp[i][j - 1].sec;
                    // 套用状态转移方程
                    if (left > right) {
                        dp[i][j].fir = left;
                        dp[i][j].sec = dp[i + 1][j].fir;
                    } else {
                        dp[i][j].fir = right;
                        dp[i][j].sec = dp[i][j - 1].fir;
                    }
                }
            }
            Pair res = dp[0][n - 1];
            return res.fir - res.sec;
        }


        class Pair {
            int fir, sec;

            Pair(int fir, int sec) {
                this.fir = fir;
                this.sec = sec;
            }
        }


    }

    class SolutionOnLeetcode1 {
        public boolean stoneGame(int[] piles) {
            return true;
        }
    }

    class SolutionOnLeetcode2 {
        public boolean stoneGame(int[] piles) {
            int left = 0;
            int right = piles.length - 1;
            int ax = 0;
            int li = 0;
            int flagA = 1;//是否alex先手
            while (left < right) {
                if (flagA == 1) {
                    if (piles[left] >= piles[right]) {
                        ax += piles[left];
                        left++;
                    } else {
                        ax += piles[right];
                        right--;
                    }
                } else {
                    if (piles[left] >= piles[right]) {
                        li += piles[left];
                        left++;
                    } else {
                        li += piles[right];
                        right--;
                    }
                }
            }
            return ax > li;
        }
    }

    @Test
    public void test() {
        int[] piles = new int[]{5, 3, 4, 5};
        Solution solution = new Solution();
        boolean re = solution.stoneGame(piles);
        TestCase.assertTrue(re);
    }
}
