package leetcode_solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets78 {

    public List<List<Integer>> subsets(int[] nums) {
        if (nums.length < 1) {
            List<Integer> list = new ArrayList<>();
            List<List<Integer>> re = new ArrayList<>();
            re.add(list);
            return re;
        } else if (nums.length == 1) {
            List<List<Integer>> re = new ArrayList<>();
            List<Integer> list = new ArrayList<>();
            re.add(list);
            list = new ArrayList<>();
            list.add(nums[0]);
            re.add(list);
            return re;
        } else {
            List<List<Integer>> re = subsets(Arrays.copyOfRange(nums, 0, nums.length - 1));

            List<List<Integer>> reTemp = new ArrayList<>();


            for (List<Integer> tempList : re) {
                List<Integer> temp = new ArrayList<>();
//                for (Integer i:
//                     tempList) {
//                    temp.add(i);
//                }
                temp.addAll(tempList);
                temp.add(nums[nums.length - 1]);
                reTemp.add(temp);
            }
            re.addAll(reTemp);
            return re;
        }

    }

    @Test
    public void test() {
//        int[] arr=new int[]{1,2,3,4,5};
//        int[] temp=Arrays.copyOfRange(arr, 0, arr.length - 1);
//        for (int i:
//             temp) {
//        System.out.println(i);
//        }
        List<List<Integer>> re = subsets(new int[]{1, 2});
        System.out.println(re);
//        for (List<Integer> list :
//                re) {
//            System.out.println(list);
//        }
    }


    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsets0(int[] nums) {
        boolean[] isChoosed = new boolean[nums.length];
        addOneCondition(nums, 0, isChoosed);
        return res;
    }

    public void addOneCondition(int[] nums, int index, boolean[] isChoosed) {
        if (index >= nums.length) {
            List<Integer> tmp = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                if (isChoosed[i]) {
                    tmp.add(nums[i]);
                }
            }
            res.add(tmp);
            return;
        }
        isChoosed[index] = true;
        addOneCondition(nums, index + 1, isChoosed);
        //这里必须重新设置为0，因为传入的数组，会对该地址处的值进行修改
        isChoosed[index] = false;
        addOneCondition(nums, index + 1, isChoosed);
    }

    @Test
    public void test0(){
        List<List<Integer>> re = subsets0(new int[]{1, 2});
        System.out.println(re);
    }
}
