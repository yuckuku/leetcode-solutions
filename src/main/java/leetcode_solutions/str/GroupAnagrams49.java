package leetcode_solutions.str;

import org.junit.Test;

import java.util.*;

public class GroupAnagrams49 {
    class Solution {

        public List<List<String>> groupAnagrams(String[] strs) {
            Map<Integer, List<String>> map = new HashMap<>();

            for (int i = 0; i < strs.length; i++) {
                int n = ana(strs[i]);
                List<String> tmp = map.getOrDefault(n, new ArrayList<>());
                tmp.add(strs[i]);
                map.put(n, tmp);
            }

            List<List<String>> re = new ArrayList<>();

            for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
                re.add(entry.getValue());
            }
            return re;
        }

        private int ana(String s) {
            char[] chs = s.toCharArray();
            Arrays.sort(chs);
            return String.valueOf(chs).hashCode();
        }
    }

    class Solution1 {
        public List<List<String>> groupAnagrams(String[] strs) {
            int[] prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};
            HashMap<Integer, Integer> hash = new HashMap<>();
            List<List<String>> ans = new ArrayList<>();

            for (String str : strs) {
                char[] arr = str.toCharArray();
                int key = 1;
                for (char c : arr) {
                    key *= prime[c - 'a'];
                }

                if (hash.get(key) == null) {
                    List<String> list = new ArrayList<>();
                    list.add(str);
                    ans.add(list);
                    hash.put(key, ans.size() - 1);
                } else {
                    ans.get(hash.get(key)).add(str);
                }
            }

            return ans;
        }
    }

    @Test
    public void test() {
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        Solution solution = new Solution();
        List<List<String>> re = new Solution().groupAnagrams(strs);
        System.out.println(re);
        System.out.println(new Solution().ana("duh"));
        System.out.println(new Solution().ana("ill"));
        System.out.println("dog".hashCode());
        System.out.println("aid".hashCode());
    }
}
