package leetcode_solutions.arrays;

import org.junit.Test;

/**
 * @author: L'Nan
 * Date: 2019/6/12 14:43
 * Description:
 */
public class FindMinimuminRotatedSortedArrayII154 {
    class Solution {
        public int findMin(int[] nums) {
            int len = nums.length;
//            if (nums[len - 1] == nums[0]) return nums[0];
            int left = 0, right = len - 1;
            while (left < right) {
                while (left < len - 1 && nums[left] == nums[left + 1]) left++;
                while (right > 0 && nums[right] == nums[right - 1]) right--;
                if (left >= right) return nums[left];
                if (nums[left] == nums[right]) {
                    right--;
                }
                if (nums[left] < nums[right]) {
                    return nums[left];
                }
                int mid = (left + right) >> 1;
                if (mid == left) return Math.min(nums[mid], nums[right]);
                if (mid > 0 && nums[mid] < nums[mid - 1]) return nums[mid];
                if (nums[mid] < nums[left]) {
                    right = mid;
                    continue;
                }
                if (nums[mid] > nums[right]) {
                    left = mid;
                    continue;
                }
            }
            return nums[left];
        }
    }

    class SolutionOnLeetcode1 {

        int Min(int[] array) {
            int min = array[0];
            for (int i = 1; i < array.length; i++) {
                if (array[i] < min) {
                    min = array[i];
                }
            }
            return min;
        }

        public int findMin(int[] nums) {
            if (nums.length == 1) return nums[0];
            return Min(nums);
        }
    }

    @Test
    public void test() {
        int[] nums = new int[]{3, 3, 1};
        Solution s = new Solution();
        int re = s.findMin(nums);
        System.out.println(re);
    }
}
