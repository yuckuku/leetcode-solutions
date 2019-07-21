package leetcode_solutions.str;

public class LengthofLastWord58 {
    class Solution {
        public int lengthOfLastWord(String s) {
            int index = s.lastIndexOf(' ');
            String[] ss = s.split("\\s+");
            if (ss.length > 0) return ss[ss.length - 1].length();
            else return 0;
        }
    }

    class Solution1 {
        public int lengthOfLastWord(String s) {
            int end = s.length() - 1;
            while (end >= 0 && s.charAt(end) == ' ') end--;
            if (end < 0)
                return 0;
            int start = end;
            while (start >= 0 && s.charAt(start) != ' ') start--;
            return end - start;
        }
    }
}
