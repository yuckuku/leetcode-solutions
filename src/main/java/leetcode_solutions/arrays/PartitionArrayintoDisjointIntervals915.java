package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Auther: L'Nan
 * @Date: 2018/12/4 21:51
 * @Description: Given an array A, partition it into two (contiguous) subarrays left and right so that:
 * Every element in left is less than or equal to every element in right.
 * left and right are non-empty.
 * left has the smallest possible size.
 * Return the length of left after such a partitioning.  It is guaranteed that such a partitioning exists.
 */
public class PartitionArrayintoDisjointIntervals915 {
    /**
     * my solution: find first index where conditions are satisfied, but turns to be time limited.
     */
    class MySolution0 {
        public int partitionDisjoint(int[] A) {
            int leftMax = 0;
            int index = 0;
            for (; index < A.length; index++) {
                leftMax = Math.max(leftMax, A[index]);
                if (disjoint(leftMax, Arrays.copyOfRange(A, index + 1, A.length))) {
                    break;
                }
            }
            return index + 1;
        }

        private boolean disjoint(int leftMax, int[] array) {
            Arrays.sort(array);
            return array[0] < leftMax ? false : true;
        }
    }

    /**
     * my solution: divide, turns to be wrong answer.
     */
    class MySolution1 {
        public int partitionDisjoint(int[] A) {

            int index = A.length / 2;
            boolean flag = false;
            while (index >= 0 && index < A.length) {
                boolean leftFlag = true;
                for (int j = index; j < A.length; j++) {
                    if (!leftFlag) {
                        break;
                    }
                    for (int i = 0; i < index; i++) {
                        if (A[i] > A[j]) {
                            leftFlag = false;
                            break;
                        }
                        leftFlag = true;
                    }
                }


                if ((flag && !leftFlag) || index == 0) {
                    break;
                }

                if (leftFlag) {
                    index--;
                } else {
                    index++;
                }

                flag = leftFlag;
            }

            return index + 1;
        }
    }

    @Test
    public void test() {
//        int[] A = new int[]{5, 0, 3, 8, 6};
        int[] A = new int[]{1, 1};
        int re = new MySolution1().partitionDisjoint(A);

        System.out.println(re);
    }

    /**
     * @discription: use heap
     */
    class Solution_heap {

        public int partitionDisjoint(int[] A) {
            int sizeA = A.length;
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(sizeA, (o1, o2) -> o2 - o1);

            for (int i = 0; i < sizeA; i++) {
                if (i == 0) maxHeap.add(A[i]);
                else minHeap.add(A[i]);
            }

            // left <= right
            for (; ; ) {
                int topMinHeap = minHeap.peek();
                int topMaxHeap = maxHeap.peek();
                if (topMaxHeap <= topMinHeap) {
                    return maxHeap.size();
                } else {
                    int temp = A[maxHeap.size()];
                    maxHeap.add(temp);
                    minHeap.remove(temp);
                }
            }
        }
    }

    @Test
    public void testHeap() {
        int[] A = new int[]{5, 0, 8, 1, 8};
//        int[] A = new int[]{1, 1};
        int re = new Solution_heap().partitionDisjoint(A);

        System.out.println(re);
    }

}
