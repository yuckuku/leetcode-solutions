package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: L'Nan
 * Date: 2019/6/20 15:10
 * Description:
 */
public class InsertInterval57 {
    class Solution {
        public int[][] insert(int[][] intervals, int[] newInterval) {
            int len = intervals.length;
            if (len <= 0) return new int[][]{{newInterval[0], newInterval[1]}};
            int left = 0, right = len - 1;
            int mid = 0;
            while (left <= right) {
                mid = (left + right) >> 1;
                int[] temp = intervals[mid];
//                if (!left(newInterval, temp) && !right(newInterval, temp)) break;
                if (left(newInterval, temp)) {
                    right = mid - 1;
                } else if (right(newInterval, temp)) {
                    left = mid + 1;
                } else {
                    break;
                }
            }

            if (left(newInterval, intervals[mid])) {
                int[][] re = new int[len + 1][2];
                System.arraycopy(intervals, 0, re, 0, mid);
                re[mid] = newInterval;
                System.arraycopy(intervals, mid, re, mid + 1, len - mid);
                return re;
            }
            if (right(newInterval, intervals[mid])) {
                int[][] re = new int[len + 1][2];
                System.arraycopy(intervals, 0, re, 0, mid + 1);
                re[mid + 1] = newInterval;
                System.arraycopy(intervals, mid + 1, re, mid + 2, len - mid - 1);
                return re;
            }

            left = right = mid;
            while (left >= 0 && !right(newInterval, intervals[left])) {
                left--;
            }

            while (right <= len - 1 && !left(newInterval, intervals[right])) {
                right++;
            }

            left++;
            right--;
            int leftV = Math.min(intervals[left][0], newInterval[0]);
            int rightV = Math.max(intervals[right][1], newInterval[1]);
            int[] merge = new int[]{leftV, rightV};

            int[][] re = new int[len - right + left][2];
            System.arraycopy(intervals, 0, re, 0, left);
            re[left] = merge;
            System.arraycopy(intervals, right + 1, re, left + 1, len - right - 1);
            return re;
        }

        private boolean left(int[] a, int[] b) {
            if (a[1] < b[0]) return true;
            return false;
        }

        private boolean right(int[] a, int[] b) {
            if (a[0] > b[1]) return true;
            return false;
        }
    }

    /**
     * Definition for an interval.
     * public class Interval {
     * int start;
     * int end;
     * Interval() { start = 0; end = 0; }
     * Interval(int s, int e) { start = s; end = e; }
     * }
     */

    class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    class SolutionOnLeetcode1 {
        public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
            List<Interval> result = new ArrayList<Interval>();
            boolean isAddedNew = false;

            for (Interval curr : intervals) {
                if (newInterval.start > curr.end) {
                    //new > curr
                    result.add(curr);
                } else if (newInterval.end < curr.start) {
                    //curr > new
                    if (!isAddedNew) {
                        result.add(newInterval);
                        isAddedNew = true;
                    }
                    result.add(curr);
                } else {
                    int newStart = curr.start < newInterval.start ?
                            curr.start : newInterval.start;
                    int newEnd = curr.end > newInterval.end ?
                            curr.end : newInterval.end;
                    newInterval.start = newStart;
                    newInterval.end = newEnd;
                }

            }

            if (!isAddedNew) {
                result.add(newInterval);
            }
            return result;
        }
    }

    @Test
    public void test() {
        int[][] intervals = new int[][]{{2, 4}, {5, 7}, {8, 10}, {11, 13}};
        int[] newInterval = new int[]{3, 8};
        Solution s = new Solution();
        int[][] re = s.insert(intervals, newInterval);
        System.out.println(Arrays.toString(re));
    }
}
