package leetcode_solutions.arrays;

/**
 * @author: L'Nan
 * Date: 2019/6/10 11:16
 * Description:
 */
public class GrumpyBookstoreOwner1052 {
    class Solution {
        public int maxSatisfied(int[] customers, int[] grumpy, int X) {
            int re = 0;
            int len = customers.length;
            for (int i = 0; i < len; i++) {
                if (grumpy[i] == 0) re += customers[i];
            }

            int left = 0, right = X - 1;
            int max = 0;
            while (right < len) {
                int temp = 0;
                for (int i = left; i <= right; i++) {
                    if (grumpy[i] == 1) temp += customers[i];
                }
                max = Math.max(max, temp);
                left++;
                right++;
            }
            return re + max;
        }
    }

    class SolutionOnLeetcode1 {
        public int maxSatisfied(int[] customers, int[] grumpy, int X) {
            int ans = 0;
            int len = customers.length;
            int[] rec = new int[len];
            for (int i = 0; i < len; i++)
                if (grumpy[i] == 1)
                    rec[i] = customers[i];
                else
                    ans += customers[i];//计算出所有不生气的情况

            int max = 0;
            for (int i = 0; i < len && i < X; i++)
                max += rec[i];
            int pre = max;
            for (int i = X; i < len; i++) {
                pre = pre + rec[i] - rec[i - X];//在计算的时候，那个窗口里面是只是计算了生气的情况，所以不存在多计算了一次的情况
                if (pre > max)
                    max = pre;
            }
            ans += max;
            return ans;
        }
    }
}
