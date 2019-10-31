package leetcode_solutions;

import org.junit.Test;

import java.util.Arrays;

public class SingleNumberIII260 {
    class Solution {
        public int[] singleNumber(int[] nums) {
            int[] re = new int[2];
            Arrays.sort(nums);
            boolean flag = false;
            for (int i = 0; i < nums.length; i++) {
                if (i + 1 < nums.length && nums[i + 1] == nums[i]) i++;
                else {
                    if (!flag) {
                        re[0] = nums[i];
                        flag = true;
                    } else {
                        re[1] = nums[i];
                        return re;
                    }
                }
            }
            return re;
        }

    }

    class Solution1 {
        public int[] singleNumber(int[] nums) {
            // 假设只出现一次的两个数是a，b
            int[] res = new int[2];

         /*我们的初步想法是将问题转化成恰好有一个元素只出现一次的情况
         如果a和b不是同一个元素，那么a^b的结果一定不为0，那么二进制
         结果中必然有一位为1。此位置异或为1，说明两个数在这个位置一个
         为1，一个为0.我们的思路是找到这个位置，将原数组一分为二，这样
         就转化成了恰好有一个元素只出现一次的情况。*/
            int xor = 0;
            for (int n : nums) {
                xor = xor ^ n;
            }
            int mask = xor & (-xor);

         /*比如本题中a^b=110，在找值为1的位置的时候，因为是任意位置的
         1，为了方便，取了最右边的1。由于已经假设这是最右面的一位，所
         以这位右面一定是0，将diff取反之后，还是以110为例，变成001，
         这样diff&(~diff)一定每一个位置都是0,我们将~diff加一，这样
         ~diff右边的1会有进位，变成010，我们做与操作，返回010*/
         /*
         通过与这个mask进行与操作，如果为0的分为一个数组，为1的分为另一个数组。
         这样就把问题降低成了：“有一个数组每个数字都出现两次，有一个数字只出现了一次，求出该数字”。
         对这两个子问题分别进行全异或就可以得到两个解。也就是最终的数组了。
          */
            for (int n : nums) {
                if ((n & mask) == 0) {
                    //在每一组中找到那个只出现一次的数
                    res[0] ^= n;
                } else
                    res[1] ^= n;
            }
            return res;
        }
    }

    @Test
    public void test() {

    }

}
