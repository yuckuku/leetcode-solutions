package leetcode_solutions.str;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class UniqueMorseCodeWords804 {
    class Solution {
        private String[] morses = new String[]{".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

        public int uniqueMorseRepresentations(String[] words) {
            Set<String> set = new HashSet<>();
            for (String str :
                    words) {
                set.add(strToMorse(str));
            }
            return set.size();
        }

        private String strToMorse(String str) {
            char[] chs = str.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < chs.length; i++) {
                int ch_ascii = Integer.valueOf(chs[i]);
                int index = ch_ascii - 97;
                sb.append(morses[index]);
            }
            return sb.toString();
        }
    }

    class SolutionOnLeetcode1 {
        public int uniqueMorseRepresentations(String[] words) {
            String[] code = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
            Set<String> set = new HashSet<>();

            for (String word : words) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < word.length(); i++) {
                    sb.append(code[word.charAt(i) - 'a']);
                }
                set.add(sb.toString());
            }
            return set.size();

        }
    }

    @Test
    public void test() {
        System.out.println('a');
    }
}
