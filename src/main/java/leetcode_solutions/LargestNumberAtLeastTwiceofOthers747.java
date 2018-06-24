package leetcode_solutions;

public class LargestNumberAtLeastTwiceofOthers747 {
    public int dominantIndex(int[] nums) {
        int index = findLargest(nums);
        System.out.println("index:" + index);
        if (twiceLarge(nums, index)) {
            return index;
        } else {
            return -1;
        }
    }

    private boolean twiceLarge(int[] nums, int index) {
        for (int num :
                nums) {
            if (num != nums[index] && num * 2 <= nums[index]) {
                continue;
            } else if (num == nums[index]) {
                continue;
            } else {
                System.out.println(num);
                return false;
            }
        }
        return true;
    }

    private int findLargest(int[] nums) {
        int temp = nums[0];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > temp) {
                temp = nums[i];
                index = i;
            }
        }
        return index;
    }
}
