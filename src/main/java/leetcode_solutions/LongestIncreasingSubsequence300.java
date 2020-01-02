package leetcode_solutions;

import org.junit.Test;

import java.util.*;

public class LongestIncreasingSubsequence300 {
    //执行用时:150ms,在所有java提交中击败了5.08%的用户内存消耗:38.3MB,在所有java提交中击败了5.01%的用户
    //用双向链表代替map是不是会快
    class Solution {
        public int lengthOfLIS(int[] nums) {
            int len = nums.length;
            if (len == 0) return 0;
            if (len == 1) return 1;
            int max = 1;
//            int[] DP = new int[len + 1];
            Map<Integer, Set> map = new HashMap<>();
            Set<Integer> set1 = new HashSet<>();
//            DP[1]=1;
            set1.add(0);
            map.put(1, set1);

            loop1:
            for (int i = 1; i < len; i++) {
                boolean flag = false;

                //是否是最大
                Set<Integer> setMax = map.get(max);
                for (Integer index : setMax) {
                    if (nums[i] > nums[index]) {
                        //新增
                        max++;
                        Set<Integer> setTmp = new HashSet<>();
                        setTmp.add(i);
                        map.put(max, setTmp);
                        continue loop1;
                    }
                }

                //不是最大，加入前面set
                int indexToAdd = -1;
                loop2:
                for (int j = max - 1; j >= 1; j--) {
                    Set<Integer> set = map.get(j);
                    for (Integer index : set) {
                        if (nums[i] > nums[index]) {
                            indexToAdd = j + 1;
                            break loop2;
                        }
                    }
                }
                indexToAdd = indexToAdd == -1 ? 1 : indexToAdd;
                map.get(indexToAdd).add(i);
            }
            return max;
        }

    }

    @Test
    public void test() {
        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        Solution solution = new Solution();
        int re = solution.lengthOfLIS(nums);
        System.out.println(re);
    }

    class Solution0ms {
        public int lengthOfLIS(int[] nums) {

            /**
             *
             * 解决方案：获取以数组nums每个元素结尾序列的最长升序子序列长度，然后，取其最大者。
             * 方案一，采用dp方式，其时间复杂度为 (n^2 - n) / 2
             *
             * 方案二，采用贪心策略，思路：构建有序数组a，将数组nums各元素按大小（ps：小 -> 大）存放至数组a，通过元素在数组a所在位置，得到该元素在数组nums，以该元素结尾最长升序子序列长度
             * 如：对于数组nums[10,9,2,5,3,7,101,18]，依次将各元素按大小存放至有序数组a：
             *  1.）存放元素10，数组a[10]
             *  2.）存放元素9，由于9 < 10（ps：后续比10大的元素一定大于元素9），因而将元素9存放至数组a，替代元素10，数组a[9]
             *  3.）存放元素2，由于2 < 9，因而将元素2存放至数组a，替代元素9，数组a[2]
             *  4.）存放元素5，由于2 < 5，因而将元素5存放至数组a，置于元素2后，数组a[2, 5]
             *  5.）存放元素3，由于 3 < 5（ps：后续比5大的元素一定大于元素3） && 2 < 3，因而将元素 3 替代元素5，存放至数组a，数组a[2, 3]
             *  6.）存放元素7，由于 3 < 7，因而将元素7存放至数组a，置于元素3后，数组a[2, 3, 7]
             *  7.）存放元素101，由于 7 < 101，因而将元素101存放至数组a，置于元素7后，数组a[2, 3, 7, 101]
             *  8.）存放元素18，由于 7 < 18 && 18 < 101（ps：后续比101大的元素一定大于元素18），因而将元素 18 替代元素 101，存放至数组a，数组a[2, 3, 7, 18]
             *
             *  结束
             *
             *  注：上述过程，数组a各元素
             */

            int len = nums.length;

            if (0 == len) return 0;

            //初始化数组a
            int[] a = new int[len];
            a[0] = nums[0];
            int maxOffsetOfA = 0;//数组a最后一个元素所在位置（ps：索引，下标0开始）

            int left, right, middle = 0, num;//用于二分
            boolean beFound;//num在数组a是否被找到，true：找到，false：没找到

            for (int i = 1; i < len; i++) {
                num = nums[i];
                if (num > a[maxOffsetOfA]) {
                    a[++maxOffsetOfA] = num;
                    continue;
                } else if (num == a[maxOffsetOfA]) {
                    continue;
                } else {
                    beFound = false;
                    //1.）找元素nums[i]在数组a对应位置
                    left = 0;
                    right = maxOffsetOfA;
                    while (left <= right) {
                        middle = (left + right) / 2;
                        if (a[middle] > num) {
                            right = middle - 1;
                        } else if (a[middle] < num) {
                            left = middle + 1;
                        } else {
                            //found it
                            beFound = true;
                            break;
                        }
                    }
                    //经上述二分检索过程，当在数组a找到对应元素num时，middle值为num在数组a index，当num在数组a不存在时，left值为数组a中较num大的最小元素（ps：数组a[2, 5]，查找元素3，middle为1，即元素5）
                    if (beFound) {
                        //找到 do nothing

                    } else {
                        //未找到，用num替换middle值对应位置元素
                        //将元素nums[i]存放至数组a对应位置
                        a[left] = num;
                    }
                }
            }

            return maxOffsetOfA + 1;

        }


    }

    public class Solution1ms {
        public int lengthOfLIS(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            int[] dp = new int[nums.length];
            dp[0] = nums[0];
            int len = 1;
            for (int index = 1; index < nums.length; index++) {
                int i = Arrays.binarySearch(dp, 0, len, nums[index]);
                if (i < 0) {
                    i = -(i + 1);
                }
                dp[i] = nums[index];
                if (i == len) {
                    len++;
                }
            }
            return len;
        }
    }
}
