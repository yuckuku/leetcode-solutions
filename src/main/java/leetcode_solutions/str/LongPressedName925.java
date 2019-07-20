package leetcode_solutions.str;

import org.junit.Test;

public class LongPressedName925 {
    class Solution {
        public boolean isLongPressedName(String name, String typed) {
            char[] chs1 = name.toCharArray();
            char[] chs2 = typed.toCharArray();
            int i = 0, j = 0;
            for (; i < chs1.length && j < chs2.length; i++, j++) {
                if (chs1[i] != chs2[j]) return false;
                int count1 = 1, count2 = 1;
                while (i < chs1.length - 1 && chs1[i] == chs1[i + 1]) {
                    i++;
                    count1++;
                }
                while (j < chs2.length - 1 && chs2[j] == chs2[j + 1]) {
                    j++;
                    count2++;
                }
                if (count2 < count1) return false;
            }
            if (j != chs2.length || i != chs1.length) return false;
            return true;
        }
    }

    @Test
    public void test() {
        String name = "pyplrz";
        String typed = "ppyypllr";
        Solution s = new Solution();
        boolean re = s.isLongPressedName(name, typed);
        System.out.println(re);
    }
}
