package leetcode_solutions.arrays;

/**
 * @author: L'Nan
 * Date: 2019/5/15 20:46
 * Description:
 */
public class FindFirstandLastPositionofElementinSortedArray34 {
    class Solution {
        public int[] searchRange(int[] nums, int target) {
            int[] re = new int[]{-1, -1};
            if (nums == null || nums.length == 0) {
                return re;
            }

            int left = 0, right = nums.length - 1;
            int mid = (left + right) >> 1;
            while (left <= right) {
                if (target == nums[left]) {
                    return range(nums, left);
                }
                if (target == nums[right]) {
                    return range(nums, right);
                }
                if (right - left <= 1) {
                    return re;
                }
                if (target == nums[mid]) {
                    return range(nums, mid);
                } else if (target > nums[mid]) {
                    left = mid;
                    mid = (left + right) >> 1;
                } else {
                    right = mid;
                    mid = (left + right) >> 1;
                }
            }
            return re;
        }

        private int[] range(int[] nums, int index) {
            int[] re = new int[]{-1, -1};
            int temp = index;
            while (index > 0 && nums[index] == nums[index - 1]) {
                index--;
            }
            while (temp < nums.length - 1 && nums[temp] == nums[temp + 1]) {
                temp++;
            }
            re[0] = index;
            re[1] = temp;
            return re;
        }
    }

    public class SolutionOnLeetcode1 {
        public int[] searchRange(int[] A, int target) {
            int start = firstGreaterEqual(A, target);
            if (start == A.length || A[start] != target) {
                return new int[]{-1, -1};
            }
            return new int[]{start, firstGreaterEqual(A, target + 1) - 1};
        }

        //find the first number that is greater than or equal to target.
        //could return A.length if target is greater than A[A.length-1].
        //actually this is the same as lower_bound in C++ STL.
        private int firstGreaterEqual(int[] A, int target) {
            int low = 0, high = A.length;
            while (low < high) {
                int mid = low + ((high - low) >> 1);
                //low <= mid < high
                if (A[mid] < target) {
                    low = mid + 1;
                } else {
                    //should not be mid-1 when A[mid]==target.
                    //could be mid even if A[mid]>target because mid<high.
                    high = mid;
                }
            }
            return low;
        }
    }
}
