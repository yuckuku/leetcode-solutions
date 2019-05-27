package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.TreeMap;

/**
 * @author: L'Nan
 * Date: 2019/5/24 10:25
 * Description:
 */
public class AdvantageShuffle870 {
    /**
     * TLE
     */
    class Solution {
        public int[] advantageCount(int[] A, int[] B) {
            int len = A.length;
            int[] re = new int[len];
            Arrays.sort(A);
            int i = len - 1;
            for (; i >= 0; i--) {
                int temp = Integer.MAX_VALUE;
                int nextIndex = -1;
                for (int j = 0; j < len; j++) {
                    if (re[j] != 0) continue;
                    if (A[i] > B[j] && A[i] - B[j] < temp) {
                        temp = A[i] - B[j];
                        nextIndex = j;
                    }
                }
                if (nextIndex != -1) {
                    re[nextIndex] = A[i];
                } else {
                    break;
                }
            }
            for (; i >= 0; i--) {
                for (int j = 0; j < len; j++) {
                    if (re[j] == 0) {
                        re[j] = A[i];
                        break;
                    }
                }
            }
            return re;
        }
    }

    class SolutionGreedy {
        public int[] advantageCount(int[] A, int[] B) {
            int size = A.length;
            TreeMap<Integer, LinkedList<Integer>> mapB = new TreeMap<>();
            for (int i = 0; i < size; i++) {
                LinkedList<Integer> idxList =
                        mapB.getOrDefault(B[i], new LinkedList<>());
                idxList.add(i);
                mapB.putIfAbsent(B[i], idxList);
            }
            int[] ans = new int[size];
            for (int i = 0; i < size; i++) {
                //在B中取出大于a的最小元素对应的任意下标；
                // 若不存在大于a的元素，则取出当前B中的最大元素对应的下标，记为idx；
                Integer key = mapB.lowerKey(A[i]);
                if (key == null) {
                    key = mapB.lastKey();
                }
                LinkedList<Integer> idxList = mapB.get(key);
                int index = idxList.removeLast();
                ans[index] = A[i];
                if (idxList.isEmpty()) {
                    mapB.remove(key);
                }
            }
            return ans;
        }
    }

    class SolutionOnLeetcode1 {
        public int[] advantageCount(int[] A, int[] B) {
            int[] indices = sort(B);
            Arrays.sort(A);
            int min = 0;
            int max = A.length - 1;
            int index = B.length - 1;
            int[] result = new int[A.length];
            while (index >= 0) {
                if (B[index] < A[max]) {
                    result[indices[index]] = A[max];
                    max--;
                    index--;
                } else {
                    result[indices[index]] = A[min];
                    min++;
                    index--;
                }
            }
            return result;
        }

        private int[] sort(int[] b) {
            int[] indices = new int[b.length];
            for (int i = 0; i < b.length; i++) {
                indices[i] = i;
            }
            partition(b, 0, b.length - 1, indices);
            return indices;
        }

        private void partition(int[] b, int start, int end, int[] indices) {
            if (start >= end) {
                return;
            }
            int endPoint = end;
            int startPoint = start + 1;
            while (true) {
                while (endPoint >= start && b[endPoint] >= b[start])
                    endPoint--;
                while (startPoint <= end && b[startPoint] <= b[start])
                    startPoint++;
                if (endPoint <= startPoint) {
                    break;
                }
                swap(b, startPoint, endPoint);
                swap(indices, startPoint, endPoint);

            }
            swap(b, start, startPoint - 1);
            swap(indices, start, startPoint - 1);
            partition(b, start, startPoint - 2, indices);
            partition(b, startPoint, end, indices);
        }

        private void swap(int[] b, int j, int i) {
            int mid = b[i];
            b[i] = b[j];
            b[j] = mid;
        }
    }

    @Test
    public void test() {
        int[] A = new int[]{718967141, 189971378, 341560426, 23521218, 339517772};
        int[] B = new int[]{967482459, 978798455, 744530040, 3454610, 940238504};
        Solution s = new Solution();
        int[] re = s.advantageCount(A, B);
        System.out.println(Arrays.toString(re));

        int advantage = advantage(re, B);
        System.out.println(advantage);

        int[] temp = new int[]{718967141, 341560426, 339517772, 23521218, 189971378};
        System.out.println(advantage(temp, B));

    }

    private int advantage(int[] A, int[] B) {
        int advantage = 0;
        int len = A.length;
        for (int i = 0; i < len; i++) {
            if (A[i] > B[i]) advantage++;
        }
        return advantage;
    }
}
