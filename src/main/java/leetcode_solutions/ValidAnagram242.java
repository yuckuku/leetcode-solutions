package leetcode_solutions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/17.
 */
public class ValidAnagram242 {
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null)
            return false;
        if (s.length() != t.length())
            return false;
        Map<Character, Integer> map_s = new HashMap<>();
        Map<Character, Integer> map_t = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (map_s.containsKey(c))
                map_s.put(c, map_s.get(c) + 1);
            else
                map_s.put(c, 1);
        }
        for (char c : t.toCharArray()) {
            if (map_t.containsKey(c))
                map_t.put(c, map_t.get(c) + 1);
            else
                map_t.put(c, 1);
        }
        if (map_s.size() != map_t.size())
            return false;
        boolean flag = true;
        Iterator<Character> iter = map_s.keySet().iterator();
        while (iter.hasNext()) {
            char m1Key = (char) iter.next();
            if (!map_s.get(m1Key).equals(map_t.get(m1Key))) {
                flag = false;
            }
        }
//        for (char c : map_s.keySet()) {
//            if (map_t.get(c) != map_s.get(c))
//                flag = false;
//        }
        return flag;
    }

    //smart solution
    public boolean isAnagram1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }
    //same idea with my solution
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a']++;
            counter[t.charAt(i) - 'a']--;
        }
        for (int count : counter) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }
    //hash code solution
    public boolean isAnagram3(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a']++;
            counter[t.charAt(i) - 'a']--;
        }
        for (int count : counter) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }
}
