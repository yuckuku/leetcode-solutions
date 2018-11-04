package leetcode_solutions.arrays;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.BitSet;

public class FairCandySwap888 {
    public int[] fairCandySwap(int[] A, int[] B) {
        int[] ans = new int[2];
        int sumA = 0;
        int sumB = 0;
        for (int i = 0; i < A.length; i++) {
            sumA += A[i];
        }
        for (int i = 0; i < B.length; i++) {
            sumB += B[i];
        }

        Arrays.sort(A);
        Arrays.sort(B);

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                if (A[i] - B[j] == (sumA - sumB) / 2) {
                    ans[0] = A[i];
                    ans[1] = B[j];
                    return ans;
                } else if (A[i] - B[j] < (sumA - sumB) / 2) {
                    break;
                }
            }
        }

        return ans;
    }

    public int[] fairCandySwap0(int[] A, int[] B) {
        int sumA= 0, sumB= 0;
        BitSet b = new BitSet();
        for (int i: A) {
            sumA+= i;
        }
        for (int i: B) {
            b.set(i, true);
            sumB+= i;
        }

        int temp= (sumA- sumB)/2;
        for (int i: A) {
            if(i- temp<= 0){
                continue;
            }
            if (b.get(i- temp)) {
                return new int[]{i, i- temp};
            }
        }
        return null;
    }
}
