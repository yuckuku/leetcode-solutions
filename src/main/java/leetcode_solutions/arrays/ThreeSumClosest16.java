package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Auther: L'Nan
 * @Date: 2018/11/13 19:08
 * @Description:
 */
public class ThreeSumClosest16 {
    /**
     * my solution: violent traverse all possible combination.
     */
    class MySolution {
        public int threeSumClosest(int[] nums, int target) {
            Arrays.sort(nums);
            int re = 0;
            int distance = Integer.MAX_VALUE;
            for (int i = 0; i < nums.length - 2; i++) {
                for (int j = i + 1; j < nums.length - 1; j++) {
                    for (int k = j + 1; k < nums.length; k++) {
                        int temp = Math.abs(nums[i] + nums[j] + nums[k] - target);
                        if (distance > temp) {
                            re = nums[i] + nums[j] + nums[k];
                            distance = temp;
                        }
                    }
                }
            }
            return re;
        }
    }

    /**
     * solution on leetcode: use left and right points' movement.
     */
    class Solution {
        public int threeSumClosest(int[] nums, int target) {

            int result = nums[0] + nums[1] + nums[2];
            Arrays.sort(nums);
            for (int i = 0; i < nums.length - 2; i++) {
                int start = i + 1, end = nums.length - 1;

                while (start < end) {
                    int tmpSum = nums[i] + nums[start] + nums[end];
                    if (Math.abs(tmpSum - target) < Math.abs(result - target))
                        result = tmpSum;
                    if (tmpSum < target)
                        start++;
                    else if (tmpSum > target)
                        end--;
                    else
                        return result;

                }
            }
            return result;
        }

    }

    @Test
    public void test() {
        //target=1,ans=2
        int target = 1;
        int[] nums = new int[]{-1, 2, 1, -4};

        //target=1,ans=0
//        int target=1;
//        int[] nums = new int[]{0, 2, 1, -3};

        int re = new MySolution().threeSumClosest(nums, target);
        System.out.println(re);
    }

}
