package leetcode_solutions.arrays;

import java.util.HashMap;

/**
 * @author: L'Nan
 * Date: 2019/5/12 02:35
 * Description:
 */
public class SubarraySumEqualsK560 {
    class Solution {
        public int subarraySum(int[] nums, int k) {
            int sum = 0;
            int re = 0;

            for (int i = 0; i < nums.length; i++) {
                for (int j = i; j < nums.length; j++) {
                    if (i == j) {
                        sum = nums[i];
                    } else {
                        sum += nums[j];
                    }
                    if (sum == k) re++;
                }
            }

            return re;
        }
    }

    class SolutionOnLeetCode1 {
        public int subarraySum(int[] nums, int k) {
            int count = 0, sum = 0;
            HashMap<Integer, Integer> hashMap = new HashMap<>();
            hashMap.put(0, 1);
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                if (hashMap.containsKey(sum - k))
                    count += hashMap.get(sum - k);
                hashMap.put(sum, hashMap.getOrDefault(sum, 0) + 1);
            }
            return count;
        }
    }
}
