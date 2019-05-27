package leetcode_solutions.arrays;

/**
 * @author: L'Nan
 * Date: 2019/5/15 13:44
 * Description:
 */
public class SearchinRotatedSortedArray33 {
    class Solution {
        public int search(int[] nums, int target) {
            int mid;
            if (nums == null || nums.length == 0)
                return -1;
            else if (nums.length == 1) {
                return target == nums[0] ? 0 : -1;
            } else if (nums.length == 2) {
                return target == nums[0] ? 0 : (target == nums[1] ? 1 : -1);
            } else if (nums[nums.length - 1] > nums[0])
                return find(nums, 0, nums.length - 1, target);
            else {
                int left = 0, right = nums.length - 1;
                mid = (right + left) >> 1;
                while (left < right) {
                    if (nums[mid] < nums[mid - 1])
                        break;
                    else if (nums[mid + 1] < nums[mid]) {
                        mid++;
                        break;
                    }
                    if (nums[mid] < nums[right]) {
                        right = mid;
                        mid = (right + left) >> 1;
                    } else {
                        left = mid;
                        mid = (right + left) >> 1;
                    }
                }
            }

            if (target == nums[nums.length - 1]) return nums.length - 1;
            else if (target < nums[nums.length - 1]) return find(nums, mid, nums.length - 1, target);
            else return find(nums, 0, mid - 1, target);
        }

        private int find(int[] nums, int left, int right, int target) {
            if (target < nums[left]) return -1;
//            if (target == nums[right]) return right;
            if (right - left <= 1)
                return target == nums[left] ? left : (target == nums[right] ? right : -1);

            int mid = (right + left) >> 1;
            System.out.println("left:" + left + " right: " + right + " mid:" + mid);
            if (target == nums[mid])
                return mid;
            else if (target < nums[mid]) {
                return find(nums, left, mid, target);
            } else {
                return find(nums, mid, right, target);
            }
        }
    }

    public class SolutionOnLeetcode1 { //revised bianry search
        public int search(int[] nums, int target) {
            int start = 0;
            int end = nums.length - 1;
            while (start <= end) {
                int mid = (start + end) / 2;
                if (nums[mid] == target)
                    return mid;

                if (nums[start] <= nums[mid]) {
                    if (target < nums[mid] && target >= nums[start])
                        end = mid - 1;
                    else
                        start = mid + 1;
                }

                if (nums[mid] <= nums[end]) {
                    if (target > nums[mid] && target <= nums[end])
                        start = mid + 1;
                    else
                        end = mid - 1;
                }
            }
            return -1;
        }
    }
}
