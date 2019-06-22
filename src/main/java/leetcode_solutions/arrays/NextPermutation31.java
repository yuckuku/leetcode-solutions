package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.Arrays;
import java.util.IdentityHashMap;

/**
 * @author: L'Nan
 * Date: 2019/5/28 10:33
 * Description:
 */
public class NextPermutation31 {
    class Solution {
        public void nextPermutation(int[] nums) {
            int len = nums.length;
            //len is 1,return
            if (len <= 1) return;
            //locate i
            int i = len - 2;
            for (; i >= 0; i--) {
                if (nums[i] < nums[i + 1]) break;
            }
            //if largest, sort array
            if (i == -1) {
                Arrays.sort(nums);
                return;
            }
            //locate last index to move
            int last = len - 1;
            for (; last > i; last--) {
                if (nums[last] > nums[i]) break;
            }
            //assign value to index i
            int temp = nums[i];
            nums[i] = nums[last];
            nums[last] = temp;
            i++;
            //swap elements after i
            last = len - 1;
            while (i < last) {
                temp = nums[i];
                nums[i] = nums[last];
                nums[last] = temp;
                i++;
                last--;
            }
        }
    }

    class SolutionOnLeetcode1 {
        public void nextPermutation(int[] nums) {
            if (null == nums || nums.length <= 1) {
                return;
            }
            boolean isBiggest = true;
            for (int i = nums.length - 2; i >= 0; i--) {
                if (nums[i] < nums[i + 1]) {
                    int s = i + 1;
                    for (int j = i + 1; j < nums.length; j++) {
                        if (nums[j] > nums[i]) {
                            s = j;
                        } else {
                            break;
                        }
                    }
                    int tmp = nums[i];
                    nums[i] = nums[s];
                    nums[s] = tmp;

                    for (int j = i + 1; j < (nums.length - (i + 1)) / 2 + (i + 1); j++) {
                        int tmp2 = nums[j];
                        nums[j] = nums[nums.length - 1 - (j - (i + 1))];
                        nums[nums.length - 1 - (j - (i + 1))] = tmp2;
                    }
                    isBiggest = false;
                    break;
                }
            }
            if (isBiggest) {
                for (int i = 0; i < nums.length / 2; i++) {
                    int tmp = nums[i];
                    nums[i] = nums[nums.length - 1 - i];
                    nums[nums.length - 1 - i] = tmp;
                }
            }
        }
    }

    @Test
    public void test() {
        int[] nums = new int[]{2, 3, 1};
        Solution s = new Solution();
        s.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}
