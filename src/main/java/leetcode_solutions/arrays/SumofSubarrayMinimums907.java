package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.Stack;

/**
 * @author: L'Nan
 * Date: 2019/6/11 14:34
 * Description:
 */
public class SumofSubarrayMinimums907 {
    class Solution {
        public int sumSubarrayMins(int[] A) {
            int mod = (int) (1e9 + 7);
            int len = A.length;
            int[] f = new int[len];
            Stack<int[]> leftStack = new Stack<>();
            Stack<int[]> rightStack = new Stack<>();
            for (int i = 0; i < len; i++) {
                f[i] += nextLeft(A[i], leftStack);
            }
            for (int i = len - 1; i >= 0; i--) {
                f[i] *= nextRight(A[i], rightStack);
            }
            int re = 0;
            for (int i = 0; i < len; i++) {
                re = (re + (A[i] * f[i])) % mod;
            }

            return re;
        }

        public int nextLeft(int price, Stack<int[]> stack) {
            int res = 1;
            while (!stack.isEmpty() && stack.peek()[0] > price)
                res += stack.pop()[1];
            stack.push(new int[]{price, res});
            return res;
        }

        public int nextRight(int price, Stack<int[]> stack) {
            int res = 1;
            while (!stack.isEmpty() && stack.peek()[0] >= price)
                res += stack.pop()[1];
            stack.push(new int[]{price, res});
            return res;
        }
    }

    class SolutionOnline1 {
        public int sumSubarrayMins(int[] A) {
            Stack<Integer> s = new Stack<>();
            int n = A.length, res = 0, mod = (int) 1e9 + 7, j, k;
            for (int i = 0; i <= n; i++) {
                while (!s.isEmpty() && A[s.peek()] > (i == n ? 0 : A[i])) {
                    j = s.pop();
                    k = s.isEmpty() ? -1 : s.peek();
                    res = (res + A[j] * (i - j) * (j - k)) % mod;
                }
                s.push(i);
            }
            return res;
        }
    }

    class SolutionLeetcode1 {
        public int sumSubarrayMins(int[] A) {
            int[] s = new int[A.length];
            int top = -1, res = 0, mid, left, mod = (int) (1e9 + 7);
            for (int right = 0; right <= A.length; right++) {
                while (top != -1 && A[s[top]] > (right == A.length ? 0 : A[right])) {
                    mid = s[top--];
                    left = top == -1 ? -1 : s[top];
                    res = (res + A[mid] * (right - mid) * (mid - left)) % mod;
                }
                s[++top] = right;
            }
            return res;
        }
    }

    class SolutionOnLeetcode2 {
        public int sumSubarrayMins(int[] A) {
            final int MOD = (int) (1e9) + 7;
            int length = A.length;
            long result = 0;
            int[] lmin = new int[length];
            int[] rmin = new int[length];
            //右边所能到达的极限
            for (int i = 0; i < A.length; i++) {
                lmin[i] = i;
                int index = i - 1;
                while (index >= 0 && A[index] > A[i]) {
                    lmin[i] = lmin[index];
                    index = lmin[index] - 1;
                }
            }
            //左边所能到达的极限
            for (int i = length - 1; i >= 0; i--) {
                rmin[i] = i;
                int index = i + 1;
                while (index < length && A[index] >= A[i]) {
                    rmin[i] = rmin[index];
                    index = rmin[index] + 1;
                }
            }
        /*
        没有也是一种可能
        以A[i]为最小值的连续数组个数为左边大于A[i]的元素个数+1 * 右边大于等于A[i]元素个数+1
        */
            for (int i = 0; i < length; i++) {
                result += (i - lmin[i] + 1) * (rmin[i] - i + 1) * A[i];
            }
            return (int) (result % MOD);
        }
    }


    @Test
    public void test() {
        int[] A = new int[]{3, 1, 2, 4};
        SolutionOnline1 s = new SolutionOnline1();
        int re = s.sumSubarrayMins(A);
        System.out.println(re);
//        System.out.println((int) (1e9 + 7));
    }
}
