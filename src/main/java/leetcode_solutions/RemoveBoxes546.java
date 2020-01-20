package leetcode_solutions;

public class RemoveBoxes546 {

    //三维DP
    //记忆化搜索
    //初值为 dp[l][r][k]=dp[l][r−1][0]+(k+1)∗(k+1)
    //dp 的更新式为：dp[l][r][k] = max(dp[l][r][k], dp[l][i][k+1] + dp[i+1][r-1][0])
    //dp[0][n-1][0] 就是最后的结果
    //执行用时:23ms,在所有Java提交中击败了62.20%的用户
    //内存消耗:51.8MB,在所有Java提交中击败了9.00%的用户
    class Solution {
        public int removeBoxes(int[] boxes) {
            int[][][] dp = new int[100][100][100];
            return calculatePoints(boxes, dp, 0, boxes.length - 1, 0);
        }

        public int calculatePoints(int[] boxes, int[][][] dp, int l, int r, int k) {
            if (l > r) return 0;
            if (dp[l][r][k] != 0) return dp[l][r][k];
            while (r > l && boxes[r] == boxes[r - 1]) {
                r--;
                k++;
            }
            dp[l][r][k] = calculatePoints(boxes, dp, l, r - 1, 0) + (k + 1) * (k + 1);
            for (int i = l; i < r; i++) {
                if (boxes[i] == boxes[r]) {
                    dp[l][r][k] = Math.max(dp[l][r][k], calculatePoints(boxes, dp, l, i, k + 1) + calculatePoints(boxes, dp, i + 1, r - 1, 0));
                }
            }
            return dp[l][r][k];
        }

    }

    //暴力法，超时
    public class SolutionTLE {
        public int removeBoxes(int[] boxes) {
            return remove(boxes);
        }

        public int remove(int[] boxes) {
            if (boxes.length == 0)
                return 0;
            int res = 0;
            for (int i = 0, j = i + 1; i < boxes.length; i++) {
                while (j < boxes.length && boxes[i] == boxes[j])
                    j++;
                int[] newboxes = new int[boxes.length - (j - i)];
                for (int k = 0, p = 0; k < boxes.length; k++) {
                    if (k == i)
                        k = j;
                    if (k < boxes.length)
                        newboxes[p++] = boxes[k];
                }
                res = Math.max(res, remove(newboxes) + (j - i) * (j - i));
            }
            return res;
        }
    }


    class Solution4ms {
        int[][] jilu = new int[100][100];

        public int removeBoxes(int[] boxes) {
            int[][] nums = new int[boxes.length][2];
            int tot = 0;
            int count = 0;
            for (int i = 0; i < boxes.length; i++) {
                count = 1;
                while (i + 1 < boxes.length && boxes[i] == boxes[i + 1]) {
                    count++;
                    i++;
                }
                nums[tot][0] = count;
                nums[tot][1] = boxes[i];
                tot++;
            }
            int[][] nums0 = new int[tot][2];
            for (int i = 0; i < tot; i++) {
                nums0[i][0] = nums[i][0];
                nums0[i][1] = nums[i][1];
            }
            //System.out.println("nums0.length="+tot);
            //for(int i = 0; i < nums0.length; i++){
            //System.err.println(nums0[i][0] + " " + nums0[i][1]);
            //}
            int res = DSearch(nums0, 0, nums0.length - 1);
            //System.out.println("di er wei = " + res[1]);
            return res;
        }

        private int DSearch(int[][] nums, int begin, int end) {
            if (begin == end) {
                int result;
                result = (int) Math.pow(nums[begin][0], 2);
                return result;
            }
            if (begin > end)
                return 0;
            int max = 0;
            int sum = 0;
            int count = nums[end][0];
            int left = 0;
            int temp;
            int right = end;
            int mid;
            int midpow;
            int tot = 0;
            int leftrecord;
            boolean flag = false;
            int[] record = new int[50];
            int[] index = new int[50];
            for (int i = end - 1; i >= begin; i--) {
                if (nums[i][1] == nums[end][1]) {
                    if (i - 1 >= 0 && jilu[begin][i - 1] != 0)
                        left = jilu[begin][i - 1];
                    else
                        left = DSearch(nums, begin, i - 1);
                    if (jilu[i + 1][right - 1] != 0)
                        record[tot] = jilu[i + 1][right - 1];
                    else
                        record[tot] = DSearch(nums, i + 1, right - 1);
                    sum += record[tot];
                    index[tot] = i;
                    tot++;
                    if (jilu[i + 1][end - 1] != 0)
                        mid = jilu[i + 1][end - 1];
                    else
                        mid = DSearch(nums, i + 1, end - 1);
                    midpow = nums[end][0] + nums[i][0];
                    count += nums[i][0];
                    if ((temp = left + (int) Math.pow(count, 2) + sum) > max)
                        max = temp;
                    flag = true;
                    right = i;
                    if ((temp = left + mid + (int) Math.pow(midpow, 2)) > max)
                        max = temp;
                }
            }
            //leftrecord = left;
            int sumtemp;
            int total;
            if (tot >= 3) {
                int sum1 = 0;
                //leftrecord = DSearch(nums, begin, index[tot - 1]-1);
                leftrecord = left;
                int count1 = nums[index[tot - 1]][0] + nums[end][0];
                for (int i = tot - 2; i > 0; i--) {
                    count1 += nums[index[i]][0];
                    sum1 += record[i + 1];
                    if (jilu[index[i] + 1][end - 1] != 0)
                        sumtemp = jilu[index[i] + 1][end - 1];
                    else
                        sumtemp = DSearch(nums, index[i] + 1, end - 1);
                    if ((total = leftrecord + sum1 + sumtemp + (int) Math.pow(count1, 2)) > max)
                        max = total;
                }
            }
            int temp1;
            int temp2;
            if (jilu[begin][end - 1] != 0)
                temp1 = jilu[begin][end - 1];
            else
                temp1 = DSearch(nums, begin, end - 1);
            if ((temp2 = temp1 + (int) Math.pow(nums[end][0], 2)) > max)
                max = temp2;
            jilu[begin][end] = max;
            return max;

        }
    }


    class Solution9ms {
        private int[][][] dp;
        private int[] boxes;

        public int removeBoxes(int[] boxes) {
            int n = boxes.length;
            this.boxes = boxes;
            this.dp = new int[n][n][n];

            return DFS(0, n - 1, 0);
        }

        private int DFS(int l, int r, int k) {
            if (l > r) return 0;
            if (dp[l][r][k] > 0) return dp[l][r][k];
            while (r > l && boxes[r] == boxes[r - 1]) {
                r--;
                k++;
            }


            dp[l][r][k] = DFS(l, r - 1, 0) + (k + 1) * (k + 1);

            for (int i = l; i < r; i++) {
                if (boxes[i] == boxes[r]) {
                    dp[l][r][k] = Math.max(dp[l][r][k], DFS(i + 1, r - 1, 0) + DFS(l, i, k + 1));
                }
            }
            return dp[l][r][k];
        }
    }
}
