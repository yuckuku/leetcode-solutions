package leetcode_solutions;

public class PredicttheWinner486 {
    /**
     * 初始化时，dp[i][i] =nums[i]; 意味着如果只有一个nums[i]可以拿，先手玩家拿走了，nums[i] 也就是多出来的分数
     * dp[i][j]表示先手玩家从nums[i]拿到nums[j]时，比后手玩家多的最大分数
     * 对于dp[i][j]来说，先手玩家有两种拿法，一种是拿开头的数，一种是拿结尾的数
     * 如果先拿了nums[i]，也就是意味着先手玩家目前的分数是nums[i]+后手玩家获得的最大分数的相反值，也就是dp[i][j] = nums[i]+（-dp[i+1][j]）这里的dp[i+1][j]表示是后手玩家比先手玩家多的最大分数，
     * 同理如果先拿了nums[j]，也就是意味着先手玩家目前的分数是nums[j]+后手玩家获得的最大分数的相反值，也就是dp[i][j] = nums[j]+（-dp[i][j-1]）这里的dp[i][j-1]表示是后手玩家比先手玩家多的最大分数
     * 而每一步，先手玩家都想拿到最大的分数，最后才有机会赢，所以最终的转移方程是：dp[i][j] =max{nums[i]+（-dp[i+1][j]）, nums[j]+（-dp[i][j-1]）}
     * 最后要求的值时dp[0][n-1]也就是dp的右上角的数，判断这个数是否大于0，大于0意味着先手玩家比后手玩家多，会赢
     */
    class Solution {
        //执行用时:1ms,在所有Java提交中击败了88.45%的用户内存消耗:34.5MB,在所有Java提交中击败了92.21%的用户
        public boolean PredictTheWinner(int[] nums) {
            int n = nums.length;
            int[][] dp = new int[n][n];
            //初始化
            for (int i = 0; i < n; i++) {
                dp[i][i] = nums[i];
            }
            //DP
            for (int i = n - 1; i >= 0; i--) {
                for (int j = i + 1; j < n; j++) {
                    dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
                }
            }
            return dp[0][n - 1] >= 0;
        }

        //执行用时:1ms,在所有Java提交中击败了88.45%的用户内存消耗:34.4MB,在所有Java提交中击败了92.21%的用户
        public boolean PredictTheWinner2nd(int[] nums) {
            if (nums == null || nums.length == 0) return false;
            int n = nums.length;
            int[][] dp = new int[n][n];
            for (int i = 0; i < n; i++) dp[i][i] = nums[i];
            for (int step = 2; step <= n; step++) {
                for (int i = 0; i < n - step + 1; i++) {
                    int j = i + step - 1;
                    dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
                }
            }
            return dp[0][n - 1] >= 0;
        }

        //执行用时:1ms,在所有Java提交中击败了88.45%的用户内存消耗:34.1MB,在所有Java提交中击败了94.81%的用户
        public boolean PredictTheWinner3rd(int[] nums) {
            if (nums == null || nums.length == 0) return false;
            int n = nums.length;
            int[][] dp = new int[n][n];
            int turn = n & 1;
            int sum = 0;
            for (int i = 0; i < n; i++) {
                if (turn == 1) dp[i][i] = nums[i];
                sum += nums[i];
            }
            for (int step = 2; step <= n; step++) {
                turn ^= 1;
                for (int i = 0; i < n - step + 1; i++) {
                    int j = i + step - 1;
                    if (turn == 1) dp[i][j] = Math.max(nums[i] + dp[i + 1][j], nums[j] + dp[i][j - 1]);
                    else dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]);
                }
            }
            return 2 * dp[0][n - 1] >= sum;
        }

    }

    class Solution0ms {
        public boolean PredictTheWinner(int[] nums) {
            int length = nums.length;
            if (length % 2 == 0) {
                return true;
            }

            int[] dp = new int[length];
            for (int i = length - 1; i >= 0; i--) {
                for (int j = i + 1; j < length; j++) {
                    dp[j] = Math.max(nums[i] - dp[j], nums[j] - dp[j - 1]);
                }
            }
            return dp[length - 1] >= 0;
        }

    }
}
