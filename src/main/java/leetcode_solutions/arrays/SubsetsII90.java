package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.*;

/**
 * @author: L'Nan
 * Date: 2019/4/16 21:50
 * Description:
 */
public class SubsetsII90 {
    /**
     * 计算笛卡尔乘积(算法错误)
     */
    class Solution {
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            List<List<Integer>> re = new ArrayList<>();

            Map<Integer, Integer> map = new HashMap<>();
            int[] lengths = new int[map.size()];
            Map<Integer, Integer> map1 = new HashMap<>();
            for (int num : nums) {
                if (map.containsKey(num)) map.put(num, map.get(num) + 1);
                else {
                    map.put(num, 1);
                    map1.put(num, 0);
                }
            }


            List<Integer> list = null;
            map.values();
            int num = 1;
            for (Integer value : map.values()) {
                num *= (value + 1);
            }
            for (int k = 0; k < num; k++) {
                list = new ArrayList<>();
                for (Integer key : map.keySet()) {
                    int i = map1.get(key);
                    for (int j = 0; j < i; j++) {
                        list.add(key);
                    }
                }
                re.add(list);
                for (Integer key : map.keySet()) {
                    if (map1.get(key) < map.get(key)) {
                        map1.put(key, map1.get(key) + 1);
                        break;
                    }
                }
            }
            return re;
        }
    }

    /**
     * dfs
     */
    class Solution1 {
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            Arrays.sort(nums);
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> out = new ArrayList<>();
            getSubsets(nums, 0, out, res);
            return res;
        }

        private void getSubsets(int[] nums, int pos, List<Integer> out, List<List<Integer>> res) {
            List<Integer> temp = new ArrayList<>(out.size());
            temp.addAll(out);
//            res.add(new ArrayList<Integer>(out));
            res.add(temp);
            for (int i = pos; i < nums.length; ++i) {
                out.add(nums[i]);
                getSubsets(nums, i + 1, out, res);
                out.remove(out.size() - 1);
                while (i + 1 < nums.length && nums[i] == nums[i + 1]) ++i;
            }
        }
    }


    @Test
    public void test() {
        int[] nums = new int[]{1, 2, 2};

        List<List<Integer>> re = new Solution1().subsetsWithDup(nums);

        System.out.println(re);
    }
}
