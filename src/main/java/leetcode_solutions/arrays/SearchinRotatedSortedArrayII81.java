package leetcode_solutions.arrays;

import org.junit.Test;

import java.lang.annotation.Target;

/**
 * @author: L'Nan
 * Date: 2019/5/23 14:36
 * Description:
 */
public class SearchinRotatedSortedArrayII81 {
    class Solution {
        public boolean search(int[] nums, int target) {
            int len = nums.length;
            int start = 0;
            int end = nums.length - 1;
            while (start <= end) {
                int mid = (start + end) / 2;
                if (nums[mid] == target)
                    return true;
                if (start == end) {
                    return false;
                }
                if (nums[start] == nums[mid]) {
                    while (nums[start] == nums[mid] && start < len - 1) {
                        start++;
                    }
                    continue;
                }
                if (nums[end] == nums[mid]) {
                    while (nums[end] == nums[mid] && end > 1) {
                        end--;
                    }
                    continue;
                }
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
            return false;
        }
    }

    class SolutionOnLeetcode1 {
        // 旋转数组中查找
        public boolean search(int[] nums, int target) {
            if (nums.length == 0) {
                return false;
            }
            // 不使用递归
            int left = 0;
            int right = nums.length - 1;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    return true;
                }
                if (nums[mid] < nums[right]) { // 右边是有序的
                    if (target > nums[mid] && target <= nums[right]) { // 在右边
                        left = mid + 1;
                    } else { // 在左边
                        right = mid - 1;
                    }
                } else if (nums[mid] > nums[right]) { // 左边是有序的
                    if (target >= nums[left] && nums[mid] > target) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                } else {
                    right--;
                }
            }

            return nums[left] == target;
        }
    }

    @Test
    public void test() {
        int[] nums = new int[]{1};
        int target = 0;
        Solution s = new Solution();
        boolean re = s.search(nums, target);
        System.out.println(re);
    }
}
