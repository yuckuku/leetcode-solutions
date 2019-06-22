package leetcode_solutions.arrays;

import org.junit.Test;

/**
 * @author: L'Nan
 * Date: 2019/6/20 09:43
 * Description:
 */
public class MedianofTwoSortedArrays4 {
    class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int m = nums1.length, n = nums2.length;
            if ((m + n) % 2 == 1) {
                if (0 == m) {
                    return nums2[n / 2];
                } else if (0 == n) {
                    return nums1[m / 2];
                }

                int index = -1;
                int element = 0;
                int i = 0, j = 0;
                while (index < (m + n) / 2) {
                    if (j >= n || (i < m && nums1[i] < nums2[j])) {
                        element = nums1[i];
                        i++;
                        index++;
                    } else {
                        element = nums2[j];
                        j++;
                        index++;
                    }
                }
                return element;
            } else {
                if (0 == m) {
                    return ((double) nums2[n / 2] + (double) nums2[(n / 2) - 1]) / 2;
                } else if (0 == n) {
                    return ((double) nums1[m / 2] + (double) nums1[(m / 2) - 1]) / 2;
                }

                int index = -1;
                int element1 = 0;
                int element2 = 0;
                int i = 0, j = 0;
                while (index < (m + n) / 2) {
                    if (j >= n || (i < m && nums1[i] < nums2[j])) {
                        element2 = element1;
                        element1 = nums1[i];
                        i++;
                        index++;
                    } else {
                        element2 = element1;
                        element1 = nums2[j];
                        j++;
                        index++;
                    }
                }

                return ((double) element1 + (double) element2) / 2;
            }
        }
    }

    class SolutionOnLeetcode1 {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int length1 = nums1.length;
            int length2 = nums2.length;
            int length = length1 + length2;
            int point = length % 2;

            int p = 0, q = 0, i = 0;
            double[] aws = new double[length];

            while (p < length1 && q < length2) {
                if (nums1[p] < nums2[q]) {
                    aws[i] = nums1[p];
                    p++;
                } else {
                    aws[i] = nums2[q];
                    q++;
                }
                i++;
            }

            while (length1 <= length2 && q < length2) {
                aws[i++] = nums2[q++];

            }
            while (length1 >= length2 && p < length1) {
                aws[i++] = nums1[p++];
            }

            if (point == 1) {
                return aws[length / 2];
            } else {
                return (aws[length / 2] + aws[length / 2 - 1]) / 2;
            }
        }
    }

    class SolutionOnLeetcode2 {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int totalIndex = nums1.length + nums2.length;
            int[] mergeNums = new int[totalIndex];

            if (totalIndex == 0) {
                return 0;
            }

            int media1 = totalIndex / 2;
            int media2 = -1;
            if (totalIndex % 2 == 0) {
                media2 = media1 - 1;
            }

            int ti = 0;
            int i = 0, j = 0;
            for (ti = 0; ti <= media1; ti++) {
                if (nums1.length == 0) {
                    mergeNums[ti] = nums2[j];
                    j++;
                } else if (nums2.length == 0) {
                    mergeNums[ti] = nums1[i];
                    i++;
                } else if (j < nums2.length && i < nums1.length && nums1[i] <= nums2[j]) {
                    mergeNums[ti] = nums1[i];
                    i++;
                } else if (j < nums2.length) {
                    mergeNums[ti] = nums2[j];
                    j++;
                } else if (j == nums2.length && i < nums1.length) {
                    mergeNums[ti] = nums1[i];
                    i++;
                }
            }

            double r = mergeNums[media1];
            if (media2 != -1) {
                r = (mergeNums[media1] + mergeNums[media2]) / 2.0;
            }
            return r;
        }
    }

    @Test
    public void test() {
        int[] nums1 = new int[]{};
        int[] nums2 = new int[]{2, 3};
        Solution s = new Solution();
        double re = s.findMedianSortedArrays(nums1, nums2);
        System.out.println(re);
    }
}
