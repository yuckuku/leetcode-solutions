package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: L'Nan
 * @Date: 2018/12/25 20:28
 * @Description: Given an array arr that is a permutation of [0, 1, ..., arr.length - 1], we split the array into some number of "chunks" (partitions), and individually sort each chunk.  After concatenating them, the result equals the sorted array.
 * <p>
 * What is the most number of chunks we could have made?
 */
public class MaxChunksToMakeSorted769 {

    /**
     * my solution:wrong ans
     */
    class Solution {
        public int maxChunksToSorted(int[] arr) {
            Map<Integer, Integer> map = new HashMap<>();

            for (int i = 0; i < arr.length; i++) {
                map.put(arr[i], i);
            }

            int re = 0;
            int index = -1;
            for (int i = 0; i < arr.length; i++) {
                if (map.get(i) < index) {
                    continue;
                }
                if (map.get(i) > index) {
                    re++;
                    index = map.get(i);
                }
            }

            return re;
        }
    }

    /**
     * 归位原理：maxn是第0～i个数字中的最大值，遍历的过程中如果maxn==i，就保证了前面i-1个数字必然都比maxn小（因为maxn是0～i中的最大值），则第0～i个数字必然能排列成正确顺序，以此类推，找到下一个满足maxn==i的地方（记为j），则i+1～j又能分为一个块…直到遍历到最后一个数为止得到答案～
     */
    class Solution0 {
        public int maxChunksToSorted(int[] arr) {
            int ans = 0;
            for (int i = 0, maxn = 0; i < arr.length; i++) {
                maxn = Math.max(arr[i], maxn);
                if (maxn == i) ans++;
            }
            return ans;

        }
    }


    @Test
    public void test() {
        int[] arr
//        =new int[]{4,3,2,1,0};
//                = new int[]{2, 0, 1};
//                = new int[]{4, 3, 2, 1, 0};
                = new int[]{1, 0, 2, 3, 4};
//        System.out.println(new Solution().maxChunksToSorted(arr));

        System.out.println(new Solution0().maxChunksToSorted(arr));
    }
}
