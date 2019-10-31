package leetcode_solutions;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LetterTilePossibilities1079 {
    class Solution {
        int re = 0;

        public int numTilePossibilities(String tiles) {
            Map<Character, Integer> map = new HashMap<>();
            char[] chs = tiles.toCharArray();
            for (int i = 0; i < chs.length; i++) {
                if (map.containsKey(chs[i])) {
                    map.put(chs[i], map.get(chs[i]) + 1);
                } else {
                    map.put(chs[i], 1);
                }
            }
            LinkedList path = new LinkedList();
            backtrack(map, path);
            return re;
        }

        private void backtrack(Map<Character, Integer> map, LinkedList path) {
            if (path.size() > 0) re += 1;
            for (Character key : map.keySet()) {
                if (map.get(key) > 0) {
                    path.add(key);
                    map.put(key, map.get(key) - 1);
//                if (map.get(key) == 0) map.remove(key);
                    backtrack(map, path);
                    path.removeLast();
                    map.put(key, map.get(key) + 1);
                }
            }
        }
    }

    //回溯+剪枝算法？
    class Solution1 {
        public int numTilePossibilities(String tiles) {
            char[] chars = tiles.toCharArray();
            Arrays.sort(chars);
            return helper(chars, 0, chars.length, 1, 1, 0, new int[26]);
        }

        //m 层数
        //a 深度之积
        // b 当前字符数目之积
        private int helper(char[] chars, int i, int n, int a, int b, int m, int[] counts) {
            int result = 0;
            a *= (++m);
            for (int j = i; j < n; ++j) {
                int index = chars[j] - 'A';
                if (j == i || chars[j] != chars[j - 1]) {
                    b *= (++counts[index]);
                    System.out.println("a=" + a + " b=" + b + " " + a / b);
                    result += a / b + helper(chars, j + 1, n, a, b, m, counts);
                    b /= (counts[index]--);
                }
            }
            return result;
        }
    }

    class Solution2 {
        public int numTilePossibilities(String tiles) {
            int[] nums = new int[26];
            for (int i = 0; i < tiles.length(); i++) {
                nums[tiles.charAt(i) - 'A']++;
            }
            return getNum(nums, tiles.length());
        }

        public int getNum(int[] nums, int len) {
            if (len <= 1) {
                return len;
            } else {
                int res = 0;
                for (int j = 0; j < nums.length; j++) {
                    if (nums[j] > 0) {
                        nums[j]--;
                        res += getNum(nums, len - 1) + 1;
                        nums[j]++;
                    }
                }
                return res;
            }
        }
    }

    @Test
    public void test() {
        String tile = "AAB";
        Solution1 solution = new Solution1();
        int re = solution.numTilePossibilities(tile);
        System.out.println(re);
    }
}
