package leetcode_solutions;

/**
 * Created by Administrator on 2017/10/16.
 */
public class CountBinarySubstrings696 {

//    public int countBinarySubstrings(String s) {
//
//        list<String> list = findAllSubWithEvenElements( s);
//        if(null == list){
//            return 0;
//        }
//        int num=0;
//        for(String s:list){
//            if(check(s)){
//                num++;
//            }
//        }
//        return num;
//    }
//
//    public List<String> findAllSubWithEvenElements(String s){
//        if(null == str ||str.length<2){
//            return null;
//        }
//        List<String> list = new ArrayList<String>();
//        for(int len =2;len <=str.length();len=len+2) {
//            for (int i = 0; i <=str.length() - len ; i++) {
//                list.add(str.substring(i, i + len));
//            }
//        }
//        return list;
//    }
//
//    public boolean check(String s){
//        if(s.length%2!=0){
//            return false;
//        }
//        String regex_1 = "[1]+";
//        String regex_0 = "[0]+";
//        if(s.substring(0,s.length/2).matches(regex_1)&&s.substring(s.length/2).matches(regex_0 )){
//            return true;
//        }
//        if(s.substring(0,s.length/2).matches(regex_0)&&s.substring(s.length/2).matches(regex_1 )){
//            return true;
//        }
//        return false;
//    }

    //recommanded solution
    //the idea is group elements.
    public static int countBinarySubstrings(String s) {
        int[] groups = new int[s.length()];
        int t = 0;
        groups[0] = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) != s.charAt(i)) {
                System.out.println(t + ":");
                groups[++t] = 1;
                System.out.println("if " + t + ":" + groups[t]);
            } else {
                groups[t]++;
                System.out.println("el " + t + ":" + groups[t]);
            }
        }
//        for(int i:groups){
//            System.out.println(i);
//        }
        int ans = 0;
        for (int i = 1; i <= t; i++) {
            ans += Math.min(groups[i - 1], groups[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "00110011";
        System.out.println("s:" + s);
        int num = countBinarySubstrings(s);
        System.out.println(num);
//        Integer i1 = 1;
//        Integer i2 = 1;
//        Integer i3 = 128;
//        Integer i4 = 128;
//        Integer i5 = -1;
//        Integer i6 = -1;
//        Integer i7 = -128;
//        Integer i8 = -128;
//        System.out.println(i1==i2);
//        System.out.println(i3==i4);
//        System.out.println(i5==i6);
//        System.out.println(i7==i8);


    }

}
