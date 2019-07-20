package leetcode_solutions.str;

public class ReverseVowelsofaString345 {
    class Solution {
        public String reverseVowels(String s) {
            char[] chs = s.toCharArray();
            int l = 0, r = chs.length - 1;
            while (l < r) {
                while (l < r) {
                    if (chs[l] == 'a' || chs[l] == 'e' || chs[l] == 'i' || chs[l] == 'o' || chs[l] == 'u' ||
                            chs[l] == 'A' || chs[l] == 'E' || chs[l] == 'I' || chs[l] == 'O' || chs[l] == 'U') break;
                    else l++;
                }
                while (l < r) {
                    if (chs[r] == 'a' || chs[r] == 'e' || chs[r] == 'i' || chs[r] == 'o' || chs[r] == 'u' ||
                            chs[r] == 'A' || chs[r] == 'E' || chs[r] == 'I' || chs[r] == 'O' || chs[r] == 'U') break;
                    else r--;
                }
                if (l < r) {
                    char tmp = chs[l];
                    chs[l] = chs[r];
                    chs[r] = tmp;
//                    System.out.println(String.valueOf(chs));
                    l++;
                    r--;
                }
            }
            return String.valueOf(chs);
        }
    }

    class SolutionOnLeetcode1 {
        public String reverseVowels(String s) {
            if (s == null || s.trim().equals("")) return s;
            char[] cs = s.toCharArray();
            int low = 0, high = cs.length - 1;
            while (low < high) {
                if (isVowelChar(cs[low])) {
                    if (isVowelChar(cs[high])) {
                        char temp = cs[low];
                        cs[low] = cs[high];
                        cs[high] = temp;
                        low++;
                        high--;
                    } else {
                        high--;
                    }
                } else {
                    low++;
                }
            }
            return String.valueOf(cs);
        }

        private boolean isVowelChar(char ch) {
            return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' || ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U';
        }
    }
}
