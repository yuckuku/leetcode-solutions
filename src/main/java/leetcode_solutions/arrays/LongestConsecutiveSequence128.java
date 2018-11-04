package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.Arrays;

public class LongestConsecutiveSequence128 {
    //my solution
    public int longestConsecutive(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        int temp = 0;
        int i = 0, j = 1;
        for (; i < nums.length - 1; i++, j++) {
            if (nums[j] - nums[i] == 1) {
                temp++;
            } else if (nums[j] - nums[i] == 0) {
                continue;
            } else {
                ans = Math.max(ans, temp);
                temp = 0;
            }
        }
        ans = Math.max(ans, temp);
        return nums.length < 1 ? 0 : ans + 1;
    }

    //use one index to loop
    public int longestConsecutive0(int[] nums) {
        int length = nums.length;
        if(length==1){
            return 1;
        }
        Arrays.sort(nums);
        int result = 0 ;
        int temp = 1 ;
        for(int i = 1 ; i < length;i++){
            while(i < length&& (nums[i]==nums[i-1]||nums[i]==nums[i-1]+1) ){
                if( nums[i]==nums[i-1]+1){
                    temp++;
                }
                i++;
            }
            result =Math.max(result,temp);
            temp=1;
        }
        return result;
    }

    @Test
    public void test() {
        int[] nums = new int[]{1, 2, 0, 1};
        longestConsecutive(nums);
    }
}
