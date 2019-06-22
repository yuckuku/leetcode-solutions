package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: L'Nan
 * Date: 2019/5/29 09:45
 * Description:
 */
public class CircularArrayLoop457 {
    class Solution {
        public boolean circularArrayLoop(int[] nums) {
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                if (nums[i] == 0) {
                    continue;
                }
                // slow fast pointers
                int j = i, k = getNext(j, nums);
                while (nums[i] * nums[k] > 0 && nums[i] * nums[getNext(k, nums)] > 0) {
                    if (j == k) {
                        //check for one element between loop
                        if (j == getNext(j, nums)) {
                            break;
                        }
                        return true;
                    }
                    j = getNext(j, nums);
                    k = getNext(getNext(k, nums), nums);
                }
                // clean all the loop along the way
                j = i;
                int val = nums[j];
                while (nums[j] * val > 0) {
                    int next = getNext(j, nums);
                    nums[j] = 0;
                    j = next;
                }
            }
            return false;
        }

        //判断有无向左过0的情况，计算得到不同的index
        private int getNext(int j, int[] nums) {
            int n = nums.length;
//            return j + nums[j] >= 0 ? (j + nums[j]) % n : n + ((j + nums[j]) % n);
            return (((nums[j] + j) % n) + n) % n;
        }
    }

    class SolutionOnLeetcode1 {
        public boolean circularArrayLoop(int[] nums) {
            if (nums.length < 2) {
                return false;
            }
            int len = nums.length;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 0) {
                    return false;
                }
                int slow = i, fast = calIndex(nums, i);
                while (nums[i] * nums[fast] > 0 && nums[i] * nums[calIndex(nums, fast)] > 0) {
                    if (slow == fast) {
                        if (slow == calIndex(nums, slow)) {
                            break;
                        }
                        return true;
                    }
                    slow = calIndex(nums, slow);
                    fast = calIndex(nums, calIndex(nums, fast));
                }
                slow = i;
                int pre = nums[i];
                while (nums[slow] * pre > 0) {
                    int next = calIndex(nums, slow);
                    nums[slow] = 0;
                    slow = next;
                }
            }
            return false;

        }

        public int calIndex(int[] nums, int index) {
            int n = nums.length;
            int next = nums[index] + index;
            return next >= 0 ? next % n : next % n + n;
        }
    }

    @Test
    public void test() {
        int[] nums = new int[]{-1};
        Solution s = new Solution();
        boolean re = s.circularArrayLoop(nums);
//        int nextIndex = s.getNext(1, nums);
//        System.out.println(s.getNext(nextIndex, nums));
        System.out.println(re);

    }


}
