package leetcode_solutions.arrays;

import java.util.Arrays;

public class MergeSortedArray88 {
    //my solution;use quick sort
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i < nums2.length; i++) {
            nums1[m + i] = nums2[i];
        }
        Arrays.sort(nums1);
    }

    public void merge0(int[] nums1, int m, int[] nums2, int n) {
        if (nums1.length < m + n) {
            return;
        }

        int index = m + n - 1;
        m--;
        n--;

        while (m >= 0 && n >= 0) {
            if (nums1[m] > nums2[n]) {

                nums1[index] = nums1[m];
                index--;
                m--;
            } else {
                nums1[index] = nums2[n];
                index--;
                n--;
            }
        }
        while (n >= 0) {
            nums1[index] = nums2[n];
            index--;
            n--;
        }
    }

    //same idea with merge0
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        int len = m + n - 1;
        m = m - 1;
        n = n - 1;
        while (m >= 0 && n >= 0 && len >= 0) {
            if (nums1[m] > nums2[n]) {
                nums1[len--] = nums1[m--];
            } else {
                nums1[len--] = nums2[n--];
            }
        }
        while (n >= 0 && len >= 0) {
            nums1[len--] = nums2[n--];
        }
    }
}
