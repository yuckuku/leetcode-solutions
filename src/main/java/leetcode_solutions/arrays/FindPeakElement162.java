package leetcode_solutions.arrays;

public class FindPeakElement162 {
    //my solution: find all situations
    public int findPeakElement(int[] nums) {

        if (nums.length == 1) {
            return 0;
        }
        if (nums.length == 2) {
            if (nums[0] > nums[1]) {
                return 0;
            } else {
                return 1;
            }
        }

        if (nums[0] > nums[1]) {
            return 0;
        }
        if (nums[nums.length - 1] > nums[nums.length - 2]) {
            return nums.length - 1;
        }

        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                return i;
            }
        }
        return Integer.MAX_VALUE;
    }

    public int findPeakElement0(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high)  //当两者相等时，
        {
            int mid = (low + high) / 2;
            if (nums[mid] < nums[mid + 1])
                low = mid + 1;  //注意为什么要+1，因为mid位置一定不是峰，而low=mid+1有可能是峰
            else
                high = mid;      //注意这里为什么不加+，因为high=mid有可能是峰
        }
        return low;
    }
}
