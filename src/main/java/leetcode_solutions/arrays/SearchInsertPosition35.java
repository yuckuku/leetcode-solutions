package leetcode_solutions.arrays;

import org.junit.Test;

public class SearchInsertPosition35 {
    //my solution
    public int searchInsert(int[] nums, int target) {

        int i = 0;
        for (; i < nums.length; i++) {
            if (nums[i] == target) {
                break;
            }
            if (nums[i] < target) {
                continue;
            }
            if (nums[i] > target) {

                break;
            }
        }
        return i > 0 ? i : 0;
    }

    //binary search.
    public int searchInsert0(int[] nums, int target) {
        if(target<nums[0]){
            return 0;
        }
        if(target>nums[nums.length-1]){
            return nums.length;
        }
        int p = binarySearch(nums,target);
        if(nums[p]>=target){
            return p;
        } else {
            return p+1;
        }
    }

    public int binarySearch(int[] nums, int target){
        int lo = 0;
        int hi = nums.length-1;
        int p=0;
        while(hi>=lo){
            p = (hi+lo)/2;
            if(nums[p]>target){
                hi = p-1;
            } else if(nums[p]<target){
                lo = p+1;
            } else {
                break;
            }
        }
        return p;
    }

    @Test
    public void test() {
        int[] nums = new int[]{1, 3, 5, 6};
        searchInsert(nums, 2);
    }
}
