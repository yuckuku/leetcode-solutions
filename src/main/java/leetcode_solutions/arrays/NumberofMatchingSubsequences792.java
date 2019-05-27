package leetcode_solutions.arrays;

import com.sun.corba.se.impl.resolver.FileResolverImpl;

import java.util.HashSet;

/**
 * @author: L'Nan
 * Date: 2019/5/13 11:10
 * Description:
 */
public class NumberofMatchingSubsequences792 {
    class Solution {
        public int numMatchingSubseq(String S, String[] words) {
            int re = 0;
            for (int i = 0; i < words.length; i++) {
                re += isSubseq(S, words[i]) ? 1 : 0;
            }
            return re;
        }

        private boolean isSubseq(String S, String word) {
            int i = 0, j = 0;
            while (i < word.length() && j < S.length()) {
                if (word.charAt(i) == S.charAt(j)) {
                    i++;
                    j++;
                } else {
                    j++;
                }
            }
            if (i == word.length()) {
                return true;
            } else {
                return false;
            }
        }
    }

    class SolutionOnLeetcode1 {
        public int numMatchingSubseq(String S, String[] words) {
            int sum = 0;
            HashSet<String> set1 = new HashSet<>();
            HashSet<String> set2 = new HashSet<>();
            for (String word : words) {
                if (!set2.contains(word) && (set1.contains(word) || isSubsequence(S, word))) {
                    sum = sum + 1;
                    set1.add(word);
                } else {
                    set2.add(word);
                }

            }
            return sum;
        }

        private boolean isSubsequence(String s, String word) {
            int index = -1;
            for (char c : word.toCharArray()) {
                index = s.indexOf(c, index + 1);
                if (index == -1) {
                    return false;
                }
            }

            return true;
        }
    }
}
