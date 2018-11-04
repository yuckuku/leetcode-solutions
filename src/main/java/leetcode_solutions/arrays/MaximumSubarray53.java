package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.Arrays;

public class MaximumSubarray53 {
    //my solution:divide;wrong answer, can not use loop
    public int maxSubArray(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int leftMax = maxSubArray(Arrays.copyOfRange(nums, 0, i));
            int rightMax = maxSubArray(Arrays.copyOfRange(nums, i, nums.length));


        }
        return 0;
    }

    //divide
    public int maxSubArray0(int[] nums) {
        // Solution 3: Divide and Conquer. O(nlogn)
        if (nums == null || nums.length == 0)
            return 0;


        return Max_Subarray_Sum(nums, 0, nums.length - 1);
    }

    public int Max_Subarray_Sum(int[] nums, int left, int right) {
        if (left == right)    // base case: meaning there is only one element.
            return nums[left];

        int middle = (left + right) / 2;    // calculate the middle one.

        // recursively call Max_Subarray_Sum to go down to base case.
        int left_mss = Max_Subarray_Sum(nums, left, middle);
        int right_mss = Max_Subarray_Sum(nums, middle + 1, right);

        // set up leftSum, rightSum and sum.
        int leftSum = Integer.MIN_VALUE;
        int rightSum = Integer.MIN_VALUE;
        int sum = 0;

        // calculate the maximum subarray sum for right half part.
        for (int i = middle + 1; i <= right; i++) {
            sum += nums[i];
            rightSum = Integer.max(rightSum, sum);
        }

        sum = 0;    // reset the sum to 0.

        // calculate the maximum subarray sum for left half part.
        for (int i = middle; i >= left; i--) {
            sum += nums[i];
            leftSum = Integer.max(leftSum, sum);
        }

        // choose the max between left and right from down level.
        int res = Integer.max(left_mss, right_mss);
        // choose the max between res and middle range.

        return Integer.max(res, leftSum + rightSum);

    }

    //Kadane's Algorithm

    /**
     * 理解此算法的关键在于:
     * 最大子片段中不可能包含求和值为负的前缀。 例如 【-2， 1，4】 必然不能是最大子数列， 因为去掉值为负的前缀后【-2，1】， 可以得到一个更大的子数列 【4】、
     * 所以在遍历过程中，每当累加结果成为一个非正值时， 就应当将下一个元素作为潜在最大子数列的起始元素， 重新开始累加。
     * 由于在累加过程中， 出现过的最大值都会被记录， 且每一个可能成为 最大子数列起始元素 的位置， 都会导致新一轮的累加， 这样就保证了答案搜索过程的完备性和正确性。
     *
     * @param nums
     * @return
     */
    public int maxSubArray1(int[] nums) {
        int max_ending_here = 0;
        int max_so_far = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            if (max_ending_here < 0)
                max_ending_here = 0;
            max_ending_here += nums[i];
            max_so_far = Math.max(max_so_far, max_ending_here);
        }
        return max_so_far;
    }

    // Kadane's Algorithm deformation
    public int maxSubArray2(int[] nums) {
        //    Solution 1:  O(n)
        // check param validation.
        if (nums == null || nums.length == 0)
            return 0;

        int sum = 0;
        int max = Integer.MIN_VALUE;

        // iterate nums array.
        for (int i = 0; i < nums.length; i++) {
            // choose a larger one between current number or (previous sum + current number).
            sum = Math.max(nums[i], sum + nums[i]);
            max = Math.max(max, sum);    // choose the larger max.
        }

        return max;
    }

    @Test
    public void test(){
        int[] nums={-1,-4,-9,-3,-3};
        int re=maxSubArray1(nums);
        System.out.println(re);
    }
}
