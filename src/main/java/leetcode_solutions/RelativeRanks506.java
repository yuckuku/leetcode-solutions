package leetcode_solutions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/24.
 */
public class RelativeRanks506 {
    public String[] findRelativeRanks(int[] nums) {
        int[] temp = Arrays.copyOfRange(nums, 0, nums.length);
        Arrays.sort(temp);
        Map<Integer, String> map = new HashMap<>();
        String[] ranks = new String[temp.length];
        if (ranks.length < 1)
            return null;
        if (ranks.length == 1) {
            ranks[0] = "Gold Medal";
            return ranks;
        }
        if (ranks.length == 2) {
            if(nums[0]>nums[1]) {
                ranks[0] = "Gold Medal";
                ranks[1] = "Silver Medal";
            }else{
                ranks[1] = "Gold Medal";
                ranks[0] = "Silver Medal";
            }
            return ranks;
        }
        if (ranks.length >= 3) {
            map.put(temp[temp.length - 1], "Gold Medal");
            map.put(temp[temp.length - 2], "Silver Medal");
            map.put(temp[temp.length - 3], "Bronze Medal");
            for (int i = temp.length - 4,j=4; i >= 0; i--,j++)
                map.put(temp[i], j + "");
            for (int i = 0; i < nums.length; i++)
                ranks[i] = map.get(nums[i]);
            return ranks;
        }
        return null;
    }

    //priority queue
}
