package leetcode_solutions.arrays;

import org.junit.Test;

import javax.swing.text.rtf.RTFEditorKit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class NumberofSubarrayswithBoundedMaximum795 {

    private List<Integer> list;

    List<int[]> subArrays;

    /**
     * my solution:
     * 1.find all sub arrays split by element bigger than R;
     * 2.find each array's num, add them all
     * 3.use x1*(x2+x3+x4)+x2*(x3+x4)+x3*x4 to find each array's num
     */
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        int re = 0;

        findAllArrays(A, R);

        for (int[] temp :
                subArrays) {
            getMaxElements(temp, L, R);
            re += function(list);
            list.clear();
        }

        return re;
    }

    private void findAllArrays(int[] A, int R) {
        subArrays = new ArrayList<>();
        int lastIndex = 0;
        int i = 0;
        for (; i < A.length; i++) {
            if (A[i] > R) {
                if (i != lastIndex) {
                    subArrays.add(Arrays.copyOfRange(A, lastIndex, i));
                }
                if (i < A.length - 1) {
                    lastIndex = i + 1;
                }
            } else if (i == A.length - 1) {
                subArrays.add(Arrays.copyOfRange(A, lastIndex, A.length));
            }
        }
    }

    private int function(List<Integer> list) {
        if (list.size() <= 1) {
            return 0;
        }
        int re = 0;
        for (int i = 1; i < list.size(); i++) {
            re += list.get(i);
        }
        re = list.get(0) * re;
        list.remove(0);
        return re + function(list);
    }

    private void getMaxElements(int[] A, int L, int R) {
        list = new ArrayList<>();
        int lastIndex = -1;
        int i = 0;
        for (; i < A.length; i++) {
            if (A[i] <= R && A[i] >= L) {
                list.add(i - lastIndex);
                lastIndex = i;
            }

        }
        list.add(i - lastIndex);
    }

    @Test
    public void test() {
        //re=3
//        int[] A = new int[]{2, 1, 4, 3};
//        int L = 2;
//        int R = 3;

        //re=5
        int[] A = new int[]{45, 2, 49, 6, 55, 73, 27, 17, 4, 71};
        int L = 17;
        int R = 33;

//        findAllArrays(A, 3);


        int re = numSubarrayBoundedMax(A, L, R);
        System.out.println(re);
    }

    /**
     * solution on leetcode
     */
    class Solution {
        //思路：
        //1 暴力法
        //找出所有的连续子数列，然后计算最值。再做比较
        //2 滑动窗口法：维持左右两个指针，如果不满足，则右边增加，如果满足，则左边减少
        // public int numSubarrayBoundedMax(int[] A, int L, int R) {
        //     int count = 0;
        //     for(int i = 0; i < A.length; i++){
        //         int big = A[i];
        //         for(int j = i; j < A.length; j++){
        //             big = Math.max(big, A[j]);
        //             if(big >= L && big <= R)
        //                 count++;
        //         }
        //     }
        //     return count;
        // }
        public int numSubarrayBoundedMax(int[] A, int L, int R) {
            int start = -1, last = -1, res = 0;
            for(int i = 0; i < A.length; i++) {
                if(A[i] > R) {
                    start = last = i;
                    continue;
                }

                if(A[i] >= L)
                    last = i;

                res += last - start;
            }

            return res;
        }
    }
}
