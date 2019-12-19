package leetcode_solutions;

import org.junit.Test;

import java.util.List;

public class WordBreak139 {
    //trace back
    class Solution {
        public boolean wordBreak(String s, List<String> wordDict) {
            return backTrace(s, wordDict);
        }

        private boolean backTrace(String s, List<String> wordDict) {
            if (s.length() == 0) return true;
            boolean re = false;
            for (int i = 0; i < wordDict.size(); i++) {
                if (s.startsWith(wordDict.get(i))) {
                    re = backTrace(s.substring(wordDict.get(i).length(), s.length()), wordDict);
                }
                //trace back
                if (re) return re;
            }
            return false;
        }
    }

    @Test
    public void test() {
        String s = "abcdefgabcdefg";
        System.out.println(s.indexOf("abcdefg"));
        System.out.println(s.substring("abcdefg".length(), s.length()));
    }
}
