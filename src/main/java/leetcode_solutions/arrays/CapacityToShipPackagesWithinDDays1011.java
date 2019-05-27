package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.AbstractList;

/**
 * @author: L'Nan
 * Date: 2019/4/29 14:42
 * Description:
 */
public class CapacityToShipPackagesWithinDDays1011 {
    class Solution {
        public int shipWithinDays(int[] weights, int D) {
            int len = weights.length;
            int[] a = new int[len + 1];
            int max = -1;
            for (int i = 1; i < len + 1; i++) {
                a[i] = a[i - 1] + weights[i - 1];
                max = Math.max(max, weights[i - 1]);
            }

            int re = bSearch(a, max, a[len], D);
            if (days(a, re) > D) {
                while (days(a, re) > D) {
                    ++re;
                }
                return re;
            }
            while (re - 1 >= max && days(a, re - 1) <= D) {
                re--;
            }
            return re;
        }

        private int bSearch(int[] a, int l, int r, int d) {
            int mid = (l + r) / 2;

            int k = days(a, mid);
            if (k < d) {  // 如果所需天数小于d，说明船容量还可以更小
                if (l < mid - 1)
                    return bSearch(a, l, mid - 1, d);
                else
                    return l;
            } else if (k > d) {  //如果天数大于d，说明船容量不够大
                if (mid + 1 < r)
                    return bSearch(a, mid + 1, r, d);
                else
                    return r;
            }
            return mid;
        }


        private int days(int[] a, int c) {
            int days = 1;
            int pre = 0;
            for (int i = 1; i < a.length; i++) {
                if (a[i] - a[pre] < c) {
                    continue;
                } else if (a[i] - a[pre] == c) {
                    if (i == a.length - 1) {
                        return days;
                    }
                    days++;
                    pre = i;
                } else {
                    i--;
                    pre = i;
                    days++;
                }
            }
            return days;
        }
    }

    class SolutionOnLeetCode1 {
        public int shipWithinDays(int[] weights, int D) {
            int minAns = 1;
            int maxAns = 501 * weights.length;
            int middleAns;
            int ans = 0;

            while (minAns < maxAns) {
//            System.out.println("min" + minAns + ", max" + maxAns);
                middleAns = (maxAns + minAns) >> 1;

                if (count(weights, middleAns) <= D) {
                    maxAns = middleAns;
                    ans = middleAns;
                } else {
                    minAns = middleAns + 1;
                }

            }

            return ans;
        }


        private int count(int[] weights, int capacity) {
            int MAX_DAY = (int) 1e8;
            int cnt = 0;
            int curSum = 0;
            for (int i = 0; i < weights.length; i++) {
                curSum += weights[i];
                if (weights[i] > capacity) {
                    return MAX_DAY;
                }
                if (cnt == 0 || curSum > capacity) {
                    cnt += 1;
                    curSum = weights[i];
                }

//            System.out.println("i:" + i + ", v:" + weights[i] + ", curSum:" + curSum + ", cnt:" + cnt);
            }
            return cnt;
        }
    }

    @Test
    public void test() {
        int[] weights = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Solution solution = new Solution();
        int re = solution.shipWithinDays(weights, 1);
        System.out.println(re);
    }

}
