package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Auther: L'Nan
 * @Date: 2018/11/13 20:22
 * @Description: Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 */
public class ThreeSum15 {
    /**
     * my solution: use left and right points' movement to find all possible combination.
     *
     * @see ThreeSumClosest16.Solution
     */
    class MySolution {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> re = new ArrayList<>();
            Arrays.sort(nums);
            int preNum = 0;
            for (int i = 0; i < nums.length - 2; i++) {
                if (0 != i && nums[i] == preNum) {
                    continue;
                }

                int start = i + 1;
                int end = nums.length - 1;
                int preStartNum = Integer.MAX_VALUE;
                int preEndNum = 0;
                while (start < end) {
                    if (nums[i] + nums[start] + nums[end] == 0) {

                        if (nums[start] == preStartNum && nums[end] == preEndNum) {
                            start++;
                            end--;
                            continue;
                        }

                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[start]);
                        list.add(nums[end]);
                        re.add(list);
                        preStartNum = nums[start];
                        preEndNum = nums[end];
                        start++;
                        end--;
                    } else if (nums[i] + nums[start] + nums[end] > 0) {
                        end--;
                    } else {
                        start++;
                    }
                }
                preNum = nums[i];
            }
            return re;
        }
    }

    /**
     * solution on leetcode:
     */
    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            if (nums.length < 3)
                return Collections.emptyList();
            List<List<Integer>> res = new ArrayList<>();
            int minValue = Integer.MAX_VALUE;
            int maxValue = Integer.MIN_VALUE;
            int negSize = 0;
            int posSize = 0;
            int zeroSize = 0;
            for (int v : nums) {
                if (v < minValue)
                    minValue = v;
                if (v > maxValue)
                    maxValue = v;
                if (v > 0)
                    posSize++;
                else if (v < 0)
                    negSize++;
                else
                    zeroSize++;
            }
            if (zeroSize >= 3)
                res.add(Arrays.asList(0, 0, 0));
            if (negSize == 0 || posSize == 0)
                return res;
            if (minValue * 2 + maxValue > 0)
                maxValue = -minValue * 2;
            else if (maxValue * 2 + minValue < 0)
                minValue = -maxValue * 2;

            int[] map = new int[maxValue - minValue + 1];
            int[] negs = new int[negSize];
            int[] poses = new int[posSize];
            negSize = 0;
            posSize = 0;
            for (int v : nums) {
                if (v >= minValue && v <= maxValue) {
                    if (map[v - minValue]++ == 0) {
                        if (v > 0)
                            poses[posSize++] = v;
                        else if (v < 0)
                            negs[negSize++] = v;
                    }
                }
            }
            Arrays.sort(poses, 0, posSize);
            Arrays.sort(negs, 0, negSize);
            int basej = 0;
            for (int i = negSize - 1; i >= 0; i--) {
                int nv = negs[i];
                int minp = (-nv) >>> 1;
                while (basej < posSize && poses[basej] < minp)
                    basej++;
                for (int j = basej; j < posSize; j++) {
                    int pv = poses[j];
                    int cv = 0 - nv - pv;
                    if (cv >= nv && cv <= pv) {
                        if (cv == nv) {
                            if (map[nv - minValue] > 1)
                                res.add(Arrays.asList(nv, nv, pv));
                        } else if (cv == pv) {
                            if (map[pv - minValue] > 1)
                                res.add(Arrays.asList(nv, pv, pv));
                        } else {
                            if (map[cv - minValue] > 0)
                                res.add(Arrays.asList(nv, cv, pv));
                        }
                    } else if (cv < nv)
                        break;
                }
            }
            return res;
        }
    }


    @Test
    public void test() {
        //ans=[[-1, 0, 1],[-1, -1, 2]]
//        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};

        //ans=[[-2,0,2]]
        int[] nums = new int[]{-2, 0, 0, 2, 2};

        List<List<Integer>> re = new MySolution().threeSum(nums);
        System.out.println(re);
    }
}
