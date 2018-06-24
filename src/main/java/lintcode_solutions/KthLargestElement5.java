package lintcode_solutions;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KthLargestElement5 {
    /*
     * @param k : description of k
     * @param nums : array of nums
     * @return: description of return
     */
    public int kthLargestElement(int k, int[] nums) {
        // write your code here
        if (k > nums.length) {
            return -1;
        }
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    //use PriorityQueue
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> p = new PriorityQueue<Integer>();
        for(int i = 0 ; i < nums.length; i++){
            p.add(nums[i]);
            if(p.size()>k) p.poll();
        }
        return p.poll();
    }
}
