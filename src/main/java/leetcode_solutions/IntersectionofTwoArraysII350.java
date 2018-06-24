package leetcode_solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

/**
 * Created by Administrator on 2017/12/7.
 */
public class IntersectionofTwoArraysII350 {

  public int[] intersect(int[] nums1, int[] nums2) {
    Map<Integer, Integer> map1 = new HashMap<>();
    Map<Integer, Integer> map2 = new HashMap<>();

    for (int num1 : nums1) {
      if (map1.containsKey(num1)) {
        map1.put(num1, map1.get(num1) + 1);
      } else {
        map1.put(num1, 1);
      }
    }
    for (int num2 : nums2) {
      if (map2.containsKey(num2)) {
        map2.put(num2, map2.get(num2) + 1);
      } else {
        map2.put(num2, 1);
      }
    }
    List<Integer> list = new ArrayList<>();
    for (Map.Entry<Integer, Integer> entry1 : map1.entrySet()) {
      for (Map.Entry<Integer, Integer> entry2 : map2.entrySet()) {
        System.out.println("--- " + entry1.getKey() + " --- " + entry2.getKey());
        if (entry2.getKey().equals(entry1.getKey())) {
          System.out.println(entry2.getKey());
          int m = entry1.getValue() < entry2.getValue() ? entry1.getValue() : entry2.getValue();
          for (int i = 0; i < m; i++) {
            list.add(entry1.getKey());
          }
          break;
        }
      }
    }
    int[] re = new int[list.size()];
    for (int i = 0; i < list.size(); i++) {
      re[i] = list.get(i);
    }
    return re;
  }

  //sort
  public int[] intersect1(int[] nums1, int[] nums2) {
    List<Integer> list = new ArrayList<>();
    Arrays.sort(nums1);
    Arrays.sort(nums2);
    for (int i = 0, j = 0; i < nums1.length && j < nums2.length; ) {
      if (nums1[i] == nums2[j]) {
        list.add(nums1[i]);
        i++;
        j++;
      } else if (nums1[i] > nums2[j]) {
        j++;
      } else if (nums1[i] < nums2[j]) {
        i++;
      }
    }
    int[] re = new int[list.size()];
    for (int i = 0; i < list.size(); i++) {
      re[i] = list.get(i);
    }
    return re;
  }

  @Test
  public void test() {
    System.out.println(Integer.MIN_VALUE);
  }
}
