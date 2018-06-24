package leetcode_solutions;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/10/10.
 */
public class LongestUncommonSubsequenceI {
    public int findLUSlength(String a, String b) {
        if (a.length() > b.length()) {
            return a.length();
        }
        if (a.length() < b.length()) {
            return b.length();
        }
        int usLen = -1;
        Set<String> seta = findAllSubsequences (a);
        for(String str: seta){
            if(!b.contains(str)&& str.length()>usLen ){
                usLen =str.length();
            }
        }
        Set<String> setb = findAllSubsequences (b);
        for(String str: setb){
            if(!a.contains(str)&& str.length()>usLen ){
                usLen =str.length();
            }
        }

        return usLen ;
    }

    public Set<String> findAllSubsequences(String str){
        if(null == str ){
            return null;
        }
        Set<String> set = new HashSet<String>();
        for(int len =0;len <=str.length();len++) {
            for (int i = 0; i <=str.length() - len ; i++) {
                set.add(str.substring(i, i + len));
            }
        }
        return set;
    }
}
