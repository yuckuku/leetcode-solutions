package leetcode_solutions;

import org.junit.Test;

import java.util.Arrays;

public class CountingBits338 {
    class Solution {
        public int[] countBits(int num) {
            if (num == 0) return new int[]{0};
            int[] re = new int[num + 1];
            for (int i = 0; i <= num; i++) {
                int count = count(i);
                re[i] = count;
            }
            return re;
        }

        private int count(int num) {
            int count = 0;
            while (num != 0) {
                if (num % 2 == 1) count++;
                num = num >> 1;
            }
            return count;
        }

    }

    class Solution1 {
        public int[] countBits(int num) {
            // 当 i 的最低位是0，则 i 中1的个数和i >> 1中1的个数相同；当i的最低位是1，i 中1的个数是 i >> 1中1的个数再加1
            int[] res = new int[num + 1];
            for (int i = 0; i < num + 1; i++) {
                //res[i>>1]：如i=1010,去找res[101]=2
                //i&1:如1011&0001=1,最后一位为1,前面抵掉了，所以需要+1
                //i&1:如1010&0001=0,抵掉的是0,+0不影响结果
                res[i] = res[i >> 1] + (i & 1);
            }
            return res;
        }
    }

    @Test
    public void test() {
        int[] re = new Solution().countBits(5);
        System.out.println(Arrays.toString(re));
    }
}
