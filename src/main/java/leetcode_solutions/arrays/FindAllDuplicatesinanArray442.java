package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllDuplicatesinanArray442 {

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> list = new ArrayList<>();
        if (null == nums || nums.length < 1) {
            return list;
        }
        Arrays.sort(nums);

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                boolean flag = true;
                if (i + 1 < nums.length && nums[i] == nums[i + 1]) {
                    flag = false;
                }
                if (i - 2 >= 0 && nums[i] == nums[i - 2]) {
                    flag = false;
                }

                if (flag) {
                    list.add(nums[i]);
                }
            }
        }
        return list;
    }

    @Test
    public void test() {
        int[] nums = new int[]{4, 3, 2, 7, 8, 2, 3, 1};
        List<Integer> list = findDuplicates(nums);
        System.out.println(list.toString());
    }

    public List<Integer> findDuplicates0(int[] nums) {
        int[] arr = new int[nums.length+1];

        for (int i : nums) {
            arr[i]++;
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]>1){
                list.add(i);
            }

        }

        return list;
    }
}
