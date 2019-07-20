package leetcode_solutions.arrays;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class FindKthSmallestPairDistance719 {
    //桶
    class Solution {
        public int smallestDistancePair(int[] nums, int k) {
            int n = nums.length, N = 1000000;
            int[] cnt = new int[N];
            for (int i = 0; i < n; ++i) {
                for (int j = i + 1; j < n; ++j) {
                    ++cnt[Math.abs(nums[i] - nums[j])];
                }
            }
            for (int i = 0; i < N; ++i) {
                if (cnt[i] >= k) return i;
                k -= cnt[i];
            }
            return -1;
        }
    }

    //二分
    class SolutionDivide {
        public int smallestDistancePair(int[] nums, int k) {
            Arrays.sort(nums);
            int len = nums.length, l = 0, r = nums[len - 1] - nums[0];
            while (l < r) {
                int mid = (l + r) / 2;
                if (getcount(nums, mid) < k) l = mid + 1;
                else r = mid;
            }
            return l;

        }

        //sliding window
        private int getcount(int[] nums, int mid) {
            int ans = 0;
            int l = 0;
            for (int i = 1; i < nums.length; i++) {
                while (nums[i] - nums[l] > mid) l++;
                ans += i - l;
            }
            return ans;
        }
    }
}
