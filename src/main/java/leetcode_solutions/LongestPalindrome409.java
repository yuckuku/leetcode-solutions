package leetcode_solutions;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/30.
 */
public class LongestPalindrome409 {
    public int longestPalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (map.containsKey(c))
                map.put(c, map.get(c) + 1);
            else
                map.put(c, 1);
        }

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {

        }

        int num = 0;
        for (Character c : map.keySet()) {
            num += map.get(c) / 2 * 2;
        }
        System.out.println(num);
        for (Character c : map.keySet()) {
            if (map.get(c) % 2 == 1) {
                num++;
                break;
            }
        }
        return num;
    }

    //ascii code
    public int longestPalindrome1(String s) {
        int []cnt=new int[58];
        int max=0;
        int len=0;
        for(int i=0;i<s.length();i++){
            cnt[s.charAt(i)-'A']++;
        }
        for(int i=0;i<58;i++){
            if(cnt[i]%2==0)
                len+=cnt[i];
            else {
                if(cnt[i]>max){
                    len+=max-1;
                    max=cnt[i];
                }
                else len=len+cnt[i]-1;
            }
        }
        return max==0?len:len+max+1;
    }
}
