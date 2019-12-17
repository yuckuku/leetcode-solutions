package leetcode_solutions;

public class DailyTemperatures739 {
    //执行用时:233ms,在所有java提交中击败了26.91%的用户内存消耗:41MB,在所有java提交中击败了97.16%的用户
    class Solution {
        public int[] dailyTemperatures(int[] T) {
            int len = T.length;
            int[] days = new int[len];
            for (int i = 0; i < len - 1; i++) {
                int j = i + 1;
                for (; j < len; j++) {
                    if (T[j] > T[i]) break;
                }
                days[i] = j == len ? 0 : j - i;
            }
            return days;
        }
    }

    class Solution4ms {
        public int[] dailyTemperatures(int[] T) {
            int l = T.length;
            int[] result = new int[l];
            for (int i = l - 1; i >= 0; i--) {
                for (int j = i + 1; j < l; ) {
                    if (T[j] > T[i]) {
                        result[i] = j - i;
                        break;
                    } else if (result[j] == 0) {
                        result[i] = 0;
                        break;
                    } else {
                        j += result[j];
                    }
                }
            }
            return result;
        }
    }
}
