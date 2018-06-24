package leetcode_solutions;

import java.util.HashMap;
import java.util.Map;

public class LongestHarmoniousSubsequence594 {

    public int findLHS(int[] nums) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            // getOrDefault（JDK 8）
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int max = 0;
        for (int num : map.keySet()) {
            // 如果map包括num+1，那么比较当前值
            if (map.containsKey(num + 1)) {
                int tmp = map.get(num) + map.get(num + 1);
                max = Math.max(max, tmp);
            }
        }
        return max;
    }
}
