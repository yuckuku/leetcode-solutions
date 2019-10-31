package leetcode_solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MinimumTimeDifference539 {
    class Solution {
        public int findMinDifference(List<String> timePoints) {
            Collections.sort(timePoints);
            int re = Integer.MAX_VALUE;
            for (int i = 1; i < timePoints.size(); i++) {
                int minutes = getMinute(timePoints.get(i)) - getMinute(timePoints.get(i - 1));
                if (minutes < re) re = minutes;
            }
            int m = 24 * 60 + getMinute(timePoints.get(0)) - getMinute(timePoints.get(timePoints.size() - 1));
            if (m < re) re = m;
            return re;
        }

        private int getMinute(String s) {
            String[] ss = s.split(":");
            return Integer.valueOf(ss[0]) * 60 + Integer.valueOf(ss[1]);
        }
    }

    class Solution1 {
        public int findMinDifference(List<String> timePoints) {
            boolean[] mark = new boolean[24 * 60];
            for (String time : timePoints) {
                String[] t = time.split(":");
                int h = Integer.parseInt(t[0]);
                int m = Integer.parseInt(t[1]);
                if (mark[h * 60 + m]) return 0;
                mark[h * 60 + m] = true;
            }
            int res = Integer.MAX_VALUE, pre = Integer.MAX_VALUE, first = Integer.MAX_VALUE;
            for (int i = 0; i < 24 * 60; i++) {
                if (mark[i]) {
                    if (first == Integer.MAX_VALUE) {
                        first = i;
                    }
                    if (pre == Integer.MAX_VALUE) {
                        pre = i;
                    } else {
                        res = Math.min(res, i - pre);
                        pre = i;
                    }
                }
            }
            res = Math.min(res, first + 24 * 60 - pre);
            return res;
        }
    }


    @Test
    public void test() {
        List<String> timePoints = new ArrayList<>(Arrays.asList("23:59", "00:00"));
        int re = new Solution().findMinDifference(timePoints);
        System.out.println("re:" + re);
        Collections.sort(timePoints);
        System.out.println(timePoints);
    }
}
