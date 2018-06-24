package leetcode_solutions;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/10/31.
 */
public class IntersectionofTwoArrays349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for (int num1_1 :
                nums1) {
            for (int num2_2 :
                    nums2) {
                if (num1_1 == num2_2) {
                    set.add(num1_1);
                }
            }
        }
        Object[] objects = set.toArray();
        int[] re = new int[objects.length];
        for (int i = 0; i < objects.length; i++) {
            re[i] = (int) objects[i];
        }
        return re;

    }
}
