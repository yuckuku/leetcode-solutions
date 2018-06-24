package leetcode_solutions;

/**
 * Created by Administrator on 2017/10/18.
 */

public class FindTheDifference389 {

    //bit && number of chars
    public static char findTheDifference(String s, String t) {
        char c = 0;
        for (int i=0;i<s.length();i++) {
            c ^= s.charAt(i);
            System.out.println(s.charAt(i));
        }
        for (int i=0;i<t.length();i++) {
            c ^= t.charAt(i);
            System.out.println(t.charAt(i));
        }
        return c;
    }

    public static void main(String[] args){
        char theDifference = findTheDifference("abcd", "abcde");
        System.out.println(theDifference);
    }
}
