package leetcode_solutions;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/11/24.
 */
public class ContainsDuplicate217 {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set =new HashSet<>();
        for (int i :nums ) {
            set.add(i);
        }
        if(nums.length!=set.size())
            return true;
        else
            return false;
    }
}
