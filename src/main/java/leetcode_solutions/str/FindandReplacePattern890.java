package leetcode_solutions.str;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindandReplacePattern890 {
    class Solution {
        public List<String> findAndReplacePattern(String[] words, String pattern) {
            List<String> re = new ArrayList<>();
            for (int i = 0; i < words.length; i++) {
                if (isPattern(words[i], pattern) && isPattern(pattern, words[i])) {
                    re.add(words[i]);
                }
            }
            return re;
        }

        private boolean isPattern(String word, String pattern) {
            if (word.length() != pattern.length()) return false;
            char[] chs = new char[58];
            for (int i = 0; i < word.length(); i++) {
                int j = word.charAt(i) - 65;
                if (0 == chs[j]) chs[j] = pattern.charAt(i);
                else if (chs[j] != pattern.charAt(i)) {
                    return false;
                }
            }
            return true;
        }
    }

    class Solution1 {
        public List<String> findAndReplacePattern(String[] words, String pattern) {
            List<String> res = new ArrayList();
            for(String word : words){
                if(match(word, pattern))
                    res.add(word);
            }
            return res;

        }
        public boolean match(String word, String pattern){
            for(int i = 0; i < word.length(); i++){
                if(word.indexOf(word.charAt(i), i+1) != pattern.indexOf(pattern.charAt(i), i+1))
                    return false;
            }
            return true;
        }
    }

    @Test
    public void test() {
        String[] words = new String[]{"aqq", "abc", "deq", "mee", "dkd", "ccc"};
        String pattern = "abb";
        Solution solution = new Solution();
        List<String> re = solution.findAndReplacePattern(words, pattern);
        System.out.println(re);

        char[] chs = new char[58];

        System.out.println(Arrays.toString(chs));
        System.out.println(chs[0]);
        System.out.println((int) chs[0]);
        int i = 'a';
        System.out.println(i);
        System.out.println((int) 'a');//97
        System.out.println((int) 'z');//122
        System.out.println((int) 'A');//65
        System.out.println((int) 'Z');//90
    }
}
