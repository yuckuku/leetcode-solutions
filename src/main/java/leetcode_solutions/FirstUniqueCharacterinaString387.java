package leetcode_solutions;

import org.junit.Test;

import java.util.*;

/**
 * Created by Administrator on 2017/11/14.
 */
public class FirstUniqueCharacterinaString387 {
    //time limit exceeded
    public int firstUniqChar(String s) {
        System.out.println("s=" + s);
        System.out.println("s.length " + s.length());
        if (s == null || s.equals(""))
            return -1;
        int i = 0;
        String temp = s;
        for (; i < s.length(); i++) {
            System.out.println(s);
            System.out.println(i);
            System.out.println(s.substring(0, i) + s.substring(i + 1));
            if ((s.substring(0, i) + s.substring(i + 1)).contains(s.charAt(i) + "")) {
                System.out.println("cut");
//                temp = s.substring(i + 1);
                continue;
            } else {
                break;
            }
        }
        System.out.println("i= " + i);
        if (i == s.length())
            return -1;
        else
            return i;
    }

    public int firstUniqChar1(String s) {
        if (s == null || s.equals(""))
            return -1;
        Map<Character, Integer> map = new HashMap();
        for (char c :
                s.toCharArray()) {
            if (map.containsKey(c))
                map.put(c, map.get(c) + 1);
            else
                map.put(c, 1);
        }
//        //排序
//        List<Map.Entry<Character,Integer>> list = new ArrayList<Map.Entry<Character,Integer>>(map.entrySet());
//        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
//            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
//                return (o1.getValue() - o2.getValue());
////                return o1.getValue().compareTo(o2.getValue());
//            }
//        });
//        Map.Entry<Character,Integer> mapping = list.get(0);
        int i=0;
        for (; i <s.length() ; i++) {
            if(map.get(s.charAt(i))==1){
                break;
            }
        }
        if (i == s.length())
            return -1;
        else
            return i;    }

    @Test
    public void test() {
        String s = "lle";
        FirstUniqueCharacterinaString387 firstUniqueCharacterinaString387 = new FirstUniqueCharacterinaString387();
        int i = firstUniqueCharacterinaString387.firstUniqChar(s);
        System.out.println(i);
    }

    @Test
    public void test1() {
        int i = 0;
        for (printChar('A'); printChar('B') && (i < 2); printChar('C'), i++) {
//            i++;
            printChar('D');
            System.out.println("====================");
        }
        System.out.println(i);
    }

    public static boolean printChar(char c) {
        System.out.println(c);
        return true;
    }

}
