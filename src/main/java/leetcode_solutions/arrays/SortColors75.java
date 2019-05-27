package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author: L'Nan
 * Date: 2019/4/25 22:45
 * Description:
 */
public class SortColors75 {
    class Solution {
        public void sortColors(int[] nums) {
            int len = nums.length - 1;
            int beginIndex = (len - 1) >> 1;
            for (int i = beginIndex; i >= 0; i--) {
                maxHeapify(i, len, nums);
            }

            for (int i = len; i > 0; i--) {
                swap(0, i, nums);
                maxHeapify(0, i - 1, nums);
            }
        }

        private void maxHeapify(int index, int len, int[] arr) {
            int li = (index << 1) + 1; // 左子节点索引
            int ri = li + 1;           // 右子节点索引
            int cMax = li;             // 子节点值最大索引，默认左子节点。
            if (li > len) {
                return;       // 左子节点索引超出计算范围，直接返回。
            }
            if (ri <= len && arr[ri] > arr[li]) // 先判断左右子节点，哪个较大。
            {
                cMax = ri;
            }
            if (arr[cMax] > arr[index]) {
                swap(cMax, index, arr);      // 如果父节点被子节点调换，
                System.out.println(Arrays.toString(arr));
                maxHeapify(cMax, len, arr);  // 则需要继续判断换下后的父节点是否符合堆的特性。
            }
        }

        private void swap(int i, int j, int[] arr) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }


    class SolutionOnLeetCode1 {
        public void sortColors(int[] nums) {
            int a = 0, b = 0, c = 0;
            for (int i = 0; i < nums.length; i++) {
                switch (nums[i]) {
                    case 0:
                        a++;
                        break;
                    case 1:
                        b++;
                        break;
                    case 2:
                        c++;
                        break;
                    default:
                        break;
                }
            }
            for (int i = 0; i < a; i++) {
                nums[i] = 0;
            }
            for (int i = 0; i < b; i++) {
                nums[a + i] = 1;
            }
            for (int i = 0; i < c; i++) {
                nums[a + b + i] = 2;
            }
        }
    }

    @Test
    public void test() {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int len = array.length - 1;
        int beginIndex = (len - 1) >> 1;
        for (int i = beginIndex; i >= 0; i--) {
            new Solution().maxHeapify(i, len, array);
        }
        System.out.println(Arrays.toString(array));
    }
}
