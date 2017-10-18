/**
 * Created by Administrator on 2017/10/16.
 */

import java.util.*;

public class FindAllNumbersDisappearedInAnArray448 {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<Integer>();
        Set<Integer> set = new HashSet<Integer>();
        if(null == nums||nums.length<=0){
            return list;
        }
        Arrays.sort(nums);
        for(int i= 1;i<=nums.length;i++){
            set.add(i);
        }
//        List<Integer> l = new ArrayList<Integer>();

        for(int num:nums){
            if(set.contains(num)){
                set.remove(num);
            }
        }

        for(int i:set){
            list.add(i);
        }
        return list;
    }
}
