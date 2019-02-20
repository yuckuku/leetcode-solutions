package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: L'Nan
 * @Date: 2018/11/8 16:39
 * @Description:
 */
public class MajorityElementII229 {
    /**
     * @param nums
     * @return
     * my solution:traverse array once
     */
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> list = new ArrayList<>();

        if (null == nums || nums.length < 1) {
            return list;
        }

        int times = nums.length / 3;
        Arrays.sort(nums);
        int lastElement = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == lastElement) {
                count++;
            } else {
                if (count > times) {
                    list.add(lastElement);
                }
                lastElement = nums[i];
                count = 1;
            }
        }
        if (count > times) {
            list.add(lastElement);
        }
        return list;
    }

    @Test
    public void test() {
        int[] nums = new int[]{3, 2, 3};
        List<Integer> list = majorityElement(nums);
        System.out.println(list);
    }

    /**
     * solution on leetcode
     */
    class Solution {
        public List<Integer> majorityElement(int[] nums) {
            List<Integer> list = new ArrayList<>();
            int a = 0;
            int b = 0;
            int aTimes = 0;
            int bTimes = 0;
            for (int temp : nums) {//mark: 找到占用数组中leng/n的数字
                //当计数器为0时，需要变更a，但不能变更为与b相同的元素
                if ((aTimes == 0 && temp != b) || temp == a) {
                    a = temp;
                    aTimes++;
                } else if (bTimes == 0 || temp == b) {
                    b = temp;
                    bTimes++;
                } else {
                    aTimes--;
                    bTimes--;
                }
            }
            int size = nums.length / 3;
            aTimes = 0;
            bTimes = 0;
            for (int c : nums) {
                if (c == a) {
                    aTimes++;
                } else if (c == b) {
                    bTimes++;
                }
            }
            if (aTimes > size) {
                list.add(a);
            }
            if (bTimes > size) {
                list.add(b);
            }
            return list;
        }
    }

   /* @Test
    public void test0(){
        int[] nums=new int[]{1,1,1,4,4,6,7,8,9};

        int a = 0;
        int b = 0;
        int aTimes = 0;
        int bTimes = 0;
        for (int temp : nums) {
            if ((aTimes == 0 && temp != b) || temp == a) {
                a = temp;
                aTimes++;
            } else if (bTimes == 0 || temp == b) {
                b = temp;
                bTimes++;
            } else {
                aTimes--;
                bTimes--;
            }
        }
        System.out.println(a);
        System.out.println(aTimes);
        System.out.println(b);
        System.out.println(bTimes);

    }*/
}
