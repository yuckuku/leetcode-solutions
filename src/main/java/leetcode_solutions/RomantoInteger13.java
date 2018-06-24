package leetcode_solutions;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/24.
 */
public class RomantoInteger13 {
    public int romanToInt(String s) {

        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        char[] chars = s.toCharArray();
        int re = 0;
        for (int i = 0; i < chars.length; i++) {
            if (i == chars.length - 1 || map.get(chars[i]) >= map.get(chars[i + 1]))
                re += map.get(chars[i]);
            else
                re -= map.get(chars[i]);
            System.out.println(re);
        }
        return re;
    }
}
