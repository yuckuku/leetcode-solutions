package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author: L'Nan
 * Date: 2019/4/27 22:01
 * Description:
 */
public class RemoveDuplicatesfromSortedArrayII80 {
    class Solution {
        public int removeDuplicates(int[] nums) {
            if (nums.length <= 2) {
                return nums.length;
            }

            int re = 2;
            for (int i = 2; i < nums.length; i++) {
                System.out.println(Arrays.toString(nums));
                if (nums[i] > nums[i - 1]) {
                    re = i + 1;
                    continue;
                }
                if (nums[i] < nums[i - 1]) {
                    int j = i;
                    while (j < nums.length) {
                        if (nums[j] > nums[i - 1]) {
                            swap(nums, i, j);
                            re = i + 1;
                            break;
                        } else if (nums[j] == nums[i - 1] && nums[j] > nums[i - 2]) {
                            swap(nums, i, j);
                            re = i + 1;
                            break;
                        } else {
                            re = i;
                        }
                        j++;
                    }
                    if (j >= nums.length - 1) {
                        break;
                    }
                }

                if (nums[i] == nums[i - 1] && nums[i] > nums[i - 2]) {
                    re = i + 1;
                    continue;
                }
                if (nums[i] == nums[i - 1] && nums[i] == nums[i - 2]) {
                    re = i;
                    int j = i;
                    while (j < nums.length) {
                        if (nums[j] > nums[i]) {
                            swap(nums, i, j);
                            re = i + 1;
                            break;
                        }
                        j++;
                    }
                    if (j >= nums.length - 1) {
                        break;
                    }
                }
            }
            return re;
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    /**
     * 双游标遍历
     */
    class SolutionOnLeetCode1 {
        public int removeDuplicates(int[] nums) {
            int i = 0;
            int count = 1;

            for (int j = 1; j < nums.length; j++) {
                if (nums[i] != nums[j]) {
                    nums[++i] = nums[j];
                    count = 1;
                } else if (count >= 2) {
                    continue;
                } else {
                    nums[++i] = nums[j];
                    count++;
                }
            }
            return i + 1;
        }
    }

    @Test
    public void test() {
        Solution solution = new Solution();
        int re = 0;
        int[] a = new int[]{1, 1, 1, 2, 2, 3};
        re = solution.removeDuplicates(a);
        System.out.println(Arrays.toString(a));
        System.out.println(re);

        a = new int[]{1, 1, 1};
        re = solution.removeDuplicates(a);
        System.out.println(re);

        a = new int[]{1, 1, 1, 2, 2, 2, 3, 3};
        re = solution.removeDuplicates(a);
        System.out.println(Arrays.toString(a));
        System.out.println(re);

        a = new int[]{0, 1, 2, 2, 2, 2, 2, 3, 4, 4, 4};
        re = solution.removeDuplicates(a);
        System.out.println(Arrays.toString(a));
        System.out.println(re);

        a = new int[]{1, 1, 1, 1};
        re = solution.removeDuplicates(a);
        System.out.println(Arrays.toString(a));
        System.out.println(re);
    }

}
