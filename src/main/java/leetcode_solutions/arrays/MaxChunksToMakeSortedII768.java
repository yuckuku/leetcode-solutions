package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Auther: L'Nan
 * @Date: 2018/12/25 20:27
 * @Description: This question is the same as "Max Chunks to Make Sorted" except the integers of the given array are not necessarily distinct, the input array could be up to length 2000, and the elements could be up to 10**8.
 * <p>
 * Given an array arr of integers (not necessarily distinct), we split the array into some number of "chunks" (partitions), and individually sort each chunk.  After concatenating them, the result equals the sorted array.
 */
public class MaxChunksToMakeSortedII768 {

    class Solution {
        public int maxChunksToSorted(int[] arr) {
            int ans = 0;
            for (int i = 0; i < arr.length; i++) {
                int[] left = Arrays.copyOfRange(arr, 0, i);
                int[] right = Arrays.copyOfRange(arr, i, arr.length);
                if (check(left, right)) {
                    ans++;
                }
            }
            return ans;
        }

        private boolean check(int[] left, int[] right) {
            Arrays.sort(left);
            Arrays.sort(right);

            return (left.length == 0 || left[left.length - 1] <= right[0]) ? true : false;
        }
    }

    class BetterSolution {
        public int maxChunksToSorted(int[] arr) {
            int[] maxOfLeft = new int[arr.length];
            int[] minOfRight = new int[arr.length];

            int max = Integer.MIN_VALUE;
            for(int i=0; i<arr.length; i++){
                if(arr[i] > max){
                    max = arr[i];
                }
                maxOfLeft[i] = max;
            }

            int min = Integer.MAX_VALUE;
            for(int i=arr.length-1; i>=0; i--){
                if(min > arr[i]){
                    min = arr[i];
                }
                minOfRight[i] = min;
            }

            int result = 0;
            for(int i=0; i<arr.length-1; i++){
                if(maxOfLeft[i] <= minOfRight[i+1]){
                    result++;
                }
            }

            return result+1;
        }
    }

    @Test
    public void test() {
        int[] arr = new int[]{2, 1, 3, 4, 4};
        int[] right = Arrays.copyOfRange(arr, 5, 5);
        for (int i : right) {
            System.out.println(i);
        }
    }
}
