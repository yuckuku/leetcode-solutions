package leetcode_solutions.arrays;

import org.junit.Test;

/**
 * @author: L'Nan
 * Date: 2019/6/17 15:59
 * Description:
 */
public class FirstMissingPositive41 {
    class Solution {
        public int firstMissingPositive(int[] nums) {
            return -1;
        }
    }

    class SolutionOnLeetcode1 {
        public int firstMissingPositive(int[] A) {
            int i = 0;
            while (i < A.length) {
                if (A[i] <= 0 || A[i] > A.length || A[A[i] - 1] == A[i]) {
                    i++;
                } else {
                    swap(A, i, A[i] - 1);
                }
            }
            i = 0;
            while (i < A.length && A[i] == i + 1) {
                i++;
            }
            return i + 1;
        }

        private void swap(int[] A, int i, int j) {
            int temp = A[i];
            A[i] = A[j];
            A[j] = temp;
        }
    }

    public class SolutionOnLeetcode2 {
        public int firstMissingPositive(int[] nums) {
            if (nums.length == 0)
                return 1;
            //第i位存放i+1的数值
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > 0) {//nums[i]为正数，放在i+1位置
                    //假设交换的数据还是大于0且<i+1，则放在合适的位置,且数据不相等，避免死循环
                    //这个while是关键，其它都是没有难度的
                    while (nums[i] > 0 && nums[i] < i + 1 && nums[i] != nums[nums[i] - 1]) {
                        int temp = nums[nums[i] - 1];//交换数据
                        nums[nums[i] - 1] = nums[i];
                        nums[i] = temp;
                    }
                }
            }
            //循环寻找不符合要求的数据，返回
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != i + 1) {
                    return i + 1;
                }
            }
            //假设都符合要求，则返回长度+1的值
            return nums.length + 1;
        }
    }

    @Test
    public void test() {
        int[] nums = new int[]{3, 4, -1, 1};
        SolutionOnLeetcode1 s = new SolutionOnLeetcode1();
        int re = s.firstMissingPositive(nums);
        System.out.println(re);
    }
}
