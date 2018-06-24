package leetcode_solutions;

import org.junit.Test;

import java.util.*;


/**
 * Created by Administrator on 2017/11/13.
 */
public class MajorityElement169 {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num :
                nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        //排序
        List<Map.Entry<Integer,Integer>> list = new ArrayList<Map.Entry<Integer,Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return (o1.getValue() - o2.getValue());
//                return o1.getValue().compareTo(o2.getValue());
            }
        });
        for(Map.Entry<Integer,Integer> mapping:list){
            System.out.println(mapping.getKey()+":"+mapping.getValue());
        }
        Map.Entry<Integer,Integer> mapping = list.get(0);
        return mapping.getKey();
    }

    @Test
    public void test(){
        MajorityElement169 majorityElement169 =new MajorityElement169();
        int[] nums=new int[]{3,2,3};
        System.out.println(majorityElement169.majorityElement(nums));
    }
}
