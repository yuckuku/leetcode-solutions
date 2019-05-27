package leetcode_solutions.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: L'Nan
 * Date: 2019/4/8 14:14
 * Description:
 */
public class PancakeSorting969 {
    class Solution {
        public List<Integer> pancakeSort(int[] A) {
            List<Integer> re = new ArrayList<>();
            int length = A.length;
            int temp[] = new int[length];
            System.arraycopy(A, 0, temp, 0, length);
            Arrays.sort(temp);
            for (int i = length - 1; i >= 0; i--) {
                if (temp[i] == A[i]) {
                    continue;
                }
                int j = i;
                while (A[j] != temp[i] && j >= 0) {
                    j--;
                }
                re.add(j + 1);
                swap(A, j);
                re.add(i + 1);
                swap(A, i);
            }
            return re;
        }

        private void swap(int[] A, int index) {
            int left = 0;
            int right = index;
            while (left < right) {
                int temp = A[left];
                A[left] = A[right];
                A[right] = temp;
                left++;
                right--;
            }
        }
    }

    /**
     * 如果元素不是从1到n呢？
     */
    class SolutionOnLeetCode1 {
        public List<Integer> pancakeSort(int[] A) {
            List<Integer> res = new ArrayList<>();
            for (int i = A.length - 1; i > 0; i--) {
                if (A[i] == i + 1) continue;
                for (int j = i - 1; j > 0; j--) {
                    if (A[j] == i + 1) {
                        reverse(A, j);
                        res.add(j + 1);
                    }
                }
                reverse(A, i);
                res.add(i + 1);
            }
            return res;
        }

        private void reverse(int[] A, int index) {
            if (index < 1) return;
            for (int i = 0; i < index; i++) {
                int temp = A[i];
                A[i] = A[index];
                A[index--] = temp;
            }
        }
    }
}
