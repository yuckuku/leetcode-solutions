package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: L'Nan
 * @Date: 2018/11/14 20:26
 * @Description:
 */
public class FourSum18 {
    /**
     * my solution: base on my solution of the fifteenth problem 3-sum, turns to be right answer.
     */
    class MySolution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> re = new ArrayList<>();
            Arrays.sort(nums);

            int preNum0 = Integer.MAX_VALUE;
            for (int i = 0; i < nums.length - 3; i++) {
                if (0 != i && nums[i] == preNum0) {
                    continue;
                }

                int needNum = target - nums[i];
                int preNum1 = Integer.MAX_VALUE;
                for (int j = i + 1; j < nums.length - 2; j++) {
                    if (nums[j] == preNum1) {
                        continue;
                    }

                    int start = j + 1;
                    int end = nums.length - 1;
                    int preStartNum = Integer.MAX_VALUE;
                    int preEndNum = 0;
                    while (start < end) {
                        if (nums[j] + nums[start] + nums[end] == needNum) {

                            if (nums[start] == preStartNum && nums[end] == preEndNum) {
                                start++;
                                end--;
                                continue;
                            }

                            List<Integer> list = new ArrayList<>();
                            list.add(nums[i]);
                            list.add(nums[j]);
                            list.add(nums[start]);
                            list.add(nums[end]);
                            re.add(list);
                            preStartNum = nums[start];
                            preEndNum = nums[end];
                            start++;
                            end--;
                        } else if (nums[j] + nums[start] + nums[end] > needNum) {
                            end--;
                        } else {
                            start++;
                        }
                    }
                    preNum1 = nums[j];
                }
                preNum0 = nums[i];
            }

            return re;
        }
    }

    /**
     * solution on leetcode
     */
    class Solution0 {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> res = new ArrayList<>();
            if (nums.length < 4) return res;
            Arrays.sort(nums);
            for (int i = 0; i < nums.length - 3; i++) {
                if (i > 0 && nums[i] == nums[i - 1]) continue;

                if (nums[i] * 4 > target) break;// Too Big!!太大了，后续只能更大，可以直接结束循环；
                if (nums[i] + 3 * nums[nums.length - 1] < target) continue;//Too Small！太小了，当前值不需要再算，可以继续循环尝试后面的值。

                for (int j = i + 1; j < nums.length - 2; j++) {
                    if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                    if (nums[j] * 3 > target - nums[i]) break;//Too Big！ 注意此时不能结束i的循环，因为j是移动的 当j移动到后面的时候继续i循环也sum可能变小
                    if (nums[j] + 2 * nums[nums.length - 1] < target - nums[i]) continue;// Too Small

                    int begin = j + 1;
                    int end = nums.length - 1;
                    while (begin < end) {
                        int sum = nums[i] + nums[j] + nums[begin] + nums[end];
                        if (sum == target) {
                            res.add(Arrays.asList(nums[i], nums[j], nums[begin], nums[end]));
                            while (begin < end && nums[begin] == nums[begin + 1]) {
                                begin++;
                            }
                            while (begin < end && nums[end] == nums[end - 1]) {
                                end--;
                            }
                            begin++;
                            end--;
                        } else if (sum < target) {
                            begin++;
                        } else {
                            end--;
                        }
                    }
                }
            }
            return res;
        }
    }


    @Test
    public void test() {
        //ans=[[-1,  0, 0, 1],[-2, -1, 1, 2],[-2,  0, 0, 2]]
        int[] nums = new int[]{1, 0, -1, 0, -2, 2};
        int target = 0;

        List<List<Integer>> re = new MySolution().fourSum(nums, target);
        System.out.println(re);
    }
}
