package leetcode_solutions;

import java.util.*;
import java.util.stream.Collectors;

public class SortanArray912 {
    //执行用时:17ms,在所有Java提交中击败了37.61%的用户
    //内存消耗:47.2MB,在所有Java提交中击败了100.00%的用户
    class Solution {
        public List<Integer> sortArray(int[] nums) {
            Arrays.sort(nums);
            List<Integer> re = Arrays.stream(nums).boxed().collect(Collectors.toList());
            return re;
        }
    }

    class Solution2ms {
        public int[] sortArray(int[] nums) {
            if (nums.length < 1) {
                return nums;
            }
            int min = nums[0], max = nums[0];

            for (int i = 0; i < nums.length; i++) {

                min = min < nums[i] ? min : nums[i];
                max = max > nums[i] ? max : nums[i];
            }

            int len = max - min + 1;

            int[] bucket = new int[len];

            for (int i = 0; i < nums.length; i++) {

                bucket[nums[i] - min]++;
            }

            int j = 0;
            for (int i = 0; i < bucket.length; i++) {
                for (int k = 0; k < bucket[i]; k++) {
                    nums[j++] = i + min;
                }

            }
            return nums;
        }
    }

    class Solution3ms {
        public List<Integer> sortArray(int[] nums) {
            return counting(nums);
        }

        // counting sort;
        private List<Integer> counting(int[] nums) {

            List<Integer> ans = new ArrayList<>();
            int[] bucks = new int[100000 + 1];
            for (int i = 0; i < nums.length; i++) {
                bucks[nums[i] + 50000] += 1;
            }

            for (int i = 0; i < bucks.length; i++) {
                if (bucks[i] > 0) {
                    for (int j = 0; j < bucks[i]; j++) ans.add(i - 50000);
                }
            }
            return ans;
        }


        // recursive quicksort;
        private List<Integer> quickSort1(List<Integer> nums) {

            if (nums.size() == 0 || nums.size() == 1) return nums;
            if (nums.size() == 2) {
                if (nums.get(0) < nums.get(1)) return nums;
                int n = nums.get(0);
                nums.set(0, nums.get(1));
                nums.set(1, n);
                return nums;
            }
            int i = (int) (Math.random() * nums.size());
            int pivot = nums.get(i);
            List<Integer> left = new LinkedList<>();
            List<Integer> right = new LinkedList<>();
            for (int n : nums) {
                if (n < pivot) {
                    left.add(n);
                } else {
                    right.add(n);
                }
            }
            left = quickSort1(left);
            right = quickSort1(right);
            left.addAll(right);
            return left;
        }

        // iterative quicksort
        private List<Integer> quickSort2(List<Integer> nums) {

            Stack<List<Integer>> s = new Stack<>();
            s.push(nums);
            List<Integer> ans = new LinkedList<>();
            while (!s.empty()) {
                List<Integer> l = s.pop();
                if (l.size() == 0 || l.size() == 1) {
                    ans.addAll(l);
                    continue;
                }
                if (l.size() == 2) {
                    if (l.get(0) > l.get(1)) {
                        int n = l.get(0);
                        l.set(0, l.get(1));
                        l.set(1, n);
                    }
                    ans.addAll(l);
                    continue;
                }
                int i = (int) (Math.random() * l.size());
                int pivot = l.get(i);
                List<Integer> left = new LinkedList<>();
                List<Integer> right = new LinkedList<>();
                for (int n : l) {
                    if (n < pivot) {
                        left.add(n);
                    } else {
                        right.add(n);
                    }
                }
                s.push(right);
                s.push(left);
            }
            return ans;
        }

    }
}
