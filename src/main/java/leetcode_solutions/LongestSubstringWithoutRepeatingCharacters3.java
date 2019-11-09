package leetcode_solutions;

import org.junit.Test;

import java.util.Arrays;

public class LongestSubstringWithoutRepeatingCharacters3 {

    class Solution {
        public int lengthOfLongestSubstring(String s) {
            if (s.length() <= 1) return s.length();

            int max = 0;
            int[] indexes = new int[128];//保存字母对应下标
            Arrays.fill(indexes, -1);

            int slow = 0;
            indexes[(int) s.charAt(0)] = 0;
            int fast = 1;
            for (; fast < s.length(); fast++) {
                //非重复字母
                if (indexes[(int) s.charAt(fast)] == -1) {
                    indexes[(int) s.charAt(fast)] = fast;

                } else {//重复字母
                    //更新最大长度
                    max = Math.max(max, fast - slow);
                    //获取重复位置
                    int duplicateIndex = indexes[(int) s.charAt(fast)];
                    //清理前半部分元素下标
                    for (int i = slow; i < duplicateIndex; i++) {
                        indexes[(int) s.charAt(i)] = -1;
                    }
                    //slow游标前进1
                    slow = duplicateIndex + 1;
                    //fast游标不动，更新重复元素下标
                    indexes[(int) s.charAt(fast)] = fast;
                }
            }
            max = Math.max(max, fast - slow);

            return max;
        }
    }

    class SolutionOnLeetcode1 {
        public int lengthOfLongestSubstring(String s) {

            int size, i = 0, j, k, max = 0;
            size = s.length();
            char[] arr = s.toCharArray();
            for (j = 0; j < size; j++) {
                for (k = i; k < j; k++)
                    if (arr[k] == arr[j]) {
                        i = k + 1;
                        break;
                    }
                if (j - i + 1 > max)
                    max = j - i + 1;
            }
            return max;
        }
    }

    @Test
    public void test() {
        System.out.println((int) 'a');
        System.out.println("----------------");
        String s = "abcabcbb";
        Solution solution = new Solution();
        int re = solution.lengthOfLongestSubstring(s);
        System.out.println(re);
        String s1 = "au";
        re = solution.lengthOfLongestSubstring(s1);
        System.out.println(re);
    }
}
