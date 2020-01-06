package leetcode_solutions;

public class NumberofLongestIncreasingSubsequence673 {
    class Solution {
        public int findNumberOfLIS(int[] nums) {
            /**
             *
             * 解决方案：获取以数组nums每个元素结尾序列的最长升序子序列长度，然后，取其最大者。
             * 方案一，采用dp方式，其时间复杂度为 (n^2 - n) / 2
             *
             * 方案二，采用贪心策略，思路：构建有序数组a，将数组nums各元素按大小（ps：小 -> 大）存放至数组a，通过元素在数组a所在位置，得到该元素在数组nums，以该元素结尾最长升序子序列长度
             * 如：对于数组nums[10,9,2,5,3,7,101,18]，依次将各元素按大小存放至有序数组a：
             *  1.）存放元素10，数组a[10]  re=1
             *  2.）存放元素9，由于9 < 10（ps：后续比10大的元素一定大于元素9），因而将元素9存放至数组a，替代元素10，数组a[9]  re=2
             *  3.）存放元素2，由于2 < 9，因而将元素2存放至数组a，替代元素9，数组a[2] re=3
             *  4.）存放元素5，由于2 < 5，因而将元素5存放至数组a，置于元素2后，数组a[2, 5] re=1
             *  5.）存放元素3，由于 3 < 5（ps：后续比5大的元素一定大于元素3） && 2 < 3，因而将元素 3 替代元素5，存放至数组a，数组a[2, 3] re=2
             *  6.）存放元素7，由于 3 < 7，因而将元素7存放至数组a，置于元素3后，数组a[2, 3, 7] re=2
             *  7.）存放元素101，由于 7 < 101，因而将元素101存放至数组a，置于元素7后，数组a[2, 3, 7, 101] re=2
             *  8.）存放元素18，由于 7 < 18 && 18 < 101（ps：后续比101大的元素一定大于元素18），因而将元素 18 替代元素 101，存放至数组a，数组a[2, 3, 7, 18] re=4
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
}
