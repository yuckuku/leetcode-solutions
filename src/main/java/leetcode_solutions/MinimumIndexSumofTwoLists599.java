package leetcode_solutions;

import java.util.*;

/**
 * Created by Administrator on 2017/11/24.
 */
public class MinimumIndexSumofTwoLists599 {
    public String[] findRestaurant(String[] list1, String[] list2) {
        List<String> list =new ArrayList<>();
        Map<String, Integer> map =new HashMap<>();
        for (String s : list1) {
            if(map.containsKey(s))
                map.put(s,2);
            else
                map.put(s,1);
        }
        for (String s : list2) {
            if(map.containsKey(s))
                map.put(s,2);
            else
                map.put(s,1);
        }
        for(String key:map.keySet()){
            if(map.get(key)>1)
                list.add(key);
        }
        map.clear();
        for (int i=0;i<list1.length;i++) {
            if(map.containsKey(list1[i]))
                map.put(list1[i],map.get(list1[i])+i);
            else
                map.put(list1[i],i);
        }
        for (int i=0;i<list2.length;i++) {
            if(map.containsKey(list2[i]))
                map.put(list2[i],map.get(list2[i])+i);
            else
                map.put(list2[i],i);
        }
        int minIndex=Integer.MAX_VALUE;
        List<String> reList =new ArrayList<>();
        for(String s:list){
            if(map.get(s)<minIndex){
                reList.clear();
                reList.add(s);
                minIndex=map.get(s);
            }else if(map.get(s)==minIndex){
                reList.add(s);
            }
        }
        return  reList.toArray(new String[reList.size()]);
    }
}
