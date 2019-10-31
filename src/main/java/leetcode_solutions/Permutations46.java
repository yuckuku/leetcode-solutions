package leetcode_solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Permutations46 {
    class Solution {

        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> re = new ArrayList<>();
            if (nums.length == 1) {
                List<Integer> list = new ArrayList<>();
                list.add(nums[0]);
                re.add(list);
                return re;
            }
            for (int i = 0; i < nums.length; i++) {
                int[] subNums = new int[nums.length - 1];
                System.arraycopy(nums, 0, subNums, 0, i);
                System.arraycopy(nums, i + 1, subNums, i, nums.length - 1 - i);
                List<List<Integer>> tmp = permute(subNums);
                for (int i1 = 0; i1 < tmp.size(); i1++) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.addAll(tmp.get(i1));
                    re.add(list);
                }
            }
            return re;
        }
    }

    // 剑指offer上的原题
    // 思路：交换元素，用DFS做
    class Solution1 {

        // 全局变量，方便参数传递，避免方法之间写太多参数
        List<List<Integer>> result = new LinkedList<>();

        public void swap(int[] nums, int i, int j) {
            if (i != j) {
                nums[i] = nums[i] ^ nums[j];
                nums[j] = nums[i] ^ nums[j];
                nums[i] = nums[i] ^ nums[j];
            }
        }

        public void recursion(int[] nums, int index) {
            if (index == nums.length - 1) {
                List<Integer> list = new LinkedList<Integer>();
                for (int x : nums)
                    list.add(x);
                result.add(list);
            }
            for (int i = index; i < nums.length; i++) {  // i = index是与本身交换，i=num.length-1是和最后一个交换
                swap(nums, index, i);
                recursion(nums, index + 1);
                swap(nums, index, i);        // backtracing，换回来
            }
        }

        public List<List<Integer>> permute(int[] nums) {
            if (null == nums || 0 == nums.length)
                return result;
            if (1 == nums.length) {
                List<Integer> list = new LinkedList<Integer>();
                list.add(nums[0]);
                result.add(list);
                return result;
            }
            recursion(nums, 0);
            return result;
        }
    }


    @Test
    public void test() {
        int[] nums = new int[]{1, 2, 3};
        Solution solution = new Solution();
        List<List<Integer>> re = solution.permute(nums);
        System.out.println(re);
    }
}
