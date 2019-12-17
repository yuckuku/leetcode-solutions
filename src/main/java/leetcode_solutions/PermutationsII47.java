package leetcode_solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class PermutationsII47 {
    class Solution {
        public List<List<Integer>> permuteUnique(int[] nums) {
            return null;
        }
    }

    public class Solution1 {

        private List<List<Integer>> res = new ArrayList<>();
        private boolean[] used;

        private void findPermuteUnique(int[] nums, int depth, Stack<Integer> stack) {
            if (depth == nums.length) {
                res.add(new ArrayList<>(stack));
                return;
            }
            for (int i = 0; i < nums.length; i++) {
                if (!used[i]) {
                    // 修改 2：因为排序以后重复的数一定不会出现在开始，故 i > 0
                    // 和之前的数相等，并且之前的数还未使用过，只有出现这种情况，才会出现相同分支
                    // 这种情况跳过即可
                    if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                        continue;
                    }
                    used[i] = true;
                    stack.add(nums[i]);
                    findPermuteUnique(nums, depth + 1, stack);
                    stack.pop();
                    used[i] = false;
                }
            }
        }

        public List<List<Integer>> permuteUnique(int[] nums) {
            int len = nums.length;
            if (len == 0) {
                return res;
            }
            // 修改 1：首先排序，之后才有可能发现重复分支
            Arrays.sort(nums);

            // 如果是降序，需要把 nums 变为包装数组类型，输入 Arrays.sort() 方法才生效，并且还要传入一个比较器，搜索之前，再转为基本类型数组，因此不建议降序排序
            // Integer[] numsBoxed = IntStream.of(nums).boxed().collect(Collectors.toList()).toArray(new Integer[0]);
            // Arrays.sort(numsBoxed, Collections.reverseOrder());
            // nums = Arrays.stream(numsBoxed).mapToInt(Integer::valueOf).toArray();

            used = new boolean[len];
            findPermuteUnique(nums, 0, new Stack<>());
            return res;
        }
    }

    class Solution1ms {
        //    思路：
//    两种思路，第一种是直接dfs全排列然后使用set暴力去重，效率较低；
//    第二种是在dfs全排列搜索交换前先判断一下是否已经交换过
        List<List<Integer>> ans = new ArrayList<>();

        public List<List<Integer>> permuteUnique(int[] nums) {
            dfs(nums,0);
            return ans;
        }

        private void dfs(int[] nums,int cur) {
            if (cur == nums.length) {
                List<Integer> line = new ArrayList<>();
                for (int i : nums) {
                    line.add(i);
                }
                ans.add(line);
            } else {
                for (int i = cur;i < nums.length;i++) {
                    if (canSwap(nums,cur,i)) {
                        swap(nums,cur,i);
                        dfs(nums,cur + 1);
                        swap(nums,cur,i);
                    }
                }
            }
        }

        private boolean canSwap(int[] nums,int begin,int end) {
            for (int i = begin;i < end;i++) {
                if (nums[i] == nums[end]) {
                    return false;
                }
            }
            return true;
        }

        private void swap(int[] nums,int i,int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

}
