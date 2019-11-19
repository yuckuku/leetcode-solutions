package leetcode_solutions;

import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

public class KthLargestElementinanArray215 {
    class Solution {
        public int findKthLargest(int[] nums, int k) {
            int targetIndex = nums.length - 1 - (k - 1);
            return find(nums, 0, nums.length - 1, targetIndex);
        }

        public int find(int[] nums, int l, int r, int targetIndex) {
            int index = getIndex(nums, l, r);
            if (index == targetIndex) {
                return nums[index];
            } else if (index > targetIndex) {
                //在左边
                return find(nums, l, index - 1, targetIndex);
            } else {
                //在右边
                return find(nums, index + 1, r, targetIndex);
            }
        }

        private int getIndex(int[] arr, int low, int high) {
            // 基准数据
            int tmp = arr[low];
            while (low < high) {
                // 当队尾的元素大于等于基准数据时,向前挪动high指针
                while (low < high && arr[high] >= tmp) {
                    high--;
                }
                // 如果队尾元素小于tmp了,需要将其赋值给low
                arr[low] = arr[high];
                // 当队首元素小于等于tmp时,向前挪动low指针
                while (low < high && arr[low] <= tmp) {
                    low++;
                }
                // 当队首元素大于tmp时,需要将其赋值给high
                arr[high] = arr[low];

            }
            // 跳出循环时low和high相等,此时的low或high就是tmp的正确索引位置
            // 由原理部分可以很清楚的知道low位置的值并不是tmp,所以需要将tmp赋值给arr[low]
            arr[low] = tmp;
            return low; // 返回tmp的正确位置
        }
    }

    class Solution1 {
        public int findKthLargest(int[] nums, int k) {
            Arrays.sort(nums);
            int i = nums.length - 1;
            for (; i >= 0 && k > 1; i--, k--) {
            }
            return nums[i];
        }
    }

    class SolutionHeap {
        public int findKthLargest(int[] nums, int k) {
            // init heap 'the smallest element first'
            PriorityQueue<Integer> heap =
                    new PriorityQueue<Integer>((n1, n2) -> n1 - n2);

            // keep k largest elements in the heap
            for (int n : nums) {
                heap.add(n);
                if (heap.size() > k)
                    heap.poll();
            }

            // output
            return heap.poll();
        }
    }

    class SolutionQuickPick {
        int[] nums;

        public void swap(int a, int b) {
            int tmp = this.nums[a];
            this.nums[a] = this.nums[b];
            this.nums[b] = tmp;
        }


        public int partition(int left, int right, int pivot_index) {
            int pivot = this.nums[pivot_index];
            // 1. move pivot to end
            swap(pivot_index, right);
            int store_index = left;

            // 2. move all smaller elements to the left
            for (int i = left; i <= right; i++) {
                if (this.nums[i] < pivot) {
                    swap(store_index, i);
                    store_index++;
                }
            }

            // 3. move pivot to its final place
            swap(store_index, right);

            return store_index;
        }

        public int quickselect(int left, int right, int k_smallest) {
    /*
    Returns the k-th smallest element of list within left..right.
    */

            if (left == right) // If the list contains only one element,
                return this.nums[left];  // return that element

            // select a random pivot_index
            Random random_num = new Random();
            int pivot_index = left + random_num.nextInt(right - left);

            pivot_index = partition(left, right, pivot_index);

            // the pivot is on (N - k)th smallest position
            if (k_smallest == pivot_index)
                return this.nums[k_smallest];
                // go left side
            else if (k_smallest < pivot_index)
                return quickselect(left, pivot_index - 1, k_smallest);
            // go right side
            return quickselect(pivot_index + 1, right, k_smallest);
        }

        public int findKthLargest(int[] nums, int k) {
            this.nums = nums;
            int size = nums.length;
            // kth largest is (N - k)th smallest
            return quickselect(0, size - 1, size - k);
        }
    }


    @Test
    public void test() {
        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
        int k = 2;
        Solution solution = new Solution();
        int re = solution.findKthLargest(nums, k);
        System.out.println(re);
    }

}
