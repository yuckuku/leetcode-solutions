package leetcode_solutions.str;

import org.junit.Test;

import java.util.*;


public class MostCommonWord819 {
    class Solution {
        public String mostCommonWord(String paragraph, String[] banned) {
            char[] chs = paragraph.toCharArray();
            Map<String, Integer> map = new HashMap<>();
            char[] tmp = new char[10];
            int tmpIdx = 0;
            for (int i = 0; i < chs.length; i++) {
                if (chs[i] >= 'a' && chs[i] <= 'z') {
                    tmp[tmpIdx++] = chs[i];
                } else if (chs[i] >= 'A' && chs[i] <= 'Z') {
                    tmp[tmpIdx++] = (char) (chs[i] + 32);
                } else {
                    if (tmpIdx > 0) {
                        String str = String.valueOf(tmp, 0, tmpIdx);
                        map.put(str, map.getOrDefault(str, 0) + 1);
                        tmpIdx = 0;
                    }
                }
            }
            if (chs[chs.length - 1] >= 'a' && chs[chs.length - 1] <= 'z' || (chs[chs.length - 1] >= 'A' && chs[chs.length - 1] <= 'Z')) {
                if (tmpIdx > 0) {
                    String str = String.valueOf(tmp, 0, tmpIdx);
                    map.put(str, map.getOrDefault(str, 0) + 1);
                    tmpIdx = 0;
                }
            }
//            System.out.println(map);
            //sort
            Map<String, Integer> sorted = map.entrySet().stream().sorted(Collections.reverseOrder(java.util.Map.Entry.comparingByValue())).collect(java.util.stream.Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                    LinkedHashMap::new));
//            System.out.println(sorted);
            Set<String> set = new HashSet<>(Arrays.asList(banned));
            for (Map.Entry<String, Integer> entry :
                    sorted.entrySet()) {
                if (!set.contains(entry.getKey())) {
                    return entry.getKey();
                }
            }
            return "";
        }
    }

    class SolutionOnLeetcode1 {
        public String mostCommonWord(String paragraph, String[] banned) {
            paragraph += ".";

            Set<String> banset = new HashSet();
            for (String word : banned) banset.add(word);
            Map<String, Integer> count = new HashMap();

            String ans = "";
            int ansfreq = 0;

            StringBuilder word = new StringBuilder();
            for (char c : paragraph.toCharArray()) {
                if (Character.isLetter(c)) {
                    word.append(Character.toLowerCase(c));
                } else if (word.length() > 0) {
                    String finalword = word.toString();
                    if (!banset.contains(finalword)) {
                        count.put(finalword, count.getOrDefault(finalword, 0) + 1);
                        if (count.get(finalword) > ansfreq) {
                            ans = finalword;
                            ansfreq = count.get(finalword);
                        }
                    }
                    word = new StringBuilder();
                }
            }
            return ans;
        }
    }

    @Test
    public void test() {
        String paragraph = "Bob";
        String[] banned = new String[0];
        Solution s = new Solution();
        String re = s.mostCommonWord(paragraph, banned);
        System.out.println(re);
//        System.out.println((int) 'a');
//        System.out.println((int) 'z');
//        System.out.println((int) 'A');
//        System.out.println((int) 'Z');
//        char[] tmp = new char[10];
//        System.out.println(new String(tmp));
    }
}
