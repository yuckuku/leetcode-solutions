package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: L'Nan
 * Date: 2019/5/20 11:18
 * Description:
 */
public class RLEIterator900 {
    class RLEIterator {
        private int[] a;
        private int[] nums;
        private int len;
        private int index;

        public RLEIterator(int[] A) {
            len = A.length >> 1;
            index = 0;
            a = new int[len];
            nums = new int[len];
            for (int i = 0; i < len; i++) {
                nums[i] = A[i << 1];
                a[i] = A[(i << 1) + 1];
            }
        }

        public int next(int n) {
            System.out.println(Arrays.toString(a));
            System.out.println(Arrays.toString(nums));

            int re = -1;
            for (; index < len; index++) {
                if (nums[index] == n) {
                    re = a[index];
                    nums[index] = 0;
                    break;
                } else if (nums[index] > n) {
                    re = a[index];
                    nums[index] = nums[index] - n;
                    break;
                } else {
                    n = n - nums[index];
                }
            }
            return re;
        }
    }

    class RLEIteratorOnLeetcode1 {
        /**
         * 目标数组
         */
        private int[] baseNums;

        /**
         * baseNums : [count1, target1,count2,target2,...countN, targetN]
         * left :  当前左侧count之和
         * current : 当前A之和
         * lastIndex : 当前count-N索引
         */
        private long left = 0;
        private long current = 0;
        private int lastIndex = 0;

        public RLEIteratorOnLeetcode1(int[] A) {
            baseNums = A;
            lastIndex = 0;
            left = baseNums[lastIndex];
        }

        public int next(int n) {

            current += n;

            while (left < current && lastIndex < baseNums.length - 2) {

                lastIndex += 2;
                left += baseNums[lastIndex];

            }


            // 最新的current依旧小于当前left值
            if (left >= current) {
                return baseNums[lastIndex + 1];
            }

            return -1;
        }
    }


    /**
     * Your RLEIterator object will be instantiated and called as such:
     * RLEIterator obj = new RLEIterator(A);
     * int param_1 = obj.next(n);
     */

    @Test
    public void test() {
        int[] A = new int[]{3, 8, 0, 9, 2, 5};
        RLEIterator rle = new RLEIterator(A);
        int re = rle.next(2);
        re = rle.next(1);
        re = rle.next(1);
        re = rle.next(2);
    }
}
